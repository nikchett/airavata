/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package org.apache.airavata.gfac.server;

import com.google.common.eventbus.EventBus;
import org.apache.airavata.common.exception.AiravataStartupException;
import org.apache.airavata.common.utils.LocalEventPublisher;
import org.apache.airavata.gfac.core.GFacConstants;
import org.apache.airavata.gfac.core.GFacWorker;
import org.apache.airavata.gfac.core.context.ProcessContext;
import org.apache.airavata.registry.cpi.AppCatalog;
import org.apache.airavata.common.exception.AiravataException;
import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.apache.airavata.common.logger.AiravataLogger;
import org.apache.airavata.common.logger.AiravataLoggerFactory;
import org.apache.airavata.common.utils.AiravataZKUtils;
import org.apache.airavata.common.utils.Constants;
import org.apache.airavata.common.utils.ServerSettings;
import org.apache.airavata.common.utils.ThriftUtils;
import org.apache.airavata.common.utils.listener.AbstractActivityListener;
import org.apache.airavata.registry.core.experiment.catalog.impl.RegistryFactory;
import org.apache.airavata.gfac.core.GFacException;
import org.apache.airavata.gfac.core.GFac;
import org.apache.airavata.gfac.core.handler.ThreadedHandler;
import org.apache.airavata.gfac.core.GFacThreadPoolExecutor;
import org.apache.airavata.gfac.core.GFacUtils;
import org.apache.airavata.gfac.cpi.GfacService;
import org.apache.airavata.gfac.cpi.gfac_cpi_serviceConstants;
import org.apache.airavata.gfac.impl.BetterGfacImpl;
import org.apache.airavata.gfac.impl.InputHandlerWorker;
import org.apache.airavata.messaging.core.MessageContext;
import org.apache.airavata.messaging.core.MessageHandler;
import org.apache.airavata.messaging.core.MessagingConstants;
import org.apache.airavata.messaging.core.Publisher;
import org.apache.airavata.messaging.core.PublisherFactory;
import org.apache.airavata.messaging.core.impl.RabbitMQTaskLaunchConsumer;
import org.apache.airavata.model.messaging.event.MessageType;
import org.apache.airavata.model.messaging.event.TaskSubmitEvent;
import org.apache.airavata.model.messaging.event.TaskTerminateEvent;
import org.apache.airavata.model.workspace.experiment.ExperimentState;
import org.apache.airavata.model.workspace.experiment.ExperimentStatus;
import org.apache.airavata.registry.cpi.ExperimentCatalog;
import org.apache.airavata.registry.cpi.ExperimentCatalogModelType;
import org.apache.airavata.registry.cpi.RegistryException;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class GfacServerHandler implements GfacService.Iface {
    private final static AiravataLogger logger = AiravataLoggerFactory.getLogger(GfacServerHandler.class);
    private RabbitMQTaskLaunchConsumer rabbitMQTaskLaunchConsumer;
    private static int requestCount=0;
    private ExperimentCatalog experimentCatalog;
    private AppCatalog appCatalog;
    private String airavataUserName;
    private CuratorFramework curatorClient;
    private LocalEventPublisher localEventPublisher;
    private String airavataServerHostPort;
    private BlockingQueue<TaskSubmitEvent> taskSubmitEvents;
    private static File gfacConfigFile;
    private static List<ThreadedHandler> daemonHandlers = new ArrayList<ThreadedHandler>();
    private static List<AbstractActivityListener> activityListeners = new ArrayList<AbstractActivityListener>();

    public GfacServerHandler() throws AiravataStartupException {
        try {
            startCuratorClient();
            initZkDataStructure();
            initAMQPClient();
            localEventPublisher = new LocalEventPublisher(new EventBus());
            experimentCatalog = RegistryFactory.getDefaultExpCatalog();
            appCatalog = RegistryFactory.getAppCatalog();
            startStatusUpdators(experimentCatalog, curatorClient, localEventPublisher, rabbitMQTaskLaunchConsumer);
        } catch (Exception e) {
            throw new AiravataStartupException("Gfac Server Initialization error ", e);
        }
    }

    private void initAMQPClient() throws AiravataException {
        rabbitMQTaskLaunchConsumer = new RabbitMQTaskLaunchConsumer();
        rabbitMQTaskLaunchConsumer.listen(new TaskLaunchMessageHandler());
    }

    private void startCuratorClient() throws ApplicationSettingsException {
        String connectionSting = ServerSettings.getZookeeperConnection();
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        curatorClient = CuratorFrameworkFactory.newClient(connectionSting, retryPolicy);
        curatorClient.start();
    }

    private void initZkDataStructure() throws Exception {
        /*
        *|/servers
        *    - /gfac
        *        - /gfac-node0 (localhost:2181)
        *|/experiments
         */
        airavataServerHostPort = ServerSettings.getGfacServerHost()  + ":" + ServerSettings.getGFacServerPort();
        // create PERSISTENT nodes
        ZKPaths.mkdirs(curatorClient.getZookeeperClient().getZooKeeper(), GFacUtils.getZKGfacServersParentPath());
        ZKPaths.mkdirs(curatorClient.getZookeeperClient().getZooKeeper(), GFacConstants.ZOOKEEPER_EXPERIMENT_NODE);
        // create EPHEMERAL server name node
        String gfacName = ServerSettings.getGFacServerName();
        if (curatorClient.checkExists().forPath(GFacUtils.getZKGfacServersParentPath() + (gfacName.startsWith("/") ? gfacName : "/" + gfacName)) == null) {
            curatorClient.create().withMode(CreateMode.EPHEMERAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(GFacUtils.getZKGfacServersParentPath() + (gfacName.startsWith("/") ? gfacName : "/" + gfacName));

        }
        curatorClient.setData().withVersion(-1).forPath(GFacUtils.getZKGfacServersParentPath() +
                (gfacName.startsWith("/") ? gfacName : "/" + gfacName), new String(airavataServerHostPort).getBytes());
    }

    public static void main(String[] args) {
        RabbitMQTaskLaunchConsumer rabbitMQTaskLaunchConsumer = null;
        try {
            rabbitMQTaskLaunchConsumer = new RabbitMQTaskLaunchConsumer();
            rabbitMQTaskLaunchConsumer.listen(new TestHandler());
        } catch (AiravataException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private long ByateArrayToLong(byte[] data) {
        long value = 0;
        for (int i = 0; i < data.length; i++)
        {
            value += ((long) data[i] & 0xffL) << (8 * i);
        }
        return value;
    }

    public String getGFACServiceVersion() throws TException {
        return gfac_cpi_serviceConstants.GFAC_CPI_VERSION;
    }

    /**
     * * After creating the experiment Data and Task Data in the orchestrator
     * * Orchestrator has to invoke this operation for each Task per experiment to run
     * * the actual Job related actions.
     * *
     * * @param experimentID
     * * @param taskID
     * * @param gatewayId:
     * *  The GatewayId is inferred from security context and passed onto gfac.
     * * @return sucess/failure
     * *
     * *
     *
     * @param experimentId - ExperimentModel id in registry
     * @param processId - processModel id in registry
     * @param gatewayId - gateway Identification
     */
    public boolean submitJob(String experimentId, String processId, String gatewayId, String tokenId) throws TException {
        requestCount++;
        logger.info("-----------------------------------------------------" + requestCount + "-----------------------------------------------------");
        logger.infoId(experimentId, "GFac Received submit job request for the Experiment: {} process: {}", experimentId, processId);
        ProcessContext processContext = new ProcessContext(processId, gatewayId, tokenId);
        processContext.setAppCatalog(appCatalog);
        processContext.setExperimentCatalog(experimentCatalog);
        processContext.setCuratorClient(curatorClient);
        processContext.setLocalEventPublisher(localEventPublisher);

        GFacWorker worker = new GFacWorker(processContext);
        InputHandlerWorker inputHandlerWorker = new InputHandlerWorker(BetterGfacImpl.getInstance(), experimentId,
                processId, gatewayId, tokenId);
//        try {
//            if( gfac.submitJob(experimentId, taskId, gatewayId)){
        logger.debugId(experimentId, "Submitted job to the Gfac Implementation, experiment {}, task {}, gateway " +
                "{}", experimentId, processId, gatewayId);

        GFacThreadPoolExecutor.getCachedThreadPool().execute(inputHandlerWorker);

        // we immediately return when we have a threadpool
        return true;
    }

    public boolean cancelJob(String experimentId, String taskId, String gatewayId, String tokenId) throws TException {
        logger.infoId(experimentId, "GFac Received cancel job request for Experiment: {} TaskId: {} ", experimentId, taskId);
        try {
            if (BetterGfacImpl.getInstance().cancel(experimentId, taskId, gatewayId, tokenId)) {
                logger.debugId(experimentId, "Successfully cancelled job, experiment {} , task {}", experimentId, taskId);
                return true;
            } else {
                logger.errorId(experimentId, "Job cancellation failed, experiment {} , task {}", experimentId, taskId);
                return false;
            }
        } catch (Exception e) {
            logger.errorId(experimentId, "Error cancelling the experiment {}.", experimentId);
            throw new TException("Error cancelling the experiment : " + e.getMessage(), e);
        }
    }

    public ExperimentCatalog getExperimentCatalog() {
        return experimentCatalog;
    }

    public void setExperimentCatalog(ExperimentCatalog experimentCatalog) {
        this.experimentCatalog = experimentCatalog;
    }


    private GFac getGfac() throws TException {
        GFac gFac = BetterGfacImpl.getInstance();
        gFac.init(experimentCatalog, appCatalog, curatorClient, localEventPublisher);
        return gFac;
    }

    public static void startStatusUpdators(ExperimentCatalog experimentCatalog, CuratorFramework curatorClient, LocalEventPublisher publisher,

                                           RabbitMQTaskLaunchConsumer rabbitMQTaskLaunchConsumer) {
        try {
            String[] listenerClassList = ServerSettings.getActivityListeners();
            Publisher rabbitMQPublisher = PublisherFactory.createActivityPublisher();
            for (String listenerClass : listenerClassList) {
                Class<? extends AbstractActivityListener> aClass = Class.forName(listenerClass).asSubclass(AbstractActivityListener.class);
                AbstractActivityListener abstractActivityListener = aClass.newInstance();
                activityListeners.add(abstractActivityListener);
                abstractActivityListener.setup(publisher, experimentCatalog, curatorClient, rabbitMQPublisher, rabbitMQTaskLaunchConsumer);
                logger.info("Registering listener: " + listenerClass);
                publisher.registerListener(abstractActivityListener);
            }
        } catch (Exception e) {
            logger.error("Error loading the listener classes configured in airavata-server.properties", e);
        }
    }
    private static  class TestHandler implements MessageHandler{
        @Override
        public Map<String, Object> getProperties() {
            Map<String, Object> props = new HashMap<String, Object>();
            ArrayList<String> keys = new ArrayList<String>();
            keys.add(ServerSettings.getLaunchQueueName());
            keys.add(ServerSettings.getCancelQueueName());
            props.put(MessagingConstants.RABBIT_ROUTING_KEY, keys);
            props.put(MessagingConstants.RABBIT_QUEUE, ServerSettings.getLaunchQueueName());
            return props;
        }

        @Override
        public void onMessage(MessageContext message) {
            TaskSubmitEvent event = new TaskSubmitEvent();
            TBase messageEvent = message.getEvent();
            byte[] bytes = new byte[0];
            try {
                bytes = ThriftUtils.serializeThriftObject(messageEvent);
                ThriftUtils.createThriftFromBytes(bytes, event);
                System.out.println(event.getExperimentId());
            } catch (TException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private class TaskLaunchMessageHandler implements MessageHandler {
        private String experimentNode;
        private String nodeName;

        public TaskLaunchMessageHandler() throws ApplicationSettingsException {
            experimentNode = GFacConstants.ZOOKEEPER_EXPERIMENT_NODE;
            nodeName = ServerSettings.getGFacServerName();
        }

        public Map<String, Object> getProperties() {
            Map<String, Object> props = new HashMap<String, Object>();
            ArrayList<String> keys = new ArrayList<String>();
            keys.add(ServerSettings.getLaunchQueueName());
            keys.add(ServerSettings.getCancelQueueName());
            props.put(MessagingConstants.RABBIT_ROUTING_KEY, keys);
            props.put(MessagingConstants.RABBIT_QUEUE, ServerSettings.getLaunchQueueName());
            return props;
        }

        public void onMessage(MessageContext message) {
            System.out.println(" Message Received with message id '" + message.getMessageId()
                    + "' and with message type '" + message.getType());
            if (message.getType().equals(MessageType.LAUNCHTASK)) {
                try {
                    TaskSubmitEvent event = new TaskSubmitEvent();
                    TBase messageEvent = message.getEvent();
                    byte[] bytes = ThriftUtils.serializeThriftObject(messageEvent);
                    ThriftUtils.createThriftFromBytes(bytes, event);
                    // update experiment status to executing
                    ExperimentStatus status = new ExperimentStatus();
                    status.setExperimentState(ExperimentState.EXECUTING);
                    status.setTimeOfStateChange(Calendar.getInstance().getTimeInMillis());
                    experimentCatalog.update(ExperimentCatalogModelType.EXPERIMENT_STATUS, status, event.getExperimentId());
                    try {
                        GFacUtils.createExperimentEntryForPassive(event.getExperimentId(), event.getTaskId(), curatorClient,
                                experimentNode, nodeName, event.getTokenId(), message.getDeliveryTag());
                        AiravataZKUtils.getExpStatePath(event.getExperimentId());
                        submitJob(event.getExperimentId(), event.getTaskId(), event.getGatewayId(), event.getTokenId());
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        rabbitMQTaskLaunchConsumer.sendAck(message.getDeliveryTag());
                    }
                } catch (TException e) {
                    logger.error(e.getMessage(), e); //nobody is listening so nothing to throw
                } catch (RegistryException e) {
                    logger.error("Error while updating experiment status", e);
                }
            } else if (message.getType().equals(MessageType.TERMINATETASK)) {
                boolean cancelSuccess = false;
                TaskTerminateEvent event = new TaskTerminateEvent();
                TBase messageEvent = message.getEvent();
                try {
                    byte[] bytes = ThriftUtils.serializeThriftObject(messageEvent);
                    ThriftUtils.createThriftFromBytes(bytes, event);
                    boolean saveDeliveryTagSuccess = GFacUtils.setExperimentCancel(event.getExperimentId(), curatorClient, message.getDeliveryTag());
                    if (saveDeliveryTagSuccess) {
                        cancelSuccess = cancelJob(event.getExperimentId(), event.getTaskId(), event.getGatewayId(), event.getTokenId());
                        System.out.println(" Message Received with message id '" + message.getMessageId()
                                + "' and with message type '" + message.getType());
                    } else {
                        throw new GFacException("Terminate Task fail to save delivery tag : " + String.valueOf(message.getDeliveryTag()) + " \n" +
                                "This happens when another cancel operation is being processed or experiment is in one of final states, complete|failed|cancelled.");
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }finally {
                    if (cancelSuccess) {
                        // if cancel success , AiravataExperimentStatusUpdator will send an ack to this message.
                    } else {
                        try {
                            if (GFacUtils.ackCancelRequest(event.getExperimentId(), curatorClient)) {
                                if (!rabbitMQTaskLaunchConsumer.isOpen()) {
                                    rabbitMQTaskLaunchConsumer.reconnect();
                                }
                                rabbitMQTaskLaunchConsumer.sendAck(message.getDeliveryTag());
                            }
                        } catch (Exception e) {
                            logger.error("Error while ack to cancel request, experimentId: " + event.getExperimentId());
                        }
                    }
                }
            }
        }
    }
}

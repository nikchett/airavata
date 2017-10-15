package org.Notification.API;

import org.apache.thrift.transport.TServerSocket;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.apache.thrift.transport.TServerTransport;

import java.io.IOException;

import org.Notification.Authenticator.NotificationDetail;
import org.Notification.Sender.MailNotification;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;

public class NotifySubmissionServer {
	public static void StartsimpleServer(NotifySubmissionService.Processor<NotifySubmissionServerHandler> processor) {
//		  try {
//		   TServerTransport serverTransport = new TServerSocket(9091);
//		   TServer server = new TSimpleServer(
//		     new Args(serverTransport).processor(processor));
//
//		   // Use this for a multithreaded server
//		   // TServer server = new TThreadPoolServer(new
//		   // TThreadPoolServer.Args(serverTransport).processor(processor));
//
//		   System.out.println("Starting the simple server...");
//		   server.serve();
//		  } catch (Exception e) {
//		   e.printStackTrace();
//		  }
		try {
			   
		      //Create a connection factory
		      ConnectionFactory factory = new ConnectionFactory();
		      //Set the host to the location of the RabbitMQ server
		      factory.setHost("localHost");
		      //Open a new connection
		      Connection connection = factory.newConnection();
		      //Channel is the abstraction for interacting with a queue
		      Channel channel = connection.createChannel();
		      //It's possible the Queue doesn't already exist (a producer
		      //may not have been instantiated yet).  Create the queue if
		      //it doesn't exist
		      channel.queueDeclare("notify", false, false, false, null);
		      //Comfort logging
		      System.out.println(
		         " [*] Waiting for messages. To exit press CTRL+C");
		      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		      
		      String requestId = "";

		      Consumer consumer = new DefaultConsumer(channel) {
		        @Override
		        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		            throws IOException {
		          String message = new String(body, "UTF-8");
		          (new MailNotification()).sendMail(message, (new NotificationDetail()).getAdminId());
		          System.out.println(" [x] Received '" + message + "'");
		        }
		       
		      };
		      channel.basicConsume("notify", true, consumer);
		      
		       
		    } catch(Exception e){
		      //Dump any error to the console
		      e.printStackTrace();
		    }
		 }
		 
		 public static void main(String[] args) {
		  StartsimpleServer(new NotifySubmissionService.Processor<NotifySubmissionServerHandler>(new NotifySubmissionServerHandler()));
		 }

}

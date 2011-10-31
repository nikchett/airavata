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

package org.apache.airavata.xbaya.monitor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.apache.airavata.xbaya.XBayaEngine;
import org.apache.airavata.xbaya.event.Event;
import org.apache.airavata.xbaya.event.Event.Type;
import org.apache.airavata.xbaya.event.EventListener;
import org.apache.airavata.xbaya.gui.ErrorMessages;
import org.apache.airavata.xbaya.gui.XBayaGUI;
import org.apache.airavata.xbaya.modifier.gui.WorkflowModifierGUI;
import org.apache.airavata.xbaya.monitor.Monitor;
import org.apache.airavata.xbaya.monitor.MonitorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorMenu implements EventListener {

    private static final Logger logger = LoggerFactory.getLogger(MonitorMenu.class);

    private XBayaEngine engine;

    private XBayaGUI gui;

    private JMenu monitorMenu;

    private JMenuItem configMenuItem;

    private JMenuItem startMenuItem;

    private JMenuItem stopMenuItem;

    private JMenuItem resetMenuItem;

    private JMenuItem differenceMenuItem;

    /**
     * Constructs a MonitorMenu.
     * 
     * @param engine
     */
    public MonitorMenu(XBayaEngine engine) {
        this.engine = engine;
        this.gui = engine.getGUI();
        this.monitorMenu = createMonitorMenu();

        Monitor monitor = this.engine.getMonitor();
        monitor.addEventListener(this);
        monitor.getConfiguration().addEventListener(this);
    }

    /**
     * @return The JMenu.
     */
    public JMenu getMenu() {
        return this.monitorMenu;
    }

    /**
     * @see org.apache.airavata.xbaya.event.EventListener#eventReceived(org.apache.airavata.xbaya.event.Event)
     */
    @Override
    public void eventReceived(Event event) {
        Type type = event.getType();
        if (type.equals(Event.Type.MONITOR_CONFIGURATION_CHANGED)) {
            MonitorConfiguration configuration = this.engine.getMonitor().getConfiguration();
            boolean valid = configuration.isValid();
            this.startMenuItem.setEnabled(valid);
        } else if (type.equals(Event.Type.MONITOR_STARTED)) {
            this.startMenuItem.setEnabled(false);
            this.stopMenuItem.setEnabled(true);
            this.resetMenuItem.setEnabled(true);
        } else if (type.equals(Event.Type.MONITOR_STOPED)) {
            this.startMenuItem.setEnabled(true);
            this.stopMenuItem.setEnabled(false);
        }
    }

    private JMenu createMonitorMenu() {
        JMenu menu = new JMenu("Monitoring");
        menu.setMnemonic(KeyEvent.VK_M);

        this.configMenuItem = createConfigItem();
        this.startMenuItem = createStartItem();
        this.stopMenuItem = createStopItem();
        this.resetMenuItem = createResetItem();
        this.differenceMenuItem = createDifferenceItem();

        menu.add(this.configMenuItem);
        menu.addSeparator();
        menu.add(this.startMenuItem);
        menu.add(this.stopMenuItem);
        menu.add(this.resetMenuItem);
        menu.addSeparator();
        menu.addSeparator();
        menu.add(this.differenceMenuItem);
        return menu;
    }

    private JMenuItem createConfigItem() {
        JMenuItem item = new JMenuItem("Configure Monitoring");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new AbstractAction() {
            private MonitorConfigurationWindow window;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (this.window == null) {
                    this.window = new MonitorConfigurationWindow(MonitorMenu.this.engine);
                }
                this.window.show();
            }
        });
        return item;
    }

    private JMenuItem createStartItem() {
        JMenuItem item = new JMenuItem("Start Monitoring");
        item.setMnemonic(KeyEvent.VK_S);
        item.addActionListener(new AbstractAction() {
            private MonitorStarter starter;

            @Override
            public void actionPerformed(ActionEvent event) {
                if (this.starter == null) {
                    this.starter = new MonitorStarter(MonitorMenu.this.engine);
                }
                this.starter.start();
            }
        });
        boolean valid = this.engine.getMonitor().getConfiguration().isValid();
        item.setEnabled(valid);
        return item;
    }

    private JMenuItem createStopItem() {
        JMenuItem item = new JMenuItem("Stop Monitoring");
        item.setMnemonic(KeyEvent.VK_T);
        item.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    MonitorMenu.this.engine.getMonitor().asynchronousStop();
                } catch (RuntimeException e) {
                    MonitorMenu.this.gui.getErrorWindow().error(ErrorMessages.MONITOR_ERROR, e);
                } catch (Error e) {
                    MonitorMenu.this.gui.getErrorWindow().error(ErrorMessages.UNEXPECTED_ERROR, e);
                }
            }
        });
        item.setEnabled(false);
        return item;
    }

    private JMenuItem createResetItem() {
        JMenuItem item = new JMenuItem("Reset");
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    MonitorMenu.this.engine.getMonitor().reset();
                } catch (RuntimeException e) {
                    MonitorMenu.this.gui.getErrorWindow().error(ErrorMessages.MONITOR_ERROR, e);
                } catch (Error e) {
                    MonitorMenu.this.gui.getErrorWindow().error(ErrorMessages.UNEXPECTED_ERROR, e);
                }
            }
        });
        item.setEnabled(false);
        return item;
    }

    private JMenuItem createDifferenceItem() {
        JMenuItem item = new JMenuItem("Create Difference");
        item.setMnemonic(KeyEvent.VK_D);
        item.addActionListener(new AbstractAction() {
            private WorkflowModifierGUI modifierGUI;

            @Override
            public void actionPerformed(ActionEvent event) {
                if (this.modifierGUI == null) {
                    this.modifierGUI = new WorkflowModifierGUI(MonitorMenu.this.engine);
                }
                // Errors are handled inside.
                this.modifierGUI.createDifference();
            }
        });
        return item;
    }
}
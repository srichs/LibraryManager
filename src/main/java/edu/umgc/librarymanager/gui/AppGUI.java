/*
 * Filename: AppGUI.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import javax.swing.SwingUtilities;

/**
 * Entry to the GUI of the application.
 * @author Scott
 */
public class AppGUI {

    private GUIController controller;

    public AppGUI() {}

    public GUIController getController() {
        return this.controller;
    }

    /**
     * Initializes the application through the GUI controller.
     */
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                controller = new GUIController();
            }
        });
    }

}

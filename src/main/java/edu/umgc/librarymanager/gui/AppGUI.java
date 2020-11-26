/*
 * Filename: AppGUI.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import java.awt.Color;
import javax.swing.SwingUtilities;

/**
 * Entry to the GUI of the application.
 * @author Scott
 */
public class AppGUI {

    /**
     * The background color that is used throughout the application.
     */
    protected static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    /**
     * A String for the login panel.
     */
    protected static final String LOGIN = "LoginPanel";
    /**
     * A blank JPanel for testing.
     */
    protected static final String BLANK = "BlankPanel";

    private GUIController controller;

    public AppGUI() {
    }

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

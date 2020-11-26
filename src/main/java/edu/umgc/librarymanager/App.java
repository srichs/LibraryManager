/*
 * Filename: App.java
 * Author: Scott
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager;

import edu.umgc.librarymanager.gui.AppGUI;

/**
 * The main class of the application.
 * @author Scott
 */
public final class App {

    private App() {}

    /**
     * This is the main method for the Application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AppGUI app = new AppGUI();
        app.run();
        //DatabaseTest.runTest();
    }

}

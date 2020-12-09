/*
 * Filename: App.java
 * Author: Scott
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager;

import edu.umgc.librarymanager.data.DatabaseTest;
import edu.umgc.librarymanager.data.access.HibernateInit;
import edu.umgc.librarymanager.gui.GUIController;
import javax.swing.SwingUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main class of the application.
 * @author Scott
 */
public final class App {

    private static final Logger LOG = LogManager.getLogger(App.class);

    private App() {}

    /**
     * This is the main method for the Application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        LOG.info("\n" + DatabaseTest.getHeader());
        HibernateInit.databaseCheck(); // Checks if database file exists, creates it if not
        LOG.info("Application Started.");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LOG.info("GUI started.");
                GUIController controller = new GUIController();
                controller.start();
            }
        });
    }

}

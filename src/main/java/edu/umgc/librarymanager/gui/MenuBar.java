/*
 * Filename: MenuBar.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Creates the menu bar for the application. There are three types that can be
 * set: one for the Login Screen, one for the Librarian Screens, and one for
 * the Patron screens. An instance of the class is created in the MainFrame class.
 * @author Scott
 */
public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = -7787279708608831854L;

    /**
     * The constructor for the class.
     * @param control The GUIController to act as the listener.
     */
    public MenuBar(GUIController control) {
        super();
        setLoginMenuBar(control);
    }

    /**
     * Sets the Menu Bar for a Patron user.
     * @param control The GUIController to act as the listener.
     */
    public void setPatronMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        addMenuItem("Main Menu", fileMenu, Command.PATRON_MENU, control);
        addMenuItem("Search", fileMenu, Command.SEARCH, control);
        addMenuItem("View Profile", fileMenu, Command.PROFILE, control);
        addMenuItem("View Checked Items", fileMenu, Command.CHECKED_ITEMS, control);
        fileMenu.addSeparator();
        addMenuItem("Logout", fileMenu, Command.LOGOUT, control);
        JMenu helpMenu = new JMenu("Help");
        addMenuItem("About", helpMenu, Command.ABOUT, control);
        addMenuItem("Help", helpMenu, Command.HELP, control);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.removeAll();
        this.add(menuBar);
    }

    /**
     * Sets the Menu Bar for a Librarian user.
     * @param control The GUIController to act as the listener.
     */
    public void setLibrarianMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        addMenuItem("Main Menu", fileMenu, Command.LIBRARIAN_MENU, control);
        addMenuItem("Manage Users", fileMenu, Command.MANAGE_USERS, control);
        addMenuItem("Manage Items", fileMenu, Command.MANAGE_ITEMS, control);
        fileMenu.addSeparator();
        addMenuItem("Logout", fileMenu, Command.LOGOUT, control);
        JMenu helpMenu = new JMenu("Help");
        addMenuItem("About", helpMenu, Command.ABOUT, control);
        addMenuItem("Help", helpMenu, Command.HELP, control);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.removeAll();
        this.add(menuBar);
    }

    /**
     * Sets the Menu Bar for the Login screen.
     * @param control The GUIController to act as the listener.
     */
    public void setLoginMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        addMenuItem("About", helpMenu, Command.ABOUT, control);
        addMenuItem("Help", helpMenu, Command.HELP, control);
        menuBar.add(helpMenu);
        this.removeAll();
        this.add(menuBar);
    }

    // Used to add a menu item to a menu.
    private static void addMenuItem(String title, JMenu menu, String command, GUIController control) {
        JMenuItem item = new JMenuItem(title);
        item.setActionCommand(command);
        item.addActionListener((ActionListener) control);
        menu.add(item);
    }

}

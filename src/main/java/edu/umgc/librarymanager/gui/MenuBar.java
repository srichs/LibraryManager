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
 * Creates the menu bar for the application.
 * @author Scott
 */
public class MenuBar extends JMenuBar {

    private static final long serialVersionUID = -7787279708608831854L;

    public MenuBar(GUIController control) {
        super();
        setLoginMenuBar(control);
    }

    public void setPatronMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        addMenuItem("Main Menu", fileMenu, "patron_menu", control);
        addMenuItem("Search", fileMenu, "search", control);
        addMenuItem("View Profile", fileMenu, "profile", control);
        addMenuItem("View Checked Items", fileMenu, "checked_items", control);
        fileMenu.addSeparator();
        addMenuItem("Logout", fileMenu, "logout", control);
        JMenu helpMenu = new JMenu("Help");
        addMenuItem("About", helpMenu, "about", control);
        addMenuItem("Help", helpMenu, "help", control);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.removeAll();
        this.add(menuBar);
    }

    public void setLibrarianMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        addMenuItem("Main Menu", fileMenu, "librarian_menu", control);
        addMenuItem("View Profile", fileMenu, "profile", control);
        fileMenu.addSeparator();
        addMenuItem("Logout", fileMenu, "logout", control);
        JMenu helpMenu = new JMenu("Help");
        addMenuItem("About", helpMenu, "about", control);
        addMenuItem("Help", helpMenu, "help", control);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.removeAll();
        this.add(menuBar);
    }

    public void setLoginMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        addMenuItem("About", helpMenu, "about", control);
        addMenuItem("Help", helpMenu, "help", control);
        menuBar.add(helpMenu);
        this.removeAll();
        this.add(menuBar);
    }

    private static void addMenuItem(String title, JMenu menu, String command, GUIController control) {
        JMenuItem item = new JMenuItem(title);
        item.setActionCommand(command);
        item.addActionListener((ActionListener) control);
        menu.add(item);
    }

}

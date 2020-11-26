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
        createMenuBar(control);
    }

    private void createMenuBar(GUIController control) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        newItem.setActionCommand("new");
        newItem.addActionListener((ActionListener) control);
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.setActionCommand("logout");
        logoutItem.addActionListener((ActionListener) control);
        fileMenu.add(logoutItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.setActionCommand("about");
        about.addActionListener((ActionListener) control);
        JMenuItem help = new JMenuItem("Help");
        help.setActionCommand("help");
        help.addActionListener((ActionListener) control);
        helpMenu.add(about);
        helpMenu.add(help);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.add(menuBar);
    }

}

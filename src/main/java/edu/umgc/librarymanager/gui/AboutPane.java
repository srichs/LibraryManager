/*
 * Filename: AboutPane.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is used to display the About Dialog.
 */
public final class AboutPane {

    private AboutPane() {}

    public static void showAboutPane() {
        JOptionPane.showMessageDialog(new JFrame(), getAboutString(), "About", JOptionPane.PLAIN_MESSAGE);
    }

    private static String getAboutString() {
        String space = "                      ";
        StringBuilder sb = new StringBuilder();
        sb.append("Library Management System\n");
        sb.append("Version: 0.1.0\n");
        sb.append("November 2020\n\n");
        sb.append("Description: Used to manage the items and services of a library.\n\n");
        sb.append("Created By: Cindy Reynolds\n");
        sb.append(space);
        sb.append("David Koo\n");
        sb.append(space);
        sb.append("Scott Richards\n");
        sb.append(space);
        sb.append("Jonathan Dreksler\n\n");
        return sb.toString();
    }

}

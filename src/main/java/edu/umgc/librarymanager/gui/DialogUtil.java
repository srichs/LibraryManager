/*
 * Filename: DialogHelper.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is a utility class to make it easier to display JOptionPanes.
 * @author Scott
 */
public final class DialogUtil {

    private DialogUtil() {}

    public static void informationMessage(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warningMessage(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void errorMessage(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

}

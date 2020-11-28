/*
 * Filename: PatronMenuPanel.java
 * Author: Scott
 * Date Created: 11/27/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.gui.GUIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to create a Patron Menu panel.
 * @author Scott
 */
public class PatronMenuPanel extends JPanel {

    private static final long serialVersionUID = 2748070073469141467L;

    public PatronMenuPanel(GUIController control) {
        super();
        createPanel(control);
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 400));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        addButton("Search for an Item", buttonPanel, "search", control);
        addButton("View Checked Out Items", buttonPanel, "checked_items", control);
        addButton("View User Profile", buttonPanel, "profile", control);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

    private static void addButton(String text, Container container, String command, GUIController control) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setActionCommand(command);
        button.addActionListener((ActionListener) control);
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(10, 10)));
    }

}

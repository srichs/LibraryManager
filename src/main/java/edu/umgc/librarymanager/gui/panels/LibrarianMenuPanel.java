/*
 * Filename: LibrarianMenuPanel.java
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
 * This class is used to display the menu for Librarian.
 * @author Scott
 */
public class LibrarianMenuPanel extends JPanel {

    private static final long serialVersionUID = 7886830845472408459L;

    public LibrarianMenuPanel(GUIController control) {
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

        addButton("Add User", buttonPanel, "add_user", control);
        addButton("Remove User", buttonPanel, "remove_user", control);
        addButton("Add Item", buttonPanel, "add_item", control);
        addButton("Remove Item", buttonPanel, "remove_item", control);
        addButton("Checkout Item", buttonPanel, "checkout_item", control);
        addButton("Return Item", buttonPanel, "return_item", control);

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

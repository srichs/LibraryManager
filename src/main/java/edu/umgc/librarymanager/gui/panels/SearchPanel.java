/*
 * Filename: SearchPanel.java
 * Author: Scott
 * Date Created: 11/27/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.GUIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to create a Search panel.
 * @author Scott
 */
public class SearchPanel extends JPanel {

    private static final long serialVersionUID = -5577134853276029920L;

    private JTextField searchField;
    private JLabel welcomeLabel;
    private String firstName;

    /**
     * The constructor for the class.
     * @param control The GUIController of the application.
     */
    public SearchPanel(GUIController control) {
        super();
        this.searchField = new JTextField(40);
        this.welcomeLabel = new JLabel("Welcome");
        this.firstName = "";
        createPanel(control);
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 400));
        mainPanel.setBorder(new EmptyBorder(80, 20, 20, 20));
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.welcomeLabel.setSize(new Dimension(300, 80));
        this.welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.welcomeLabel.setFont(new Font(this.welcomeLabel.getName(), Font.BOLD, 24));

        searchPanel.add(this.welcomeLabel);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        JLabel subLabel = new JLabel("Search for an item below");
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(subLabel);
        searchPanel.add(Box.createRigidArea(new Dimension(30, 30)));
        searchPanel.add(this.searchField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton searchButton = new JButton("Search");
        searchButton.setActionCommand(Command.SEARCH_PRESS);
        searchButton.addActionListener((ActionListener) control);
        JButton advSearchButton = new JButton("Advanced Search");
        advSearchButton.setActionCommand(Command.ADV_SEARCH_PRESS);
        advSearchButton.addActionListener((ActionListener) control);

        buttonPanel.add(searchButton);
        buttonPanel.add(advSearchButton);
        searchPanel.add(buttonPanel);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Resets the label, should be called if this class's first name variable changes.
     */
    public void resetLabel() {
        if ("".equals(this.firstName)) {
            this.welcomeLabel.setText("Welcome");
        } else {
            this.welcomeLabel.setText("Welcome, " + this.firstName);
        }
        this.repaint();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        resetLabel();
    }

    public void reset() {
        this.searchField.setText("");
    }

}

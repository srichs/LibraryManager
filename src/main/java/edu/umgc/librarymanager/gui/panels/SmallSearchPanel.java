/*
 * Filename: SmallSearchPanel.java
 * Author: Scott
 * Date Created: 12/5/2020
 */

package edu.umgc.librarymanager.gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to create a small panel that can be attached the top of panel for searching the data.
 * @author Scott
 */
public class SmallSearchPanel extends JPanel {

    private static final long serialVersionUID = -869442281157952188L;
    /**
     * The action command String for a press of the search button.
     */
    public static final String SEARCH_PRESS = "small_search_press";
    /**
     * The action command String for a press of the clear button.
     */
    public static final String CLEAR_PRESS = "small_clear_press";

    private JTextField searchField;
    private JLabel searchLabel;
    private ActionListener actionListener;

    /**
     * The constructor of the class.
     * @param searchLabel The String to set the label for the search bar.
     * @param listener The ActionListener for the buttons on the panel.
     */
    public SmallSearchPanel(String searchLabel, ActionListener listener) {
        this.searchField = new JTextField(28);
        this.searchLabel = new JLabel(searchLabel);
        this.actionListener = listener;
        createPanel();
    }

    private void createPanel() {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(880, 60));
        this.setMinimumSize(new Dimension(880, 60));
        this.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this.actionListener);
        searchButton.setActionCommand(SEARCH_PRESS);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this.actionListener);
        clearButton.setActionCommand(CLEAR_PRESS);
        this.add(this.searchLabel);
        this.add(this.searchField);
        this.add(searchButton);
        this.add(clearButton);
        this.add(Box.createRigidArea(new Dimension(100, 40)));
    }

}

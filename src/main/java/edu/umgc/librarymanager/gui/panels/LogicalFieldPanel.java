/*
 * Filename: LogicalFieldPanel.java
 * Author: Scott
 * Date Created: 12/9/2020
 */

package edu.umgc.librarymanager.gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import edu.umgc.librarymanager.data.access.ItemField;
import edu.umgc.librarymanager.data.access.LogicalType;

/**
 * This class is used to create a panel with comboboxes and a field for the advanced search panel.
 * @author Scott
 */
public class LogicalFieldPanel extends JPanel {

    private static final long serialVersionUID = -1295997817305332454L;

    private JPanel mainPanel;
    private JComboBox<LogicalType> logicBox;
    private JComboBox<String> fieldBox;
    private JTextField searchField;

    /**
     * The default constructor of the class.
     */
    public LogicalFieldPanel() {
        this.mainPanel = new JPanel(new GridBagLayout());
        this.logicBox = new JComboBox<LogicalType>(LogicalType.values());
        this.fieldBox = new JComboBox<String>();
        fillFieldBox();
        this.searchField = new JTextField(40);
        createPanel();
    }

    private void fillFieldBox() {
        for (ItemField field : ItemField.values()) {
            this.fieldBox.addItem(field.label);
        }
    }

    private void createPanel() {
        mainPanel.setSize(new Dimension(880, 40));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;

        c.gridx = 0;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.gridy = 0;
        mainPanel.add(this.logicBox, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.weightx = 0.3;
        c.gridwidth = 1;
        c.gridy = 0;
        mainPanel.add(this.fieldBox, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.weightx = 0.6;
        c.gridwidth = 2;
        c.gridy = 0;
        mainPanel.add(this.searchField, c);

        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

    public JComboBox<LogicalType> getLogicBox() {
        return this.logicBox;
    }

    public void setLogicBox(JComboBox<LogicalType> logicBox) {
        this.logicBox = logicBox;
    }

    public JComboBox<String> getFieldBox() {
        return this.fieldBox;
    }

    public ItemField getFieldBoxValue() {
        return ItemField.valueOfLabel((String) this.fieldBox.getSelectedItem());
    }

    public void setFieldBox(JComboBox<String> fieldBox) {
        this.fieldBox = fieldBox;
    }

    public JTextField getSearchField() {
        return this.searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public void hideLogicBox() {
        this.logicBox.setVisible(false);
        JLabel findLabel = new JLabel("Find");
        findLabel.setPreferredSize(new Dimension(40, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.gridy = 0;
        mainPanel.add(findLabel, c);
    }

    public void reset() {
        this.logicBox.setSelectedIndex(0);
        this.fieldBox.setSelectedIndex(0);
        this.searchField.setText("");
        repaint();
    }

}
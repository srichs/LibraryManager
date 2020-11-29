/*
 * Filename: LabelFieldPanel.java
 * Author: Scott
 * Date Created: 11/27/2020
 */

package edu.umgc.librarymanager.gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is used to create a panel with a label and textfield.
 * @author Scott
 */
public class LabelFieldPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel label;
    private JTextField textField;

    /**
     * The default constructor of the class.
     */
    public LabelFieldPanel() {
        this.label = new JLabel();
        this.label.setPreferredSize(new Dimension(100, 20));
        this.textField = new JTextField(36);
        createPanel();
    }

    /**
     * The constructor of the class.
     * @param label The text to set the label to.
     */
    public LabelFieldPanel(String label) {
        this.label = new JLabel(label);
        this.label.setPreferredSize(new Dimension(100, 20));
        this.textField = new JTextField(36);
        createPanel();
    }

    /**
     * The constructor of the class it takes a parameter for each field.
     * @param label The text to set the label to.
     * @param text The text to set the text field to.
     */
    public LabelFieldPanel(String label, String text) {
        this.label = new JLabel(label);
        this.label.setPreferredSize(new Dimension(100, 20));
        this.textField = new JTextField(text, 36);
        createPanel();
    }

    private void createPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setSize(new Dimension(400, 60));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;

        c.gridx = 0;
        c.weightx = 0.2;
        c.gridwidth = 1;
        c.gridy = 0;
        mainPanel.add(this.label, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.weightx = 0.8;
        c.gridwidth = 2;
        c.gridy = 0;
        mainPanel.add(this.textField, c);

        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public JTextField getTextField() {
        return this.textField;
    }

    public String getText() {
        return this.textField.getText();
    }

    public void setText(String text) {
        this.textField.setText(text);
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void setFieldLength(int value) {
        this.textField.setColumns(value);
    }

}

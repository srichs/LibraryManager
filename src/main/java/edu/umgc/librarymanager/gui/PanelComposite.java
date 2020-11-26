/*
 * Filename: PanelComposite.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is a composite class containing the panels of the application.
 * @author Scott
 */
public class PanelComposite {

    private LoginPanel loginPanel;
    private JPanel blankPanel;

    /**
     * The constructor of the class.
     * @param control A GUIController Object that is the ActionListener.
     */
    public PanelComposite(GUIController control) {
        this.loginPanel = new LoginPanel(control);
        this.blankPanel = new JPanel();
        this.blankPanel.add(new JLabel("You are logged in."));
    }

    public LoginPanel getLoginPanel() {
        return this.loginPanel;
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public JPanel getBlankPanel() {
        return this.blankPanel;
    }

    public void setBlankPanel(JPanel blankPanel) {
        this.blankPanel = blankPanel;
    }

}

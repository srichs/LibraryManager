/*
 * Filename: GUIController.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to control the GUI of the application.
 * @author Scott
 */
public class GUIController implements ActionListener {

    private MainFrame frame;
    private BaseUser currentUser;

    /**
     * The default constructor for the class.
     */
    public GUIController() {
        this.frame = new MainFrame(this);
        this.frame.setTitle("Library Management System");
    }

    public MainFrame getFrame() {
        return this.frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public BaseUser getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(BaseUser currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Checks if there is a logged in user.
     * @return A boolean value for if there is a logged in user.
     */
    public boolean isUserLoggedIn() {
        if (currentUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("login".equals(e.getActionCommand())) {
            BaseUser user = frame.getPanelComp().getLoginPanel().tryLogin();
            if (user != null) {
                currentUser = user;
                frame.getLayout().show(frame.getPanels(), AppGUI.BLANK);
            }
        } else if ("logout".equals(e.getActionCommand())) {
            if (currentUser != null) {
                String username = currentUser.getUserName();
                currentUser = null;
                frame.getLayout().show(frame.getPanels(), AppGUI.LOGIN);
                DialogUtil.informationMessage(username + " has been logged out.", "Logged Out");
            }
        } else if ("new".equals(e.getActionCommand())) {
            DialogUtil.informationMessage("This is a placeholder.", "Nothing"); // TODO
        } else if ("about".equals(e.getActionCommand())) {
            AboutPane.showAboutPane();
        } else if ("help".equals(e.getActionCommand())) {
            DialogUtil.informationMessage("Help not yet configured.", "Help"); // TODO
        }
    }

}

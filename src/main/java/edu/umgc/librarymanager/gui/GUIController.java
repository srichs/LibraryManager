/*
 * Filename: GUIController.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This class is used to control the GUI of the application.
 * @author Scott
 */
public class GUIController implements ActionListener {

    private MainFrame frame;
    private BaseUser currentUser;
    private InactivityListener listener;
    private Action logoutAction;

    /**
     * The default constructor for the class.
     */
    public GUIController() {
        this.frame = new MainFrame(this);
        this.frame.setTitle("Library Management System");
        this.listener = null;
        logoutAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
                logout(true);
            }
        };
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

    private void login() {
        BaseUser user = frame.getPanelComp().getLoginPanel().tryLogin();
        if (user != null) {
            currentUser = user;
            frame.getLayout().show(frame.getPanels(), AppGUI.BLANK);
            listener = new InactivityListener(frame, logoutAction, 4);
            listener.start();
        }
    }

    private void logout(boolean inactivity) {
        if (currentUser != null) {
            String username = currentUser.getUserName();
            currentUser = null;
            frame.getLayout().show(frame.getPanels(), AppGUI.LOGIN);
            if (inactivity) {
                DialogUtil.informationMessage(username + " has been logged out due to inactivity.",
                        "Logged Out");
            } else {
                DialogUtil.informationMessage(username + " has been logged out.", "Logged Out");
            }
            listener.stop();
            listener = null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("login".equals(e.getActionCommand())) {
            login();
        } else if ("logout".equals(e.getActionCommand())) {
            logout(false);
        } else if ("new".equals(e.getActionCommand())) {
            DialogUtil.informationMessage("This is a placeholder.", "Nothing"); // TODO
        } else if ("about".equals(e.getActionCommand())) {
            AboutPane.showAboutPane();
        } else if ("help".equals(e.getActionCommand())) {
            DialogUtil.informationMessage("Help not yet configured.", "Help"); // TODO
        }
    }

}

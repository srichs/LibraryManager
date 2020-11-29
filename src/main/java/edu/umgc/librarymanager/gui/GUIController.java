/*
 * Filename: GUIController.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import edu.umgc.librarymanager.service.ControlHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to control the GUI of the application. It implements the ActionListener
 * interface and contains the actionPerformed method that controls the actions of the application.
 * This class also contains an Inactivity Listener that listens to the frame for user inactivity
 * and logs the user out after a certain period.
 * @author Scott
 */
public class GUIController implements ActionListener {

    private static final Logger LOG = LogManager.getLogger(GUIController.class);

    private MainFrame frame;
    private BaseUser currentUser;
    private InactivityListener inactiveListener;
    private Action logoutAction;
    private HashMap<String, BaseUser> logins;

    /**
     * The default constructor for the class.
     */
    public GUIController() {
        this.frame = null;
        this.currentUser = null;
        this.inactiveListener = null;
        this.logoutAction = null;
    }

    // Handles the control of the action commands of the application.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Command.LOGIN.equals(e.getActionCommand())) {
            LOG.info("Login button pressed.");
            ControlHelper.login(this);
        } else if (Command.LOGOUT.equals(e.getActionCommand())) {
            LOG.info("Logout button pressed.");
            ControlHelper.logout(false, this);
        } else if (Command.ABOUT.equals(e.getActionCommand())) {
            LOG.info("About button pressed.");
            AboutPane.showAboutPane();
        } else if (Command.HELP.equals(e.getActionCommand())) {
            LOG.info("Help button pressed.");
            DialogUtil.informationMessage("Help not yet configured.", "Help"); // TODO
        } else if (Command.ADD_USER.equals(e.getActionCommand())) {
            LOG.info("Add User Panel button pressed.");
            ControlHelper.addUser(this.frame);
        } else if (Command.CREATE_USER.equals(e.getActionCommand())) {
            LOG.info("Add User button pressed.");
            ControlHelper.createUser(this);
        } else if (Command.REMOVE_USER.equals(e.getActionCommand())) {
            LOG.info("Remove User button pressed.");
            DialogUtil.informationMessage("Remove user not yet configured.", "Remove User"); // TODO
        } else if (Command.UPDATE_USER.equals(e.getActionCommand())) {
            LOG.info("Update User button pressed.");
            ControlHelper.updateUser(this);
        } else if (Command.ADD_ITEM.equals(e.getActionCommand())) {
            LOG.info("Add Item button pressed.");
            DialogUtil.informationMessage("Add item not yet configured.", "Add Item"); // TODO
        } else if (Command.REMOVE_ITEM.equals(e.getActionCommand())) {
            LOG.info("Remove Item button pressed.");
            DialogUtil.informationMessage("Remove item not yet configured.", "Remove Item"); // TODO
        } else if (Command.CHECKOUT_ITEM.equals(e.getActionCommand())) {
            LOG.info("Checkout Item button pressed.");
            DialogUtil.informationMessage("Checkout item not yet configured.", "Checkout Item"); // TODO
        } else if (Command.RETURN_ITEM.equals(e.getActionCommand())) {
            LOG.info("Return Item button pressed.");
            DialogUtil.informationMessage("Return item not yet configured.", "Return Item"); // TODO
        } else if (Command.SEARCH.equals(e.getActionCommand())) {
            LOG.info("Search button pressed.");
            DialogUtil.informationMessage("Search not yet configured.", "Search"); // TODO
        } else if (Command.CHECKED_ITEMS.equals(e.getActionCommand())) {
            LOG.info("View Checked Items button pressed.");
            DialogUtil.informationMessage("Checked items not yet configured.", "Checked Items"); // TODO
        } else if (Command.PROFILE.equals(e.getActionCommand())) {
            LOG.info("View Profile button pressed.");
            ControlHelper.viewProfile(this);
        } else if (Command.LIBRARIAN_MENU.equals(e.getActionCommand())) {
            LOG.info("Librarian Menu displayed.");
            this.frame.getLayout().show(this.frame.getPanels(), PanelComposite.LIBRARIAN_MENU);
        } else if (Command.PATRON_MENU.equals(e.getActionCommand())) {
            LOG.info("Patron Menu displayed.");
            this.frame.getLayout().show(this.frame.getPanels(), PanelComposite.PATRON_MENU);
        } else if (Command.MANAGE_USERS.equals(e.getActionCommand())) {
            LOG.info("Manage Users panel displayed.");
            ControlHelper.viewManageUsers(this.frame);
        } else if (Command.MANAGE_UPDATE_USER.equals(e.getActionCommand())) {
            LOG.info("Update User button pressed.");
            ControlHelper.manageUpdateUser(this);
        }
    }

    /**
     * Starts the GUI and initializes other control items.
     */
    public void start() {
        LOG.info("GUIController started.");
        this.frame = new MainFrame(this);
        this.frame.setTitle("Library Management System");
        this.inactiveListener = null;
        this.logins = ControlHelper.getLoginCredentials();
        logoutAction = new AbstractAction() { // Action to be performed due to user inactivity
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
                ControlHelper.logout(true, getController());
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

    public InactivityListener getInactiveListener() {
        return this.inactiveListener;
    }

    public void setInactiveListener(InactivityListener inactiveListener) {
        this.inactiveListener = inactiveListener;
    }

    public Action getLogoutAction() {
        return this.logoutAction;
    }

    public HashMap<String, BaseUser> getLogins() {
        return this.logins;
    }

    public void setLogins(HashMap<String, BaseUser> logins) {
        this.logins = logins;
    }

    public GUIController getController() {
        return this;
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

}

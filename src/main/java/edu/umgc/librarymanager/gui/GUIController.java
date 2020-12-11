/*
 * Filename: GUIController.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import edu.umgc.librarymanager.service.CommonServices;
import edu.umgc.librarymanager.service.LibrarianServices;
import edu.umgc.librarymanager.service.PatronServices;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /**
     * The default constructor for the class.
     */
    public GUIController() {
        this.frame = null;
        this.currentUser = null;
        this.inactiveListener = null;
        this.logoutAction = null;
    }

    // Handles the control of the action commands of the application. Organized with Common services at the
    // top, then Librarian services and then Patron services at the bottom.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Command.LOGIN.equals(e.getActionCommand())) { // COMMON
            LOG.info("Login button pressed.");
            CommonServices.login(this);
        } else if (Command.LOGOUT.equals(e.getActionCommand())) {
            LOG.info("Logout button pressed.");
            CommonServices.logout(false, this);
        } else if (Command.ABOUT.equals(e.getActionCommand())) {
            LOG.info("About button pressed.");
            AboutPane.showAboutPane();
        } else if (Command.HELP.equals(e.getActionCommand())) {
            LOG.info("Help button pressed.");
            DialogUtil.informationMessage("Help not yet configured.", "Help"); // TODO add help information
        } else if (Command.LIBRARIAN_MENU.equals(e.getActionCommand())) { // LIBRARIAN
            LOG.info("Librarian Menu displayed.");
            this.frame.getLayout().show(this.frame.getPanels(), PanelComposite.LIBRARIAN_MENU);
        } else if (Command.ADD_USER.equals(e.getActionCommand())) {
            LOG.info("Add User Panel button pressed.");
            LibrarianServices.addUser(this.frame);
        } else if (Command.CREATE_USER.equals(e.getActionCommand())) {
            LOG.info("Add User button pressed.");
            LibrarianServices.createUser(this);
        } else if (Command.UPDATE_USER.equals(e.getActionCommand())) {
            LOG.info("Update User button pressed.");
            LibrarianServices.updateUser(this);
        } else if (Command.CREATE_ITEM.equals(e.getActionCommand())) {
            LOG.info("Add User button pressed.");
            LibrarianServices.createItem(this);
        } else if (Command.ADD_ITEM.equals(e.getActionCommand())) {
            LOG.info("Add Item button pressed.");
            LibrarianServices.addItem(this.frame);
        } else if (Command.CHECKOUT_ITEM.equals(e.getActionCommand())) {
            LOG.info("Checkout Item button pressed.");
            LibrarianServices.viewManageItems(this.frame, Command.CHECKOUT_ITEM);
        } else if (Command.RETURN_ITEM.equals(e.getActionCommand())) {
            LOG.info("Return Item button pressed.");
            LibrarianServices.viewManageItems(this.frame, Command.RETURN_ITEM);
        } else if (Command.ITEM_RETURNED.equals(e.getActionCommand())) {
            LOG.info("Returned button pressed.");
            //PatronServices.renewItem(this); // TODO
        } else if (Command.MANAGE_USERS.equals(e.getActionCommand())) {
            LOG.info("Manage Users panel displayed.");
            LibrarianServices.viewManageUsers(this.frame);
        } else if (Command.MANAGE_UPDATE_USER.equals(e.getActionCommand())) {
            LOG.info("Update User button pressed.");
            LibrarianServices.manageUpdateUser(this);
        } else if (Command.MANAGE_ITEMS.equals(e.getActionCommand())) {
            LOG.info("Manage Items panel displayed.");
            LibrarianServices.viewManageItems(this.frame, Command.MANAGE_ITEMS);
        } else if (Command.MANAGE_UPDATE_ITEM.equals(e.getActionCommand())) {
            LOG.info("Update Item button pressed.");
            LibrarianServices.manageUpdateItem(this);
        } else if (Command.PATRON_MENU.equals(e.getActionCommand())) { // PATRON // TODO remove? use search as main.
            LOG.info("Patron Menu displayed.");
            this.frame.getLayout().show(this.frame.getPanels(), PanelComposite.PATRON_MENU);
        } else if (Command.SEARCH.equals(e.getActionCommand())) {
            LOG.info("Search panel button pressed.");
            PatronServices.viewSearch(this);
        } else if (Command.CHECKED_ITEMS.equals(e.getActionCommand())) {
            LOG.info("View Checked Items button pressed.");
            PatronServices.viewCheckedOutItems(this);
        } else if (Command.PROFILE.equals(e.getActionCommand())) {
            LOG.info("View Profile button pressed.");
            PatronServices.viewProfile(this);
        } else if (Command.SEARCH_PRESS.equals(e.getActionCommand())) {
            LOG.info("Search button pressed.");
            PatronServices.viewSearchResults(this);
        } else if (Command.ADV_SEARCH_PRESS.equals(e.getActionCommand())) {
            LOG.info("Advanced search button pressed.");
            PatronServices.viewAdvancedSearch(this);
        } else if (Command.ADVANCED_SEARCH.equals(e.getActionCommand())) {
            LOG.info("Advanced search button pressed.");
            PatronServices.viewAdvancedSearchResults(this);
        } else if (Command.ADV_SEARCH_CLEAR.equals(e.getActionCommand())) {
            LOG.info("Advanced search clear button pressed.");
            PatronServices.clearAdvancedSearch(this);
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
        logoutAction = new AbstractAction() { // Action to be performed due to user inactivity
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
                CommonServices.logout(true, getController());
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

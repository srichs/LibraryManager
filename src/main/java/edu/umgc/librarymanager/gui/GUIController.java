/*
 * Filename: GUIController.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
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

    /**
     * Starts the GUI.
     */
    public void start() {
        LOG.info("GUIController started.");
        this.frame = new MainFrame(this);
        this.frame.setTitle("Library Management System");
        this.inactiveListener = null;
        logoutAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
                logout(true);
            }
        };
    }

    private void login() {
        BaseUser user = frame.getPanelComp().getLoginPanel().tryLogin();
        if (user != null) {
            currentUser = user;
            if (currentUser.getUserType() == UserType.Librarian) {
                frame.getTheMenuBar().setLibrarianMenuBar(this);
                frame.getLayout().show(frame.getPanels(), PanelComposite.LIBRARIAN_MENU);
            } else {
                frame.getTheMenuBar().setPatronMenuBar(this);
                frame.getLayout().show(frame.getPanels(), PanelComposite.PATRON_MENU);
            }
            inactiveListener = new InactivityListener(frame, logoutAction, 5); // user is logged out after 5 min
            inactiveListener.start();
        }
    }

    private void logout(boolean inactivity) {
        if (currentUser != null) {
            currentUser = null;
            frame.getTheMenuBar().setLoginMenuBar(this);
            frame.getLayout().show(frame.getPanels(), PanelComposite.LOGIN);
            if (inactivity) {
                DialogUtil.informationMessage("user was logged out due to inactivity.",
                        "Logged Out");
            }
            inactiveListener.stop();
            inactiveListener = null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("login".equals(e.getActionCommand())) {
            LOG.info("Login button pressed.");
            login();
        } else if ("logout".equals(e.getActionCommand())) {
            LOG.info("Logout button pressed.");
            logout(false);
        } else if ("about".equals(e.getActionCommand())) {
            LOG.info("About button pressed.");
            AboutPane.showAboutPane();
        } else if ("help".equals(e.getActionCommand())) {
            LOG.info("Help button pressed.");
            DialogUtil.informationMessage("Help not yet configured.", "Help"); // TODO
        } else if ("add_user".equals(e.getActionCommand())) {
            LOG.info("Add User Panel button pressed.");
            ControlHelper.addUser(this.frame);
        } else if ("create_user".equals(e.getActionCommand())) {
            LOG.info("Add User button pressed.");
            ControlHelper.createUser(this.frame);
        } else if ("remove_user".equals(e.getActionCommand())) {
            LOG.info("Remove User button pressed.");
            DialogUtil.informationMessage("Remove user not yet configured.", "Remove User"); // TODO
        } else if ("update_user".equals(e.getActionCommand())) {
            LOG.info("Update User button pressed.");
            BaseUser user = ControlHelper.updateUser(this.frame);
            if (user != null) {
                currentUser = user;
            }
        } else if ("add_item".equals(e.getActionCommand())) {
            LOG.info("Add Item button pressed.");
            DialogUtil.informationMessage("Add item not yet configured.", "Add Item"); // TODO
        } else if ("remove_item".equals(e.getActionCommand())) {
            LOG.info("Remove Item button pressed.");
            DialogUtil.informationMessage("Remove item not yet configured.", "Remove Item"); // TODO
        } else if ("checkout_item".equals(e.getActionCommand())) {
            LOG.info("Checkout Item button pressed.");
            DialogUtil.informationMessage("Checkout item not yet configured.", "Checkout Item"); // TODO
        } else if ("return_item".equals(e.getActionCommand())) {
            LOG.info("Return Item button pressed.");
            DialogUtil.informationMessage("Return item not yet configured.", "Return Item"); // TODO
        } else if ("search".equals(e.getActionCommand())) {
            LOG.info("Search button pressed.");
            DialogUtil.informationMessage("Search not yet configured.", "Search"); // TODO
        } else if ("checked_items".equals(e.getActionCommand())) {
            LOG.info("View Checked Items button pressed.");
            DialogUtil.informationMessage("Checked items not yet configured.", "Checked Items"); // TODO
        } else if ("profile".equals(e.getActionCommand())) {
            LOG.info("View Profile button pressed.");
            ControlHelper.viewProfile(this.frame, this.currentUser);
        } else if ("librarian_menu".equals(e.getActionCommand())) {
            LOG.info("Librarian Menu displayed.");
            frame.getLayout().show(frame.getPanels(), PanelComposite.LIBRARIAN_MENU);
        } else if ("patron_menu".equals(e.getActionCommand())) {
            LOG.info("Patron Menu displayed.");
            frame.getLayout().show(frame.getPanels(), PanelComposite.PATRON_MENU);
        }
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

}

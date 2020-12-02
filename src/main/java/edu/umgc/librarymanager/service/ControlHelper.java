/*
 * Filename: ControlHelper.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.data.access.ItemDAO;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.gui.InactivityListener;
import edu.umgc.librarymanager.gui.MainFrame;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to provide helper methods for the GUIController class.
 * @author Scott
 */
public final class ControlHelper {

    private static final Logger LOG = LogManager.getLogger(GUIController.class);

    private ControlHelper() {}

    /**
     * This method is called when the login button is pressed.
     * @param control The GUIController of the application.
     */
    public static void login(GUIController control) {
        String username = control.getFrame().getPanelComp().getLoginPanel().getUsernameField().getText();
        HashMap<String, BaseUser> map = control.getLogins();
        BaseUser user = null;
        if (map.containsKey(username)) {
            if (map.get(username).getLogin().checkPassword(control.getFrame().getPanelComp().getLoginPanel()
                    .getPasswordField().getPassword())) {
                control.getFrame().getPanelComp().getLoginPanel().clearFields();
                user = map.get(username);
            } else {
                DialogUtil.warningMessage("Incorrect password. Access denied.", "Login Failure");
            }
        } else {
            DialogUtil.warningMessage("The username was not recognized.", "Login Failure");
        }
        if (user != null) {
            control.setCurrentUser(user);
            LOG.info(user.getUserName() + " has logged on.");
            if (control.getCurrentUser().getUserType() == UserType.Librarian) {
                control.getFrame().getTheMenuBar().setLibrarianMenuBar(control);
                control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.LIBRARIAN_MENU);
            } else {
                control.getFrame().getTheMenuBar().setPatronMenuBar(control);
                control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.PATRON_MENU);
            }
            control.getFrame().repaint();
            // user is logged out after 5 min of inactivity
            control.setInactiveListener(new InactivityListener(control.getFrame(), control.getLogoutAction(), 5));
            control.getInactiveListener().start();
        }
    }

    /**
     * Performs the logout action of the user when the logout button is clicked or from inactivity.
     * @param inactivity A boolean value for whether the logout is being done because of inactivity.
     * @param control The GUIController for the application.
     */
    public static void logout(boolean inactivity, GUIController control) {
        if (control.getCurrentUser() != null) {
            control.getFrame().getTheMenuBar().setLoginMenuBar(control);
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.LOGIN);
            control.getFrame().repaint();
            LOG.info(control.getCurrentUser().getUserName() + " logged out.");
            if (inactivity) {
                LOG.info(control.getCurrentUser().getUserName() + " was logged out due to inactivity.");
                DialogUtil.informationMessage("user was logged out due to inactivity.",
                        "Logged Out");
            }
            control.setCurrentUser(null);
            control.getInactiveListener().stop();
            control.setInactiveListener(null);
        }
    }

    public static void viewProfile(GUIController control) {
        control.getFrame().getPanelComp().getUserProfilePanel().setUser(control.getCurrentUser());
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.PROFILE);
    }

    public static void addUser(MainFrame frame) {
        frame.getPanelComp().getAddUserPanel().setNew();
        frame.getLayout().show(frame.getPanels(), PanelComposite.ADD_USER);
    }

    /**
     * This method is used to create a user from the information in the Add User panel.
     * @param control The GUIController of the application.
     */
    public static void createUser(GUIController control) {
        BaseUser user = control.getFrame().getPanelComp().getAddUserPanel().tryCreate(control.getLogins());
        if (user != null) {
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.openSessionwithTransaction();
                userDAO.persist(user);
                userDAO.closeSessionwithTransaction();
            } finally {
                userDAO.closeSession();
            }
            DialogUtil.informationMessage("The user was added successfully.\nusername: " + user.getUserName() + "\n",
                    "Update Information");
            control.getLogins().put(user.getUserName(), user);
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.LIBRARIAN_MENU);
        }
    }

    /**
     * This method is used to update a user's own information in the database with information from the GUI.
     * @param control The GUIController of the application.
     */
    public static void updateUser(GUIController control) {
        BaseUser user = control.getFrame().getPanelComp().getUserProfilePanel().tryUpdate();
        if (user != null) {
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.openSessionwithTransaction();
                userDAO.update(user);
                userDAO.closeSessionwithTransaction();
                DialogUtil.informationMessage("The update was successful.", "Update Information");
            } finally {
                userDAO.closeSession();
            }
            if (user.getUserType() == UserType.Librarian) {
                control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.LIBRARIAN_MENU);
            } else {
                control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.PATRON_MENU);
            }
            control.setCurrentUser(user);
        }
    }

    /**
     * Used to display the AllUsersPanel.
     * @param frame The MainFrame of the application.
     */
    public static void viewManageUsers(MainFrame frame) {
        // TODO try to add pagination for scalability
        frame.getLayout().show(frame.getPanels(), PanelComposite.ALL_USERS);
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        List<BaseUser> list = userDAO.findAll();
        userDAO.closeSessionwithTransaction();
        frame.getPanelComp().getAllUsersPanel().setUsers(list);
    }

    /**
     * Used in the AllUsersPanel to show a user's EditUserPanel.
     * @param frame The MainFrame of the application.
     * @param user The BaseUser object.
     */
    public static void viewUser(MainFrame frame, BaseUser user) {
        frame.getPanelComp().getEditUserPanel().setUser(user);
        frame.getLayout().show(frame.getPanels(), PanelComposite.EDIT_USER);
    }

    /**
     * Used in the AllUsersPanel to delete a user.
     * @param control The GUIController object.
     * @param user A BaseUser object.
     */
    public static void deleteUser(GUIController control, BaseUser user) {
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        userDAO.delete(user);
        List<BaseUser> list = userDAO.findAll();
        userDAO.closeSessionwithTransaction();
        control.getLogins().remove(user.getUserName());
        control.getFrame().getPanelComp().getAllUsersPanel().setUsers(list);
    }

    /**
     * Used for the updated button press in the EditUserPanel.
     * @param control The GUIController object.
     */
    public static void manageUpdateUser(GUIController control) {
        BaseUser user = control.getFrame().getPanelComp().getEditUserPanel().tryUpdate();
        if (user != null) {
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.openSessionwithTransaction();
                userDAO.update(user);
                userDAO.closeSessionwithTransaction();
            } finally {
                userDAO.closeSession();
            }
            control.getLogins().put(user.getUserName(), user);
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.ALL_USERS);
            DialogUtil.informationMessage("The update was successful.", "Update Information");
        }
    }

    /**
     * Gets a hashmap of login credentials from the database.
     * @return A HashMap with the username as the key.
     */
    public static HashMap<String, BaseUser> getLoginCredentials() {
        HashMap<String, BaseUser> map = new HashMap<String, BaseUser>();
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        map = userDAO.getUserHashMap();
        userDAO.closeSessionwithTransaction();
        return map;
    }

    /**
     * Used to manage the items of the library.
     * @param frame The MainFrame of the application.
     */
    public static void viewManageItems(MainFrame frame) {
        // TODO try to add pagination for scalability
        frame.getLayout().show(frame.getPanels(), PanelComposite.ALL_ITEMS);
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.openSessionwithTransaction();
        List<BaseItem> list = itemDAO.findAll();
        itemDAO.closeSessionwithTransaction();
        frame.getPanelComp().getAllItemsPanel().setItems(list);
    }

    /**
     * Used for the updated button press in the EditItemPanel.
     * @param control The GUIController object.
     */
    public static void manageUpdateItem(GUIController control) {
        /*BaseItem item = control.getFrame().getPanelComp().getEditItemPanel().tryUpdate();
        if (item != null) {
            ItemDAO itemDAO = new ItemDAO();
            try {
                itemDAO.openSessionwithTransaction();
                itemDAO.update(user);
                itemDAO.closeSessionwithTransaction();
            } finally {
                itemDAO.closeSession();
            }
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.ALL_ITEMS);
            DialogUtil.informationMessage("The update was successful.", "Update Information");
        }*/
    }

}

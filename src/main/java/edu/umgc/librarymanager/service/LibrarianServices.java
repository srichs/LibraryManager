/*
 * Filename: LibrarianServices.java
 * Author: Scott
 * Date Created: 12/2/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.access.ItemDAO;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtility;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.gui.MainFrame;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import org.hibernate.HibernateException;

/**
 * This class is used to provide methods related to the Librarian services.
 * @author Scott
 */
public final class LibrarianServices {

    private LibrarianServices() {}

    // Manage Users

    public static void addUser(MainFrame frame) {
        frame.getPanelComp().getAddUserPanel().setNew();
        frame.getLayout().show(frame.getPanels(), PanelComposite.ADD_USER);
    }

    /**
     * This method is used to create a user from the information in the Add User panel.
     * @param control The GUIController of the application.
     */
    public static void createUser(GUIController control) {
        BaseUser user = control.getFrame().getPanelComp().getAddUserPanel().tryCreate();
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
        frame.getPanelComp().getAllUsersPanel().reset();
        frame.getLayout().show(frame.getPanels(), PanelComposite.ALL_USERS);
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
        userDAO.closeSessionwithTransaction();
        control.getFrame().getPanelComp().getAllUsersPanel().getPaginationPanel().getSearchData().runSearch();
        control.getFrame().getPanelComp().getAllUsersPanel().getPaginationPanel().update();
        control.getFrame().getPanelComp().getAllUsersPanel().update();
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
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.ALL_USERS);
            DialogUtil.informationMessage("The update was successful.", "Update Information");
        }
    }

    // Manage Items

    /**
     * Used to manage the items of the library.
     * @param frame The MainFrame of the application.
     */
    public static void viewManageItems(MainFrame frame) {
        frame.getPanelComp().getAllItemsPanel().reset();
        frame.getLayout().show(frame.getPanels(), PanelComposite.ALL_ITEMS);
    }

    /**
     * Used for the updated button press in the EditItemPanel.
     * @param control The GUIController object.
     */
    public static void manageUpdateItem(GUIController control) {
        BaseItem item = control.getFrame().getPanelComp().getEditItemPanel().tryUpdate();
        if (item != null) {
            ItemDAO itemDAO = new ItemDAO();
            try {
                itemDAO.openSessionwithTransaction();
                itemDAO.update(item);
                itemDAO.closeSessionwithTransaction();
            } finally {
                itemDAO.closeSession();
            }
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.ALL_ITEMS);
            DialogUtil.informationMessage("The update was successful.", "Update Information");
        }
    }

    public static void addItem(MainFrame frame) {
        frame.getPanelComp().getAddItemPanel().setNew();
        frame.getLayout().show(frame.getPanels(), PanelComposite.ADD_ITEM);
    }

    /**
     * This method is used to create an item, it is called when the Add User button is pressed on the AddUserPanel.
     * @param control The GUIController of the application.
     */
    public static void createItem(GUIController control) {
        BaseItem item = control.getFrame().getPanelComp().getAddItemPanel().tryCreate();
        if (item != null) {
            DeweyCategoryDAO deweyDAO = new DeweyCategoryDAO();
            try {
                deweyDAO.openSessionwithTransaction();
                item.setGenre(deweyDAO.findByCode(DeweyDecimalUtility.parseCode(item.getClassificationGroup()
                        .getDewey().getCode())).getCategory());
                deweyDAO.closeSessionwithTransaction();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } finally {
                deweyDAO.closeSession();
            }
            ItemDAO itemDAO = new ItemDAO();
            try {
                itemDAO.openSessionwithTransaction();
                itemDAO.persist(item);
                itemDAO.closeSessionwithTransaction();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } finally {
                itemDAO.closeSession();
            }
            DialogUtil.informationMessage("The item was added successfully.\ntitle: " + item.getTitle() + "\n",
                    "Update Information");
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.LIBRARIAN_MENU);
        }
    }

    /**
     * Used to delete an item from the item management screen.
     * @param control The GUIController of the application.
     * @param item The item to be deleted.
     */
    public static void deleteItem(GUIController control, BaseItem item) {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.openSessionwithTransaction();
        itemDAO.delete(item);
        itemDAO.closeSessionwithTransaction();
        control.getFrame().getPanelComp().getAllItemsPanel().getPaginationPanel().getSearchData().runSearch();
        control.getFrame().getPanelComp().getAllItemsPanel().getPaginationPanel().update();
        control.getFrame().getPanelComp().getAllItemsPanel().update();
    }

    /**
     * Used to view the details of an item.
     * @param frame The MainFrame of the application.
     * @param item The item to be viewed.
     */
    public static void viewItem(MainFrame frame, BaseItem item) {
        frame.getPanelComp().getEditItemPanel().setItem(item);
        frame.getLayout().show(frame.getPanels(), PanelComposite.EDIT_ITEM);
    }

}

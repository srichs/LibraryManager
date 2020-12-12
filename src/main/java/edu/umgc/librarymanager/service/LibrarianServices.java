/*
 * Filename: LibrarianServices.java
 * Author: Scott
 * Date Created: 12/2/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtility;
import edu.umgc.librarymanager.data.access.ItemDAO;
import edu.umgc.librarymanager.data.access.TransactionDAO;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.TransactionType;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.gui.MainFrame;
import edu.umgc.librarymanager.gui.panels.ItemViewType;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
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
                control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.SEARCH);
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
    public static void viewManageItems(MainFrame frame, String command) {
        frame.getPanelComp().getAllItemsPanel().reset(command);
        if ((frame.getPanelComp().getAllItemsPanel().getViewType() == ItemViewType.Checkout
                || frame.getPanelComp().getAllItemsPanel().getViewType() == ItemViewType.Return_)
                && frame.getPanelComp().getAllItemsPanel().getPaginationPanel().getSearchData().getResults().size() == 0) {
            return;
        }
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
    
    /**
     * This method is used to reserve an item.
     * @param control The GUIController of the applicaton.
     * @param item The BaseItem to be reserved.
     */
    public static void returnItem(GUIController control, BaseItem item) {
        System.out.println(item.toString());
        TransactionDAO transDAO = new TransactionDAO();
        try {
            transDAO.openSessionwithTransaction();
            BaseTransaction trans = transDAO.findByItem(item);
            ((BaseItem) trans.getItem()).setStatus(ItemStatus.Available);
            ((BaseItem) trans.getItem()).setCheckoutPeriod(Period.ZERO);
            trans.setTransactionDateTime(ZonedDateTime.now());
            trans.setTransactionType(TransactionType.Return);
            ((BaseItem) trans.getItem()).setNotified(false);
            transDAO.update(trans);
            transDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            transDAO.closeSession();
        }
        DialogUtil.informationMessage("The item was successfully returned.", "Return Successful");
        if (control == null) {
            System.out.println("Control is null");
        }
        control.getFrame().getPanelComp().getAllItemsPanel().update();
    }
    
    /**
     * This method is used to reserve an item.
     * @param control The GUIController of the applicaton.
     * @param item The BaseItem to be reserved.
     */
    public static void checkOutItem(GUIController control, BaseItem item) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to check out:\n"
                + item.getTitle() + "?", "Check Out Item", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            TransactionDAO transDAO = new TransactionDAO();
            try {
                transDAO.openSessionwithTransaction();
                BaseTransaction trans = null;
                trans = transDAO.findByItemAndStatus(item, TransactionType.Reserve);
                ((BaseItem) trans.getItem()).setStatus(ItemStatus.CheckedOut);
                trans.setDueDate(ZonedDateTime.now().plusDays(14));
                trans.setTransactionDateTime(ZonedDateTime.now());
                trans.setTransactionType(TransactionType.CheckOut);
                LocalDate checkDate = LocalDate.now();
                LocalDate dueDate = LocalDate.now().plusDays(14);
                ((BaseItem) trans.getItem()).setCheckoutPeriod(Period.between(checkDate, dueDate));
                transDAO.update(trans);
                transDAO.closeSessionwithTransaction();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } finally {
                transDAO.closeSession();
            }
            DialogUtil.informationMessage("The item was successfully checked out.", "Check Out Successful");
            control.getFrame().getPanelComp().getAllItemsPanel().update();
        }
    }

    public static void notifyUser(GUIController control, BaseItem item) {
        boolean wasSent = false;
        TransactionDAO transDAO = new TransactionDAO();
        BaseTransaction trans = null;
        try {
            transDAO.openSessionwithTransaction();
            trans = transDAO.findByItemAndStatus(item, TransactionType.Reserve);
            try {
                wasSent = true;
                GmailUtility.sendNotificationEmail((BaseUser) trans.getUser(), trans.getLibrary().getName(),
                        (BaseItem) trans.getItem());
            } catch (IOException | MessagingException | GeneralSecurityException e) {
                wasSent = false;
                DialogUtil.errorMessage("The application was unable to send the notification.",
                        "Notification Failure");
                e.printStackTrace();
            }
            if (wasSent) {
                ((BaseItem) trans.getItem()).setNotified(true);
            }
            transDAO.update(trans);
            transDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            transDAO.closeSession();
        }
        control.getFrame().getPanelComp().getAllItemsPanel().update();
        if (wasSent) {
            DialogUtil.informationMessage("The notification for " + trans.getItem().getTitle()
                    + " was sent successfully.", "Notification Successful");
        }
    }

}

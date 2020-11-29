/*
 * Filename: ControlHelper.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.panels.PanelComposite;

/**
 * This class is used to provide helper methods for the GUIController class.
 * @author Scott
 */
public class ControlHelper {

    public static void viewProfile(MainFrame frame, BaseUser currentUser) {
        frame.getPanelComp().getUserProfilePanel().setUser(currentUser);
        frame.getLayout().show(frame.getPanels(), PanelComposite.PROFILE);
    }

    public static void addUser(MainFrame frame) {
        frame.getPanelComp().getAddUserPanel().setNew();
        frame.getLayout().show(frame.getPanels(), PanelComposite.ADD_USER);
    }

    public static void createUser(MainFrame frame) {
        BaseUser user = frame.getPanelComp().getAddUserPanel().tryCreate(frame.getPanelComp()
                .getLoginPanel().getUsers());
        if (user != null) {
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.openSessionwithTransaction();
                userDAO.persist(user);
                userDAO.closeSessionwithTransaction();
            } finally {
                userDAO.closeSession();
            }
            DialogUtil.informationMessage("The user was added successfully.\n\nusername: " + user.getUserName(),
                    "Update Information");
            frame.getPanelComp().getLoginPanel().addUserToMap(user);
            frame.getLayout().show(frame.getPanels(), PanelComposite.LIBRARIAN_MENU);
        }
    }

    public static void updateUser(MainFrame frame, BaseUser currentUser) {
        BaseUser user = frame.getPanelComp().getUserProfilePanel().tryUpdate();
        if (user != null) {
            UserDAO userDAO = new UserDAO();
            try{
                userDAO.openSessionwithTransaction();
                userDAO.update(user);
                userDAO.closeSessionwithTransaction();
                DialogUtil.informationMessage("The update was successful.", "Update Information");
            } finally {
                userDAO.closeSession();
            }
            currentUser = user;
            if (currentUser.getUserType() == UserType.Librarian) {
                frame.getLayout().show(frame.getPanels(), PanelComposite.LIBRARIAN_MENU);
            } else {
                frame.getLayout().show(frame.getPanels(), PanelComposite.PATRON_MENU);
            }
        }
    }

}

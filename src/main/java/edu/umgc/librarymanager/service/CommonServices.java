/*
 * Filename: ControlHelper.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.gui.InactivityListener;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

/**
 * This class is used to provide helper methods for the GUIController class.
 * @author Scott
 */
public final class CommonServices {

    private static final Logger LOG = LogManager.getLogger(GUIController.class);

    private CommonServices() {}

    /**
     * This method is called when the login button is pressed.
     * @param control The GUIController of the application.
     */
    public static void login(GUIController control) {
        String username = control.getFrame().getPanelComp().getLoginPanel().getUsernameField().getText();
        UserDAO userDAO = new UserDAO();
        BaseUser user = null;
        try {
            userDAO.openSessionwithTransaction();
            user = userDAO.searchByUsername(username);
            userDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            userDAO.closeSession();
        }
        if (user != null) {
            if (user.getLogin().checkPassword(control.getFrame().getPanelComp().getLoginPanel()
                    .getPasswordField().getPassword())) {
                control.getFrame().getPanelComp().getLoginPanel().clearFields();
                control.setCurrentUser(user);
                LOG.info(user.getUserName() + " has logged on.");
                if (control.getCurrentUser().getUserType() == UserType.Librarian) {
                    control.getFrame().getTheMenuBar().setLibrarianMenuBar(control);
                    control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.LIBRARIAN_MENU);
                } else {
                    control.getFrame().getTheMenuBar().setPatronMenuBar(control);
                    control.getFrame().getPanelComp().getSearchPanel()
                            .setFirstName(control.getCurrentUser().getFirstName());
                    control.getFrame().getPanelComp().getSearchPanel().getSearchField().setText("");
                    control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.SEARCH);
                }
                control.getFrame().repaint();
                // user is logged out after 5 min of inactivity
                control.setInactiveListener(new InactivityListener(control.getFrame(), control.getLogoutAction(), 5));
                control.getInactiveListener().start();
            } else {
                DialogUtil.warningMessage("Incorrect password. Access denied.", "Login Failure");
            }
        } else {
            DialogUtil.warningMessage("The username was not recognized.", "Login Failure");
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

}

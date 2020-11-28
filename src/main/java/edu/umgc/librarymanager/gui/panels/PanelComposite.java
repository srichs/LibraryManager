/*
 * Filename: PanelComposite.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.gui.GUIController;

/**
 * This class is a composite class containing the panels of the application.
 * 
 * @author Scott
 */
public class PanelComposite {

    /**
     * A String for the Login panel.
     */
    public static final String LOGIN = "LoginPanel";
    /**
     * A String for the Librarian Menu panel.
     */
    public static final String LIBRARIAN_MENU = "LibrarianMenuPanel";
    /**
     * A String for the Patron Menu panel.
     */
    public static final String PATRON_MENU = "PatronMenuPanel";
    /**
     * A String for the Search panel.
     */
    public static final String SEARCH = "SearchPanel";
    /**
     * A String for the User Profile panel.
     */
    public static final String PROFILE = "UserProfilePanel";
    /**
     * A String for the Add User panel.
     */
    public static final String ADD_USER = "AddUserPanel";

    private LoginPanel loginPanel;
    private LibrarianMenuPanel librarianMenuPanel;
    private PatronMenuPanel patronMenuPanel;
    private SearchPanel searchPanel;
    private UserProfilePanel userProfilePanel;
    private AddUserPanel addUserPanel;

    /**
     * The constructor of the class.
     * @param control A GUIController Object that is the ActionListener.
     */
    public PanelComposite(GUIController control) {
        this.loginPanel = new LoginPanel(control);
        createPanels(control);
    }

    /**
     * Creates new panels with no information.
     * @param control The GUIController to act as the listener.
     */
    public void createPanels(GUIController control) {
        this.librarianMenuPanel = new LibrarianMenuPanel(control);
        this.patronMenuPanel = new PatronMenuPanel(control);
        this.searchPanel = new SearchPanel(control);
        this.userProfilePanel = new UserProfilePanel(control);
        this.addUserPanel = new AddUserPanel(control);
    }

    public LoginPanel getLoginPanel() {
        return this.loginPanel;
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public LibrarianMenuPanel getLibrarianMenuPanel() {
        return this.librarianMenuPanel;
    }

    public void setLibrarianMenuPanel(LibrarianMenuPanel librarianMenu) {
        this.librarianMenuPanel = librarianMenu;
    }

    public PatronMenuPanel getPatronMenuPanel() {
        return this.patronMenuPanel;
    }

    public void setPatronMenuPanel(PatronMenuPanel patronMenu) {
        this.patronMenuPanel = patronMenu;
    }

    public SearchPanel getSearchPanel() {
        return this.searchPanel;
    }

    public void setSearchPanel(SearchPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public UserProfilePanel getUserProfilePanel() {
        return this.userProfilePanel;
    }

    public void setUserProfilePanel(UserProfilePanel userPanel) {
        this.userProfilePanel = userPanel;
    }

    public AddUserPanel getAddUserPanel() {
        return this.addUserPanel;
    }

    public void setAddUserPanel(AddUserPanel userPanel) {
        this.addUserPanel = userPanel;
    }

}

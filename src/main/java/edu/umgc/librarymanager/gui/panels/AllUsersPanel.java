/*
 * Filename: AllUsersPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.Pagination;
import edu.umgc.librarymanager.data.access.SearchData;
import edu.umgc.librarymanager.data.access.UserField;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.service.LibrarianServices;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.exception.EmptyQueryException;

/**
 * This class is used to view all users of the Library system. It uses the UserView class
 * to display each user in a list of users in a scroll pane. Each individual user can
 * be viewed or deleted. There is also an option to search the users last name and to
 * add a new user.
 * @author Scott
 */
public class AllUsersPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = -9189700781888896926L;
    private static final Logger LOG = LogManager.getLogger(AllUsersPanel.class);

    private JScrollPane scrollPane;
    private JPanel userPanel;
    private JTextField searchField;
    private BaseUser selectedUser;
    private PaginationPanel<BaseUser> paginationPanel;
    private GUIController control;

    /**
     * The constructor of the class.
     * @param control The GUIController of the application.
     */
    public AllUsersPanel(GUIController control) {
        this.paginationPanel = new PaginationPanel<BaseUser>(new SearchData<BaseUser>(
                null, null, new Pagination(20, 0, 1), BaseUser.class), this);
        this.control = control;
        createPanel(control);
    }

    /**
     * Creates the panel to be displayed by the class.
     */
    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setPreferredSize(new Dimension(880, 60));
        searchPanel.setMinimumSize(new Dimension(880, 60));
        searchPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        this.searchField = new JTextField(28);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setActionCommand(Command.SEARCH);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setActionCommand(Command.CLEAR);
        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(control);
        addUserButton.setActionCommand(Command.ADD_USER);
        searchPanel.add(new JLabel("Search by Lastname "));
        searchPanel.add(this.searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(Box.createRigidArea(new Dimension(100, 40)));
        searchPanel.add(addUserButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        this.userPanel = new JPanel();
        this.userPanel.setLayout(new BoxLayout(this.userPanel, BoxLayout.Y_AXIS));
        this.scrollPane = new JScrollPane(this.userPanel);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        update();

        this.scrollPane.revalidate();
        this.scrollPane.repaint();
        mainPanel.add(this.scrollPane, BorderLayout.CENTER);
        mainPanel.add(this.paginationPanel, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Used to fill the scroll pane with the provided List of users.
    private void fillPane(List<BaseUser> list) {
        if (list == null) {
            return;
        }
        this.userPanel.removeAll();
        for (int i = 0; i < list.size(); i++) {
            UserView view = new UserView(list.get(i), new UserActionListener(list.get(i)));
            this.userPanel.add(view);
        }
        this.scrollPane.getVerticalScrollBar().setValue(0);
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
    }

    // Used to search the list then provide the names that match the search characters.
    private void searchList(String lastName) {
        String[] fields = {UserField.LastName.toString()};
        SearchData<BaseUser> sd = new SearchData<BaseUser>(fields, lastName,
                new Pagination(20, 0, 1), BaseUser.class);
        this.paginationPanel.setSearchData(sd);
        update();
    }

    /**
     * Resets the search bar and the displayed items.
     */
    public void reset() {
        this.searchField.setText("");
        this.paginationPanel.setSearchData(new SearchData<BaseUser>(
                null, null, new Pagination(20, 0, 1), BaseUser.class));
        update();
    }

    /**
     * Called to update the displayed items in the panel, it also updates the pagination information.
     */
    public void update() {
        try {
            this.paginationPanel.getSearchData().runSearch();
        } catch (EmptyQueryException ex) {
            reset();
            DialogUtil.informationMessage("No results were found.", "No Results Found");
        }
        if (this.paginationPanel.getSearchData().getResults().size() == 0) {
            reset();
            DialogUtil.informationMessage("No results were found.", "No Results Found");
        }
        this.paginationPanel.update();
        fillPane(this.paginationPanel.getSearchData().getResults());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Command.SEARCH.equals(e.getActionCommand())) {
            LOG.info("Search button pressed.");
            if (!this.searchField.getText().equals("")) {
                searchList(this.searchField.getText());
            } else {
                DialogUtil.warningMessage("Please enter the search terms into the search bar.", "No Search Terms");
            }
        } else if (Command.CLEAR.equals(e.getActionCommand())) {
            LOG.info("Clear button pressed.");
            reset();
        } else if (PaginationPanel.NEXT_PRESS.equals(e.getActionCommand())) {
            LOG.info("Next button pressed.");
            this.paginationPanel.nextPressed();
            fillPane(this.paginationPanel.getSearchData().getResults());
        } else if (PaginationPanel.PREVIOUS_PRESS.equals(e.getActionCommand())) {
            LOG.info("Previous button pressed.");
            this.paginationPanel.previousPressed();
            fillPane(this.paginationPanel.getSearchData().getResults());
        } else if (PaginationPanel.GOTO_PRESS.equals(e.getActionCommand())) {
            LOG.info("Go To button pressed.");
            this.paginationPanel.goToPressed();
            fillPane(this.paginationPanel.getSearchData().getResults());
        }
    }

    public PaginationPanel<BaseUser> getPaginationPanel() {
        return this.paginationPanel;
    }

    public void setPaginationPanel(PaginationPanel<BaseUser> pagePanel) {
        this.paginationPanel = pagePanel;
    }

    public BaseUser getSelectedUser() {
        return this.selectedUser;
    }

    public void setSelectedUser(BaseUser user) {
        this.selectedUser = user;
    }

    public GUIController getController() {
        return this.control;
    }

    /**
     * An inner class to listen to the users in the scroll pane and fire actions based on which
     * button is pressed in a user's panel.
     * @author Scott
     */
    private class UserActionListener implements ActionListener {
        private BaseUser user;

        UserActionListener(BaseUser user) {
            this.user = user;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setSelectedUser(this.user);
            if (Command.VIEW_USER.equals(e.getActionCommand())) {
                LibrarianServices.viewUser(getController().getFrame(), this.user);
            } else if (Command.DELETE_USER.equals(e.getActionCommand())) {
                LibrarianServices.deleteUser(getController(), this.user);
            }
        }
    }

}

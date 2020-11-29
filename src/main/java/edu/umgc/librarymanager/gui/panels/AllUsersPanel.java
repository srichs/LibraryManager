/*
 * Filename: AllUsersPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.service.ControlHelper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to view all users of the Library system.
 * @author Scott
 */
public class AllUsersPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = -9189700781888896926L;

    private JScrollPane scrollPane;
    private JPanel userPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton addUserButton;
    private List<BaseUser> users;
    private BaseUser selectedUser;
    private GUIController control;

    /**
     * The constructor of the class.
     * @param control The GUIController of the application.
     */
    public AllUsersPanel(GUIController control) {
        this.users = null;
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
        this.searchButton = new JButton("Search");
        this.searchButton.addActionListener(this);
        this.searchButton.setActionCommand(Command.SEARCH);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setActionCommand(Command.CLEAR);
        this.addUserButton = new JButton("Add User");
        this.addUserButton.addActionListener(control);
        this.addUserButton.setActionCommand(Command.ADD_USER);
        searchPanel.add(new JLabel("Search by Lastname "));
        searchPanel.add(this.searchField);
        searchPanel.add(this.searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(Box.createRigidArea(new Dimension(100, 40)));
        searchPanel.add(this.addUserButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        this.userPanel = new JPanel();
        this.userPanel.setLayout(new BoxLayout(this.userPanel, BoxLayout.Y_AXIS));
        this.scrollPane = new JScrollPane(this.userPanel);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        fillPane(this.users);

        this.scrollPane.revalidate();
        this.scrollPane.repaint();
        mainPanel.add(this.scrollPane);
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void fillPane(List<BaseUser> list) {
        if (list == null) {
            return;
        }
        this.userPanel.removeAll();
        for (int i = 0; i < list.size(); i++) {
            UserView view = new UserView(list.get(i), new UserActionListener(list.get(i)));
            this.userPanel.add(view);
        }
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
    }

    private void searchList(String lastName) {
        List<BaseUser> newList = new ArrayList<BaseUser>();
        for (int i = 0; i < this.users.size(); i++) {
            String lname = this.users.get(i).getLastName();
            if (lname.toLowerCase().contains(lastName.toLowerCase())) {
                newList.add(this.users.get(i));
            }
        }
        fillPane(newList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Command.SEARCH.equals(e.getActionCommand())) {
            if (!this.searchField.getText().equals("")) {
                searchList(this.searchField.getText());
            }
        } else if (Command.CLEAR.equals(e.getActionCommand())) {
            this.searchField.setText("");
            fillPane(this.users);
        }
    }

    /**
     * Sets the users list of the class and clears the search panel then fills the scroll pane.
     * @param users The List of users of the system.
     */
    public void setUsers(List<BaseUser> users) {
        this.users = users;
        this.searchField.setText("");
        fillPane(users);
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
                ControlHelper.viewUser(getController().getFrame(), this.user);
            } else if (Command.DELETE_USER.equals(e.getActionCommand())) {
                ControlHelper.deleteUser(getController(), this.user);
            }
        }
    }

}

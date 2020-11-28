/*
 * Filename: LoginPanel.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * The login panel of the application.
 * @author Scott
 */
public class LoginPanel extends JPanel {

    private static final long serialVersionUID = 8434261070851495952L;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private HashMap<String, BaseUser> users;

    /**
     * The constructor of the class.
     * @param control A GUIController Object that is the ActionListener.
     */
    public LoginPanel(GUIController control) {
        super();
        createPanel(control);
        loadHashMap();
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField field) {
        this.usernameField = field;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField field) {
        this.passwordField = field;
    }

    /**
     * Loads a HashMap with the users from the database.
     */
    public void loadHashMap() {
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        this.users = userDAO.getUserHashMap();
        userDAO.closeSessionwithTransaction();
    }

    public void addUserToMap(BaseUser user) {
        this.users.put(user.getUserName(), user);
    }

    public HashMap<String, BaseUser> getUsers() {
        return this.users;
    }

    public void clearFields() {
        this.usernameField.setText("");
        this.passwordField.setText("");
    }

    /**
     * Attempts to authenticate a user and returns if it authenticated.
     * @return A BaseUser or null if not logged on.
     */
    public BaseUser tryLogin() {
        String username = usernameField.getText().toString();
        if (this.users.containsKey(username)) {
            if (this.users.get(username).getLogin().checkPassword(passwordField.getPassword())) {
                clearFields();
                return this.users.get(username);
            } else {
                DialogUtil.warningMessage("Incorrect password. Access denied.", "Login Failure");
            }
        } else {
            DialogUtil.warningMessage("The username was not recognized.", "Login Failure");
        }
        return null;
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 300));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setSize(new Dimension(400, 200));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel titleLabel = new JLabel("Library Management System");
        JLabel unameLabel = new JLabel("Username");
        JLabel pwordLabel = new JLabel("Password");
        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("login");
        loginButton.addActionListener((ActionListener) control);

        titlePanel.add(titleLabel, BorderLayout.CENTER);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.gridy = 1;
        loginPanel.add(unameLabel, c);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.gridy = 2;
        loginPanel.add(pwordLabel, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.weightx = 0.8;
        c.gridwidth = 2;
        c.gridy = 1;
        loginPanel.add(usernameField, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.weightx = 0.8;
        c.gridwidth = 2;
        c.gridy = 2;
        loginPanel.add(passwordField, c);
        buttonPanel.add(new JPanel());
        buttonPanel.add(loginButton);
        buttonPanel.add(new JPanel());

        mainPanel.add(titlePanel, BorderLayout.PAGE_START);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

}

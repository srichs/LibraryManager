/*
 * Filename: AddUserPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.LibrarianUser;
import edu.umgc.librarymanager.data.model.user.PatronUser;
import edu.umgc.librarymanager.data.model.user.UserLogin;
import edu.umgc.librarymanager.data.model.user.UserType;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to add a user to the database.
 * @author Scott
 */
public class AddUserPanel extends JPanel {

    private static final long serialVersionUID = 8571310030746565474L;

    private LabelFieldPanel idPanel;
    private LabelFieldPanel createDatePanel;
    private LabelFieldPanel usernamePanel;
    private LabelFieldPanel firstNamePanel;
    private LabelFieldPanel lastNamePanel;
    private LabelFieldPanel emailPanel;
    private LabelFieldPanel addressPanel;
    private LabelFieldPanel phonePanel;
    private JComboBox<UserType> typeBox;
    private JPasswordField password1;
    private JPasswordField password2;
    private JButton button;

    /**
     * The constructor of the class.
     * @param control The GUIController to act as the listener.
     */
    public AddUserPanel(GUIController control) {
        super();
        this.idPanel = new LabelFieldPanel();
        this.createDatePanel = new LabelFieldPanel();
        this.usernamePanel = new LabelFieldPanel();
        this.firstNamePanel = new LabelFieldPanel();
        this.lastNamePanel = new LabelFieldPanel();
        this.emailPanel = new LabelFieldPanel();
        this.addressPanel = new LabelFieldPanel();
        this.phonePanel = new LabelFieldPanel();
        this.typeBox = new JComboBox<UserType>(UserType.values());
        this.password1 = new JPasswordField();
        this.password2 = new JPasswordField();
        this.password1.setColumns(30);
        this.password2.setColumns(30);
        this.button = new JButton("Add User");
        createPanel(control);
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 400));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        addPanel(this.idPanel, fieldPanel, "ID", "auto", true);
        addPanel(this.createDatePanel, fieldPanel, "Date Created", "auto", true);
        addPanel(this.usernamePanel, fieldPanel, "Username", "auto", true);
        passwordPanel(this.password1, fieldPanel);
        passwordPanel(this.password2, fieldPanel);
        addPanel(this.firstNamePanel, fieldPanel, "First Name", "", false);
        addPanel(this.lastNamePanel, fieldPanel, "Last Name", "", false);
        addPanel(this.emailPanel, fieldPanel, "Email", "", false);
        addPanel(this.addressPanel, fieldPanel, "Address", "", false);
        addPanel(this.phonePanel, fieldPanel, "Phone", "", false);
        this.typeBox.setSelectedItem(UserType.Patron);
        typePanel(this.typeBox, fieldPanel);
        addButton(this.button, fieldPanel, Command.CREATE_USER, control);
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void typePanel(JComboBox<UserType> typeBox, Container container) {
        JLabel label = new JLabel("User Type");
        label.setPreferredSize(new Dimension(76, 20));
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setSize(new Dimension(400, 60));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.weightx = 0.2;
        c.gridwidth = 1;
        c.gridy = 0;
        mainPanel.add(label, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.weightx = 0.8;
        c.gridwidth = 2;
        c.gridy = 0;
        mainPanel.add(typeBox, c);
        container.add(mainPanel);
    }

    private void passwordPanel(JPasswordField passField, Container container) {
        JLabel label = new JLabel("Password");
        label.setPreferredSize(new Dimension(76, 20));
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setSize(new Dimension(400, 60));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.weightx = 0.2;
        c.gridwidth = 1;
        c.gridy = 0;
        mainPanel.add(label, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.weightx = 0.8;
        c.gridwidth = 2;
        c.gridy = 0;
        mainPanel.add(passField, c);
        container.add(mainPanel);
    }

    private static void addPanel(LabelFieldPanel panel, Container container, String label, String textField,
            boolean readOnly) {
        panel.setLabel(label);
        panel.setText(textField);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(panel);
        if (readOnly) {
            panel.getTextField().setEditable(false);
        }
    }

    private static void addButton(JButton button, Container container, String command, GUIController control) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setActionCommand(command);
        button.addActionListener((ActionListener) control);
        container.add(Box.createRigidArea(new Dimension(20, 20)));
        container.add(button);
    }

    /**
     * Tries to create a new user with the information in the form and returns the user if created.
     * @param users The HashMap of users that is used at login to check for names.
     * @return The BaseUser if added or null if not added.
     */
    public BaseUser tryCreate(HashMap<String, BaseUser> users) {
        if (checkFields()) {
            String username = UserLogin.genUsername(users, this.firstNamePanel.getText(), this.lastNamePanel.getText());
            UserLogin login = new UserLogin(username, this.password1.getPassword());
            BaseUser addedUser = null;
            if (this.typeBox.getSelectedItem() == UserType.Patron) {
                addedUser = new PatronUser(ZonedDateTime.now(), this.firstNamePanel.getText(),
                        this.lastNamePanel.getText(), login, this.emailPanel.getText(), this.addressPanel.getText(),
                        this.phonePanel.getText());
            } else if (this.typeBox.getSelectedItem() == UserType.Librarian) {
                addedUser = new LibrarianUser(ZonedDateTime.now(), this.firstNamePanel.getText(),
                        this.lastNamePanel.getText(), login, this.emailPanel.getText(), this.addressPanel.getText(),
                        this.phonePanel.getText());
            }
            return addedUser;
        }
        return null;
    }

    private boolean checkFields() {
        if (this.password1.getPassword().length < 8) {
            DialogUtil.warningMessage("The password should be 8 characters or longer.", "Update Failure");
            return false;
        }
        if (!Arrays.equals(this.password1.getPassword(), this.password2.getPassword())) {
            DialogUtil.warningMessage("The passwords do not match.", "Update Failure");
            return false;
        }
        if (this.firstNamePanel.getTextField().getText().equals("")
                || this.lastNamePanel.getTextField().getText().equals("")
                || this.emailPanel.getTextField().getText().equals("")
                || this.addressPanel.getTextField().getText().equals("")
                || this.phonePanel.getTextField().getText().equals("")) {
            DialogUtil.warningMessage("All fields must be filled.", "Update Failure");
            return false;
        }
        return true;
    }

    /**
     * Sets all of the editable fields blank.
     */
    public void setNew() {
        this.password1.setText("");
        this.password2.setText("");
        this.firstNamePanel.setText("");
        this.lastNamePanel.setText("");
        this.emailPanel.setText("");
        this.addressPanel.setText("");
        this.phonePanel.setText("");
    }

}

/*
 * Filename: EditUserPanel.java
 * Author: Scott
 * Date Created: 11/29/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.user.BaseUser;
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
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to edit an user in the database.
 * @author Scott
 */
public class EditUserPanel extends JPanel {

    private static final long serialVersionUID = 8571310030746565474L;

    private LabelFieldPanel idPanel;
    private LabelFieldPanel createDatePanel;
    private LabelFieldPanel usernamePanel;
    private LabelFieldPanel firstNamePanel;
    private LabelFieldPanel lastNamePanel;
    private LabelFieldPanel emailPanel;
    private LabelFieldPanel addressPanel;
    private LabelFieldPanel phonePanel;
    private LabelFieldPanel typePanel;
    private JPasswordField password1;
    private JPasswordField password2;
    private JButton button;
    private BaseUser user;

    /**
     * The constructor of the class.
     * @param control The GUIController to act as the listener.
     */
    public EditUserPanel(GUIController control) {
        super();
        this.idPanel = new LabelFieldPanel();
        this.createDatePanel = new LabelFieldPanel();
        this.usernamePanel = new LabelFieldPanel();
        this.firstNamePanel = new LabelFieldPanel();
        this.lastNamePanel = new LabelFieldPanel();
        this.emailPanel = new LabelFieldPanel();
        this.addressPanel = new LabelFieldPanel();
        this.phonePanel = new LabelFieldPanel();
        this.typePanel = new LabelFieldPanel();
        this.password1 = new JPasswordField();
        this.password2 = new JPasswordField();
        this.password1.setColumns(30);
        this.password2.setColumns(30);
        this.button = new JButton("Update");
        createPanel(control);
    }

    public void setUser(BaseUser user) {
        this.user = user;
        setUserInfo();
    }

    /**
     * Sets the users information to the form fields.
     * @param user The user to set the information from.
     */
    private void setUserInfo() {
        if (user == null) {
            return;
        }
        this.idPanel.setText(String.valueOf(user.getId()));
        this.createDatePanel.setText(user.createdDateTime().toString());
        this.usernamePanel.setText(user.getUserName());
        this.firstNamePanel.setText(user.getFirstName());
        this.lastNamePanel.setText(user.getLastName());
        this.emailPanel.setText(user.getEmail());
        this.addressPanel.setText(user.getAddress());
        this.phonePanel.setText(user.getPhoneNumber());
        this.typePanel.setText(user.getUserType().toString());
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 400));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        addPanel(this.idPanel, fieldPanel, "ID", "", true);
        addPanel(this.createDatePanel, fieldPanel, "Date Created", "", true);
        addPanel(this.usernamePanel, fieldPanel, "Username", "", true);
        passwordPanel(this.password1, fieldPanel);
        passwordPanel(this.password2, fieldPanel);
        addPanel(this.firstNamePanel, fieldPanel, "First Name", "", false);
        addPanel(this.lastNamePanel, fieldPanel, "Last Name", "", false);
        addPanel(this.emailPanel, fieldPanel, "Email", "", false);
        addPanel(this.addressPanel, fieldPanel, "Address", "", false);
        addPanel(this.phonePanel, fieldPanel, "Phone", "", false);
        addPanel(this.typePanel, fieldPanel, "User Type", "", true);
        addButton(this.button, fieldPanel, Command.MANAGE_UPDATE_USER, control);
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
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
     * @return The BaseUser if added or null if not added.
     */
    public BaseUser tryUpdate() {
        if (checkFields()) {
            if (checkInfoDiff(user)) {
                BaseUser updatedUser = user;
                if (this.password1.getPassword().length > 0 && this.password2.getPassword().length > 0) {
                    updatedUser.getLogin().setPassword(this.password1.getPassword());
                }
                updatedUser.setFirstName(this.firstNamePanel.getText());
                updatedUser.setLastName(this.lastNamePanel.getText());
                updatedUser.setEmail(this.emailPanel.getText());
                updatedUser.setAddress(this.addressPanel.getText());
                updatedUser.setPhoneNumber(this.phonePanel.getText());
                setNew();
                return updatedUser;
            }
        }
        return null;
    }

    private boolean checkInfoDiff(BaseUser user) {
        boolean passwordChanged = false;
        if (this.password1.getPassword().length > 0 && this.password2.getPassword().length > 0) {
            if (!user.getLogin().checkPassword(this.password1.getPassword())) {
                passwordChanged = true;
            }
        }
        if (this.firstNamePanel.getTextField().getText().equals(user.getFirstName())
                && this.lastNamePanel.getTextField().getText().equals(user.getLastName())
                && this.emailPanel.getTextField().getText().equals(user.getEmail())
                && this.addressPanel.getTextField().getText().equals(user.getAddress())
                && this.phonePanel.getTextField().getText().equals(user.getPhoneNumber())
                && !passwordChanged) {
            DialogUtil.informationMessage("No update was necessary.", "Update Information");
            return false;
        }
        return true;
    }

    private boolean checkFields() {
        if (this.password1.getPassword().length > 0 || this.password2.getPassword().length > 0) {
            if (this.password1.getPassword().length < 8) {
                DialogUtil.warningMessage("The password should be 8 characters or longer.", "Update Failure");
                return false;
            }
            if (!Arrays.equals(this.password1.getPassword(), this.password2.getPassword())) {
                DialogUtil.warningMessage("The passwords do not match.", "Update Failure");
                return false;
            }
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
        this.usernamePanel.setText("");
        this.password1.setText("");
        this.password2.setText("");
        this.firstNamePanel.setText("");
        this.lastNamePanel.setText("");
        this.emailPanel.setText("");
        this.addressPanel.setText("");
        this.phonePanel.setText("");
    }

}

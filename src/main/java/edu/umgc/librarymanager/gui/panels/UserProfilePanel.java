/*
 * Filename: UserProfilePanel.java
 * Author: Scott
 * Date Created: 11/27/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to create a User Profile panel.
 * @author Scott
 */
public class UserProfilePanel extends JPanel {

    private static final long serialVersionUID = -8045285075977513887L;

    private LabelFieldPanel idPanel;
    private LabelFieldPanel createDatePanel;
    private LabelFieldPanel usernamePanel;
    private LabelFieldPanel firstNamePanel;
    private LabelFieldPanel lastNamePanel;
    private LabelFieldPanel emailPanel;
    private LabelFieldPanel addressPanel;
    private LabelFieldPanel phonePanel;
    private LabelFieldPanel typePanel;
    private JButton button;

    /**
     * The constructor of the class.
     * @param control The GUIController to act as the listener.
     */
    public UserProfilePanel(GUIController control) {
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
        this.button = new JButton("Update");
        createPanel(control);
    }

    /**
     * Sets the users information to the form fields.
     * @param user The user to set the information from.
     */
    public void setUserInfo(BaseUser user) {
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
        addPanel(this.idPanel, fieldPanel, "ID", "auto", true);
        addPanel(this.createDatePanel, fieldPanel, "Date Created", "auto", true);
        addPanel(this.usernamePanel, fieldPanel, "Username", "auto", true);
        addPanel(this.firstNamePanel, fieldPanel, "First Name", "", true);
        addPanel(this.lastNamePanel, fieldPanel, "Last Name", "", true);
        addPanel(this.emailPanel, fieldPanel, "Email", "", false);
        addPanel(this.addressPanel, fieldPanel, "Address", "", false);
        addPanel(this.phonePanel, fieldPanel, "Phone", "", false);
        addPanel(this.typePanel, fieldPanel, "User Type", "auto", true);
        addButton(this.button, fieldPanel, "update_user", control);
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
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
     * Tries to update the user by comparing it to the stored value of the user.
     * @param user The user to compare the information that is on the form to.
     * @return The user if updated or null if not updated.
     */
    public BaseUser tryUpdate(BaseUser user) {
        if (checkFields()) {
            if (checkInfoDiff(user)) {
                BaseUser updatedUser = user;
                updatedUser.setEmail(this.emailPanel.getText());
                updatedUser.setAddress(this.addressPanel.getText());
                updatedUser.setPhoneNumber(this.phonePanel.getText());
                UserDAO userDAO = new UserDAO();
                userDAO.openSessionwithTransaction();
                userDAO.update(updatedUser);
                userDAO.closeSessionwithTransaction();
                DialogUtil.informationMessage("The update was successful.", "Update Information");
                return updatedUser;
            }
        }
        return null;
    }

    private boolean checkInfoDiff(BaseUser user) {
        if (this.emailPanel.getTextField().getText().equals(user.getEmail())
                && this.addressPanel.getTextField().getText().equals(user.getAddress())
                && this.phonePanel.getTextField().getText().equals(user.getPhoneNumber())) {
            DialogUtil.informationMessage("No update was necessary.", "Update Information");
            return false;
        }
        return true;
    }

    private boolean checkFields() {
        if (this.emailPanel.getTextField().getText().equals("")
                || this.addressPanel.getTextField().getText().equals("")
                || this.phonePanel.getTextField().getText().equals("")) {
            DialogUtil.warningMessage("All fields must be filled.", "Update Failure");
            return false;
        }
        return true;
    }

    public String getIdText() {
        return this.idPanel.getTextField().getText();
    }

}

/*
 * Filename: UserView.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.Command;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to display a single user in a list of users.
 * @author Scott
 */
public class UserView extends JPanel {

    private static final long serialVersionUID = 1L;

    private BaseUser user;

    public UserView(ActionListener listener) {
        this.user = null;
        createPanel(listener);
    }

    public UserView(BaseUser user, ActionListener listener) {
        this.user = user;
        createPanel(listener);
    }

    private void createPanel(ActionListener listener) {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(740, 70));
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        rightPanel.setPreferredSize(new Dimension(120, 70));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(listener);
        viewButton.setActionCommand(Command.VIEW_USER);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton delButton = new JButton("Delete");
        delButton.addActionListener(listener);
        delButton.setActionCommand(Command.DELETE_USER);
        delButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(viewButton);
        rightPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        rightPanel.add(delButton);
        leftPanel.setLayout(new GridLayout(3, 2));
        leftPanel.setBorder(new EmptyBorder(new Insets(5, 20, 5, 5)));
        leftPanel.add(new JLabel("Name: " + user.getLastName() + ", " + user.getFirstName()));
        leftPanel.add(new JLabel("Email: " + user.getEmail()));
        leftPanel.add(new JLabel("User Type: " + user.getUserType().toString()));
        leftPanel.add(new JLabel("Username: " + user.getUserName()));
        leftPanel.add(new JLabel("Phone: " + user.getPhoneNumber()));
        leftPanel.add(new JLabel("Address: " + user.getAddress()));
        leftPanel.add(new JLabel("ID: " + user.getId()));
        this.setPreferredSize(new Dimension(860, 80));
        this.setMaximumSize(new Dimension(860, 80));
        this.setLayout(new BorderLayout());
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.setVisible(true);
    }

    public BaseUser getUser() {
        return this.user;
    }

}

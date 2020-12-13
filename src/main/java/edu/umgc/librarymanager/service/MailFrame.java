/*
 * Filename: MailFrame.java
 * Author: Scott
 * Date Created: 12/12/2020
 */

package edu.umgc.librarymanager.service;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to display a frame to show that an email is being sent.
 * @author Scott
 */
public class MailFrame extends JFrame {

    private static final long serialVersionUID = -3342051321560699343L;

    public MailFrame() {
        super();
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(new Insets(60, 10, 0, 10)));
        JLabel label = new JLabel("Sending email...");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 200));
        this.setMinimumSize(new Dimension(300, 200));
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void close() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void showFrame() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void hideFrame() {
        this.setVisible(false);
    }

}

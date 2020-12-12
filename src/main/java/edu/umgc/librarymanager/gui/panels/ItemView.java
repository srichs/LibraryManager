/*
 * Filename: ItemView.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.gui.Command;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
 * This class is used to display a single item in a list of items in a
 * scroll pane. It provides two buttons so that the item can be viewed
 * or deleted depending on which is pressed.
 * @author Scott
 */
public class ItemView extends JPanel {

    private static final long serialVersionUID = 1L;

    private ItemViewType type;
    private BaseItem item;

    /**
     * A constructor for the class.
     * @param type The ItemViewType to determine where the ItemView is being displayed.
     * @param listener The ActionListener that will implement the actions performed with the buttons.
     */
    public ItemView(ItemViewType type, ActionListener listener) {
        this.item = null;
        this.type = type;
        createPanel(listener);
    }

    /**
     * A constructor for the class.
     * @param item The BaseItem that is associated with the View.
     * @param type The ItemViewType to determine where the ItemView is being displayed.
     * @param listener The ActionListener that will implement the actions performed with the buttons.
     */
    public ItemView(BaseItem item, ItemViewType type, ActionListener listener) {
        this.item = item;
        this.type = type;
        createPanel(listener);
    }

    private void createPanel(ActionListener listener) {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(740, 70));
        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        rightPanel.setPreferredSize(new Dimension(120, 70));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        createButtonPanel(rightPanel, listener);
        leftPanel.setLayout(new GridLayout(3, 2));
        leftPanel.setBorder(new EmptyBorder(new Insets(5, 20, 5, 5)));
        leftPanel.add(new JLabel("Title: " + item.getTitle())); // TODO make view more detailed

        this.setPreferredSize(new Dimension(860, 100));
        this.setMaximumSize(new Dimension(860, 100));
        this.setLayout(new BorderLayout());
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.setVisible(true);
    }

    private void createButtonPanel(Container container, ActionListener listener) {
        JButton firstBtn = new JButton();
        JLabel label = new JLabel();
        JButton secondBtn = new JButton();
        int btnNumber = 2;
        if (this.type == ItemViewType.View) {
            firstBtn.setText(ItemViewType.View.toString());
            firstBtn.addActionListener(listener);
            firstBtn.setActionCommand(Command.VIEW_ITEM);
            firstBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            secondBtn.setText("Delete");
            secondBtn.addActionListener(listener);
            secondBtn.setActionCommand(Command.DELETE_ITEM);
            secondBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        } else if (this.type == ItemViewType.Renew) {
            btnNumber = 1;
            firstBtn.setText(ItemViewType.Renew.toString());
            firstBtn.addActionListener(listener);
            firstBtn.setActionCommand(Command.ITEM_RENEWED); // TODO
            firstBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        } else if (this.type == ItemViewType.Return_) {
            btnNumber = 1;
            firstBtn.setText(ItemViewType.Return_.toString());
            firstBtn.addActionListener(listener);
            firstBtn.setActionCommand(Command.ITEM_RETURNED); // TODO
            firstBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//            secondBtn.setText("View");
//            secondBtn.addActionListener(listener);
//            secondBtn.setActionCommand(Command.VIEW_ITEM);
//            secondBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        } else if (this.type == ItemViewType.Checkout) {
            btnNumber = 1;
            firstBtn.setText(ItemViewType.Checkout.toString());
            firstBtn.addActionListener(listener);
            firstBtn.setActionCommand(Command.CHECKOUT_ITEM); // TODO
            firstBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
//            secondBtn.setText("View");
//            secondBtn.addActionListener(listener);
//            secondBtn.setActionCommand(Command.VIEW_ITEM);
//            secondBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        } else if (this.type == ItemViewType.Reserve) {
            btnNumber = 1;
            firstBtn.setText(ItemViewType.Reserve.toString());
            firstBtn.addActionListener(listener);
            firstBtn.setActionCommand(Command.ITEM_RESERVED); // TODO
            firstBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setText(this.item.getStatus().toString());
            if (this.item.getStatus() == ItemStatus.Available) {
                label.setForeground(new Color(24, 166, 22));
                firstBtn.setEnabled(true);
                btnNumber = 2;
                secondBtn.setText("View");
                secondBtn.addActionListener(listener);
                secondBtn.setActionCommand(Command.VIEW_ITEM);
                secondBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            } else {
                label.setForeground(Color.RED);
                firstBtn.setEnabled(false);
            }
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(label);
            container.add(Box.createRigidArea(new Dimension(5, 5)));
        } else if (this.type == ItemViewType.Checkout) {
            btnNumber = 0;
        }
        if (btnNumber > 0) {
            container.add(firstBtn);
            if (btnNumber == 2) {
                container.add(Box.createRigidArea(new Dimension(5, 5)));
                container.add(secondBtn);
            }
        }
    }

    public BaseItem getItem() {
        return this.item;
    }

}

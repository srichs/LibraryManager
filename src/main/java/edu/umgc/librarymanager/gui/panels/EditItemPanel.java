/*
 * Filename: EditItemPanel.java
 * Author: Scott
 * Date Created: 11/29/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.gui.Command;
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
 * This class is used to edit an item in the database.
 * @author Scott
 */
public class EditItemPanel extends JPanel {

    private static final long serialVersionUID = -5928750991154945845L;

    private LabelFieldPanel idPanel;
    private LabelFieldPanel deweyPanel;
    private LabelFieldPanel locPanel;
    private LabelFieldPanel purchaseDatePanel;
    private LabelFieldPanel purchasePricePanel;
    private LabelFieldPanel titlePanel;
    private LabelFieldPanel descriptionPanel;
    private LabelFieldPanel summaryPanel;
    private LabelFieldPanel genrePanel;
    private LabelFieldPanel publisherPanel;
    private LabelFieldPanel publishDatePanel;
    private LabelFieldPanel publishLocationPanel;
    private LabelFieldPanel itemTypePanel;
    private LabelFieldPanel statusPanel;
    private LabelFieldPanel checkoutPeriodPanel;
    private JButton button;
    private BaseItem item;

    /**
     * The constructor of the class.
     * @param control The GUIController to act as the listener.
     */
    public EditItemPanel(GUIController control) {
        super();
        this.idPanel = new LabelFieldPanel();
        this.deweyPanel = new LabelFieldPanel();
        this.locPanel = new LabelFieldPanel();
        this.purchaseDatePanel = new LabelFieldPanel();
        this.purchasePricePanel = new LabelFieldPanel();
        this.titlePanel = new LabelFieldPanel();
        this.descriptionPanel = new LabelFieldPanel();
        this.summaryPanel = new LabelFieldPanel();
        this.genrePanel = new LabelFieldPanel();
        this.publisherPanel = new LabelFieldPanel();
        this.publishDatePanel = new LabelFieldPanel();
        this.publishLocationPanel = new LabelFieldPanel();
        this.itemTypePanel = new LabelFieldPanel();
        this.statusPanel = new LabelFieldPanel();
        this.checkoutPeriodPanel = new LabelFieldPanel();
        this.button = new JButton("Update");
        createPanel(control);
    }

    public void setUser(BaseItem item) {
        this.item = item;
        setItemInfo();
    }

    /**
     * Sets the item's information to the form fields.
     * @param user The item to set the information from.
     */
    private void setItemInfo() {
        if (item == null) {
            return;
        }
        this.idPanel.setText(String.valueOf(item.getId()));
        this.deweyPanel.setText(item.getClassificationGroup().getDewey().getCode());
        this.locPanel.setText(item.getClassificationGroup().getLOC().getCode());
        this.purchaseDatePanel.setText(item.getPurchaseDate().toString());
        this.purchasePricePanel.setText("$" + item.getPurchasePrice().toString());
        this.titlePanel.setText(item.getTitle());
        this.descriptionPanel.setText(item.getDescription());
        this.summaryPanel.setText(item.getSummary());
        this.genrePanel.setText(item.getGenre());
        this.publisherPanel.setText(item.getPublisher().getPublisher());
        this.publishDatePanel.setText(item.getPublisher().getPublishDate().toString());
        this.publishLocationPanel.setText(item.getPublisher().getPublishLocation());
        this.itemTypePanel.setText(item.getItemType().toString());
        this.statusPanel.setText(item.getStatus().toString());
        this.checkoutPeriodPanel.setText(item.getCheckoutPeriod().toString());
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 400));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        addPanel(this.idPanel, fieldPanel, "ID", "", true);
        addPanel(this.deweyPanel, fieldPanel, "Dewey Decimal Code", "", true);
        addPanel(this.locPanel, fieldPanel, "Library of Congress Code", "", true);
        addPanel(this.purchaseDatePanel, fieldPanel, "Purchase Date", "", true);
        addPanel(this.purchasePricePanel, fieldPanel, "Purchase Price", "", true);
        addPanel(this.titlePanel, fieldPanel, "Title", "", false);
        addPanel(this.descriptionPanel, fieldPanel, "Description", "", false);
        addPanel(this.summaryPanel, fieldPanel, "Summary", "", false);
        addPanel(this.genrePanel, fieldPanel, "Genre", "", true);
        addPanel(this.publisherPanel, fieldPanel, "Publisher", "", true);
        addPanel(this.publishDatePanel, fieldPanel, "Publish Date", "", true);
        addPanel(this.publishLocationPanel, fieldPanel, "Publish Location", "", true);
        addPanel(this.itemTypePanel, fieldPanel, "Item Type", "", true);
        addPanel(this.statusPanel, fieldPanel, "Item Status", "", true);
        addPanel(this.checkoutPeriodPanel, fieldPanel, "Checkout Period", "", true);
        addButton(this.button, fieldPanel, Command.MANAGE_UPDATE_ITEM, control);
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
     * Tries to create a new item with the information in the form and returns the item if created.
     * @return The BaseItem if added or null if not added.
     */
    public BaseItem tryUpdate() {
        if (checkFields()) {
            if (checkInfoDiff(item)) {
                BaseItem updatedItem = item;
                updatedItem.setTitle(this.titlePanel.getText());
                updatedItem.setDescription(this.descriptionPanel.getText());
                updatedItem.setSummary(this.summaryPanel.getText());
                setNew();
                return updatedItem;
            }
        }
        return null;
    }

    private boolean checkInfoDiff(BaseItem item) {
        if (this.titlePanel.getTextField().getText().equals(item.getTitle())
                && this.descriptionPanel.getTextField().getText().equals(item.getDescription())
                && this.summaryPanel.getTextField().getText().equals(item.getSummary())) {
            DialogUtil.informationMessage("No update was necessary.", "Update Information");
            return false;
        }
        return true;
    }

    private boolean checkFields() {
        if (this.titlePanel.getTextField().getText().equals("")
                || this.descriptionPanel.getTextField().getText().equals("")
                || this.summaryPanel.getTextField().getText().equals("")) {
            DialogUtil.warningMessage("All fields must be filled.", "Update Failure");
            return false;
        }
        return true;
    }

    /**
     * Sets all of the editable fields blank.
     */
    public void setNew() {
        this.titlePanel.setText("");
        this.descriptionPanel.setText("");
        this.summaryPanel.setText("");
    }

}

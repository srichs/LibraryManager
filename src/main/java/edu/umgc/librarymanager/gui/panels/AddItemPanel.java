/*
 * Filename: AddItemPanel.java
 * Author: Scott
 * Date Created: 11/29/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.Book;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.Classification;
import edu.umgc.librarymanager.data.model.item.ClassificationGroup;
import edu.umgc.librarymanager.data.model.item.Ebook;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.item.ItemType;
import edu.umgc.librarymanager.data.model.item.Movie;
import edu.umgc.librarymanager.data.model.item.PublishData;
import edu.umgc.librarymanager.data.model.item.VideoGame;
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
import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to edit an item to the database.
 * @author Scott
 */
public class AddItemPanel extends JPanel {

    private static final long serialVersionUID = -5928750991154945845L;

    private JScrollPane scrollPane;
    private JPanel itemPanel;
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
    private JComboBox<ItemType> typeBox;
    private JButton button;

    /**
     * The constructor of the class.
     * @param control The GUIController to act as the listener.
     */
    public AddItemPanel(GUIController control) {
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
        this.typeBox = new JComboBox<ItemType>(ItemType.values());
        this.button = new JButton("Update");
        createPanel(control);
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setSize(new Dimension(400, 400));
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        addPanel(this.idPanel, fieldPanel, "ID", "auto", true);
        addPanel(this.purchaseDatePanel, fieldPanel, "Purchase Date", "auto", true);
        addPanel(this.purchasePricePanel, fieldPanel, "Purchase Price", "", false);
        addPanel(this.deweyPanel, fieldPanel, "DDC Code", "", false);
        addPanel(this.locPanel, fieldPanel, "LOC Code", "", false);
        addPanel(this.titlePanel, fieldPanel, "Title", "", false);
        addPanel(this.descriptionPanel, fieldPanel, "Description", "", false);
        addPanel(this.summaryPanel, fieldPanel, "Summary", "", false); // TODO make textarea?
        addPanel(this.genrePanel, fieldPanel, "Genre", "auto", true);
        addPanel(this.publisherPanel, fieldPanel, "Publisher", "", false);
        addPanel(this.publishDatePanel, fieldPanel, "Publish Date", "", true); // TODO find good way to get date input
        addPanel(this.publishLocationPanel, fieldPanel, "Publish Location", "", false);
        this.typeBox.setSelectedItem(ItemType.Book);
        typePanel(this.typeBox, fieldPanel);
        addButton(this.button, fieldPanel, Command.CREATE_ITEM, control);

        this.itemPanel = new JPanel();
        this.itemPanel.setLayout(new FlowLayout()); // TODO correct scroll pane
        this.scrollPane = new JScrollPane(this.itemPanel);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.itemPanel.add(fieldPanel);
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
        mainPanel.add(this.scrollPane);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void typePanel(JComboBox<ItemType> typeBox, Container container) {
        JLabel label = new JLabel("Item Type");
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
     * Tries to create a new item with the information in the form and returns the
     * item if created.
     * @return The BaseItem if added or null if not added.
     */
    public BaseItem tryCreate() {
        if (checkFields()) {
            ClassificationGroup cg = new ClassificationGroup();
            Classification ddc = new Classification(this.deweyPanel.getText(), ClassType.DeweyDecimal);
            Classification loc = new Classification(this.locPanel.getText(), ClassType.LibraryOfCongress);
            cg.setDewey(ddc);
            cg.setLOC(loc);
            PublishData pd = new PublishData(this.publisherPanel.getText(), ZonedDateTime.now(),
                    this.publishLocationPanel.getText());
            Period per = null;
            BaseItem addedItem = null;
            if (this.typeBox.getSelectedItem() == ItemType.Book) {
                addedItem = new Book(cg, ZonedDateTime.now(), this.descriptionPanel.getText(),
                        new BigDecimal(this.purchasePricePanel.getText()), this.titlePanel.getText(),
                        pd, this.genrePanel.getText(), this.summaryPanel.getText(), ItemStatus.Available,
                        per, "", "");
            } else if (this.typeBox.getSelectedItem() == ItemType.EBook) {
                addedItem = new Ebook(cg, ZonedDateTime.now(), this.descriptionPanel.getText(),
                        new BigDecimal(this.purchasePricePanel.getText()), this.titlePanel.getText(),
                        pd, this.genrePanel.getText(), this.summaryPanel.getText(), ItemStatus.Available,
                        per, "", "");
            } else if (this.typeBox.getSelectedItem() == ItemType.Movie) {
                addedItem = new Movie(cg, ZonedDateTime.now(), this.descriptionPanel.getText(),
                        new BigDecimal(this.purchasePricePanel.getText()), this.titlePanel.getText(),
                        pd, this.genrePanel.getText(), this.summaryPanel.getText(), ItemStatus.Available, per);
            } else if (this.typeBox.getSelectedItem() == ItemType.VideoGame) {
                addedItem = new VideoGame(cg, ZonedDateTime.now(), this.descriptionPanel.getText(),
                        new BigDecimal(this.purchasePricePanel.getText()), this.titlePanel.getText(),
                        pd, this.genrePanel.getText(), this.summaryPanel.getText(), ItemStatus.Available, per);
            }
            return addedItem;
        }
        return null;
    }

    private boolean checkFields() {
        if (this.titlePanel.getTextField().getText().equals("")
                || this.descriptionPanel.getTextField().getText().equals("")
                || this.summaryPanel.getTextField().getText().equals("")
                || this.deweyPanel.getTextField().getText().equals("")
                || this.locPanel.getTextField().getText().equals("")
                || this.purchasePricePanel.getTextField().getText().equals("")
                || this.publisherPanel.getTextField().getText().equals("")
                || this.publishLocationPanel.getTextField().getText().equals("")) {
            DialogUtil.warningMessage("All fields must be filled.", "Update Failure");
            return false;
        }
        return true;
    }

    /**
     * Sets all of the editable fields blank.
     */
    public void setNew() {
        this.purchasePricePanel.setText("");
        this.deweyPanel.setText("");
        this.locPanel.setText("");
        this.titlePanel.setText("");
        this.descriptionPanel.setText("");
        this.summaryPanel.setText("");
        this.publisherPanel.setText("");
        this.publishLocationPanel.setText("");
    }

}

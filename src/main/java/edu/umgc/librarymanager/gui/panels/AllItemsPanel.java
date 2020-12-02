/*
 * Filename: AllItemsPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.GUIController;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to view all items of the Library system. It uses the ItemView class
 * to display each item in a list of items in a scroll pane. Each individual item can
 * be viewed or deleted. There is also an option to search the items title and to
 * add a new item.
 * @author Scott
 */
public class AllItemsPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 2814076991339926348L;
    private static final Logger LOG = LogManager.getLogger(AllItemsPanel.class);

    private JScrollPane scrollPane;
    private JPanel itemPanel;
    private JTextField searchField;
    private List<BaseItem> items;
    private BaseItem selectedItem;
    private GUIController control;

    /**
     * The constructor of the class.
     * @param control The GUIController of the application.
     */
    public AllItemsPanel(GUIController control) {
        this.items = null;
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
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setActionCommand(Command.SEARCH);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setActionCommand(Command.CLEAR);
        JButton addUserButton = new JButton("Add Item");
        addUserButton.addActionListener(control);
        addUserButton.setActionCommand(Command.ADD_ITEM);
        searchPanel.add(new JLabel("Search by Title "));
        searchPanel.add(this.searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(Box.createRigidArea(new Dimension(100, 40)));
        searchPanel.add(addUserButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        this.itemPanel = new JPanel();
        this.itemPanel.setLayout(new BoxLayout(this.itemPanel, BoxLayout.Y_AXIS));
        this.scrollPane = new JScrollPane(this.itemPanel);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        fillPane(this.items);

        this.scrollPane.revalidate();
        this.scrollPane.repaint();
        mainPanel.add(this.scrollPane);
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Used to fill the scroll pane with the provided List of items.
    private void fillPane(List<BaseItem> list) {
        if (list == null) {
            return;
        }
        this.itemPanel.removeAll();
        for (int i = 0; i < list.size(); i++) {
            ItemView view = new ItemView(list.get(i), new ItemActionListener(list.get(i)));
            this.itemPanel.add(view);
        }
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
    }

    // Used to search the list then provide the items that match the search characters.
    private void searchList(String title) {
        List<BaseItem> newList = new ArrayList<BaseItem>();
        for (int i = 0; i < this.items.size(); i++) {
            String t = this.items.get(i).getTitle();
            if (t.toLowerCase().contains(title.toLowerCase())) {
                newList.add(this.items.get(i));
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
            fillPane(this.items);
        }
    }

    /**
     * Sets the items list of the class and clears the search panel then fills the scroll pane.
     * @param items The List of items of the system.
     */
    public void setItems(List<BaseItem> items) {
        this.items = items;
        this.searchField.setText("");
        fillPane(items);
    }

    public BaseItem getSelectedItem() {
        return this.selectedItem;
    }

    public void setSelectedItem(BaseItem item) {
        this.selectedItem = item;
    }

    public GUIController getController() {
        return this.control;
    }

    /**
     * An inner class to listen to the users in the scroll pane and fire actions based on which
     * button is pressed in an item's panel.
     * @author Scott
     */
    private class ItemActionListener implements ActionListener {
        private BaseItem item;

        ItemActionListener(BaseItem item) {
            this.item = item;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setSelectedItem(this.item);
            if (Command.VIEW_ITEM.equals(e.getActionCommand())) {
                LOG.info("View Item button pressed.");
                //ControlHelper.viewUser(getController().getFrame(), this.item); // TODO
            } else if (Command.DELETE_ITEM.equals(e.getActionCommand())) {
                LOG.info("Delete Item button pressed.");
                //ControlHelper.deleteUser(getController(), this.item);
            }
        }
    }

}

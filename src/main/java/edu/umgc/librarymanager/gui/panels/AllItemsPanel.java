/*
 * Filename: AllItemsPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.ItemField;
import edu.umgc.librarymanager.data.access.Pagination;
import edu.umgc.librarymanager.data.access.SearchData;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.service.LibrarianServices;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.hibernate.search.exception.EmptyQueryException;

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
    private JPanel itemPanel, searchPanel;
    private JTextField searchField;
    private BaseItem selectedItem;
    private PaginationPanel<BaseItem> paginationPanel;
    private GUIController control;
    private ItemViewType viewType = ItemViewType.View;

    /**
     * The constructor of the class.
     * @param control The GUIController of the application.
     */
    public AllItemsPanel(GUIController control) {
        this.paginationPanel = new PaginationPanel<BaseItem>(new SearchData<BaseItem>(
                null, null, new Pagination(20, 0, 1), BaseItem.class), this);
        this.control = control;
        createPanel(control);
    }

    /**
     * Creates the panel to be displayed by the class.
     */
    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        searchPanel = new JPanel(new FlowLayout());
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
        update();

        this.scrollPane.revalidate();
        this.scrollPane.repaint();
        mainPanel.add(this.scrollPane, BorderLayout.CENTER);
        mainPanel.add(this.paginationPanel, BorderLayout.SOUTH);
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
            ItemView view = new ItemView(list.get(i), viewType, new ItemActionListener(list.get(i)));
            this.itemPanel.add(view);
        }
        this.scrollPane.getVerticalScrollBar().setValue(0);
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
    }

    // Used to search the list then provide the items that match the search characters.
    private void searchList(String title) {
        String[] fields = {ItemField.Title.toString()};
        SearchData<BaseItem> sd = new SearchData<BaseItem>(fields, title,
                new Pagination(20, 0, 1), BaseItem.class);
        this.paginationPanel.setSearchData(sd);
        update();
    }

    /**
     * Resets the search bar and the displayed items.
     */
    public void reset() {
        reset(Command.MANAGE_ITEMS);
    }
    
    public void reset(String commandType) {
        this.searchField.setText("");
        SearchData<BaseItem> search = null;
        String[] fields = {ItemField.Status.toString()};
        switch(commandType) {
            case Command.CHECKOUT_ITEM:
                viewType = ItemViewType.Checkout;
                searchPanel.setVisible(false);
                search = new SearchData<BaseItem>(
                    fields, ItemStatus.OnHold, new Pagination(20, 0, 1), BaseItem.class);
                break;
            case Command.RETURN_ITEM:
                viewType = ItemViewType.Return_;
                searchPanel.setVisible(false);
                search = new SearchData<BaseItem>(
                    fields, ItemStatus.CheckedOut, new Pagination(20, 0, 1), BaseItem.class);
                break;
            case Command.MANAGE_ITEMS:
            default:    
                viewType = ItemViewType.View;
                searchPanel.setVisible(true);
                search = new SearchData<BaseItem>(
                    null, null, new Pagination(20, 0, 1), BaseItem.class);
                break;
        }
        
        this.paginationPanel.setSearchData(search);
        update();
    }

    /**
     * Called to update the displayed items in the panel, it also updates the pagination information.
     */
    public void update() {
        try {
            this.paginationPanel.getSearchData().runSearch();
        } catch (EmptyQueryException ex) {
            DialogUtil.informationMessage("No results were found.", "No Results Found");
            return;
        }
        if (this.paginationPanel.getSearchData().getResults().size() == 0) {
            DialogUtil.informationMessage("No results were found.", "No Results Found");
            return;
        }
        
        this.paginationPanel.update();
        fillPane(this.paginationPanel.getSearchData().getResults());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Command.SEARCH.equals(e.getActionCommand())) {
            LOG.info("Search button pressed.");
            if (!this.searchField.getText().equals("")) {
                searchList(this.searchField.getText());
            } else {
                DialogUtil.warningMessage("Please enter the search terms into the search bar.", "No Search Terms");
            }
        } else if (Command.CLEAR.equals(e.getActionCommand())) {
            LOG.info("Clear button pressed.");
            reset();
        } else if (PaginationPanel.NEXT_PRESS.equals(e.getActionCommand())) {
            LOG.info("Next button pressed.");
            this.paginationPanel.nextPressed();
            fillPane(this.paginationPanel.getSearchData().getResults());
        } else if (PaginationPanel.PREVIOUS_PRESS.equals(e.getActionCommand())) {
            LOG.info("Previous button pressed.");
            this.paginationPanel.previousPressed();
            fillPane(this.paginationPanel.getSearchData().getResults());
        } else if (PaginationPanel.GOTO_PRESS.equals(e.getActionCommand())) {
            LOG.info("Go To button pressed.");
            this.paginationPanel.goToPressed();
            fillPane(this.paginationPanel.getSearchData().getResults());
        }
    }

    public PaginationPanel<BaseItem> getPaginationPanel() {
        return this.paginationPanel;
    }

    public void setPaginationPanel(PaginationPanel<BaseItem> pagePanel) {
        this.paginationPanel = pagePanel;
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
                LibrarianServices.viewItem(getController().getFrame(), this.item); // TODO
            } else if (Command.DELETE_ITEM.equals(e.getActionCommand())) {
                LOG.info("Delete Item button pressed.");
                LibrarianServices.deleteItem(getController(), this.item);
            } else if (Command.ITEM_RETURNED.equals(e.getActionCommand())) {
                LOG.info("Return button pressed.");
                LibrarianServices.returnItem(getController(), this.item);
            } else if (Command.CHECKOUT_ITEM.equals(e.getActionCommand())) {
                LOG.info("Checkout button pressed.");
                LibrarianServices.checkOutItem(getController(), this.item);
            }
        }
    }

}

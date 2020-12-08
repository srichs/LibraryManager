/*
 * Filename: AllItemsPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.Pagination;
import edu.umgc.librarymanager.data.access.SearchData;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.service.PatronServices;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to view the search results of the Library system. It uses the ItemView class
 * to display each item in a list of items in a scroll pane. Each individual item can
 * have certian actions done on it.
 * @author Scott
 */
public class SearchResultsPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LogManager.getLogger(AllItemsPanel.class);

    private JScrollPane scrollPane;
    private JPanel itemPanel;
    private BaseItem selectedItem;
    private PaginationPanel<BaseItem> paginationPanel;
    private GUIController control;

    /**
     * The constructor of the class.
     * @param control The GUIController of the application.
     */
    public SearchResultsPanel(GUIController control) {
        this.paginationPanel = new PaginationPanel<BaseItem>(new SearchData<BaseItem>(
                null, null, new Pagination(20, 0, 1), BaseItem.class), this);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        JButton backButton = new JButton("Back");
        backButton.setActionCommand(Command.SEARCH);
        backButton.addActionListener((ActionListener) control);
        btnPanel.add(backButton);
        this.paginationPanel.add(btnPanel, BorderLayout.WEST);
        this.control = control;
        createPanel(control);
    }

    /**
     * Creates the panel to be displayed by the class.
     */
    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
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
            ItemView view = new ItemView(list.get(i), ItemViewType.Reserve, new ItemActionListener(list.get(i)));
            this.itemPanel.add(view);
        }
        this.scrollPane.getVerticalScrollBar().setValue(0);
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
    }

    public void update() {
        this.paginationPanel.update();
        fillPane(this.paginationPanel.getSearchData().getResults());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PaginationPanel.NEXT_PRESS.equals(e.getActionCommand())) {
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
            if (Command.ITEM_RESERVED.equals(e.getActionCommand())) {
                LOG.info("Reserve button pressed.");
                PatronServices.reserveItem(getController(), this.item);
            }
        }
    }

}

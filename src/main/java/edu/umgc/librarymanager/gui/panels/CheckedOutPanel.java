/*
 * Filename: AllItemsPanel.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.service.PatronServices;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to view the items a logged in user has checked out. It uses the ItemView class
 * to display each item in a list of items in a scroll pane. Each individual item can
 * have certian actions done on it.
 * @author Scott
 */
public class CheckedOutPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LogManager.getLogger(AllItemsPanel.class);

    private JScrollPane scrollPane;
    private JPanel itemPanel;
    private BaseItem selectedItem;
    private GUIController control;

    /**
     * The constructor of the class.
     * @param control The GUIController of the application.
     */
    public CheckedOutPanel(GUIController control) {
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
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Used to fill the scroll pane with the provided List of items.
    private void fillPane(List<BaseTransaction> list) {
        if (list == null) {
            return;
        }
        this.itemPanel.removeAll();
        for (int i = 0; i < list.size(); i++) {
            ItemView view = new ItemView((BaseItem) list.get(i).getItem(), ItemViewType.Renew,
                    new ItemActionListener((BaseItem) list.get(i).getItem()));
            this.itemPanel.add(view);
        }
        this.scrollPane.getVerticalScrollBar().setValue(0);
        this.scrollPane.revalidate();
        this.scrollPane.repaint();
    }

    public void update() {
        fillPane(PatronServices.getCheckedOutItems(this.control.getCurrentUser()));
    }

    public JPanel getItemPanel() {
        return this.itemPanel;
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
            if (Command.ITEM_RENEWED.equals(e.getActionCommand())) {
                LOG.info("Renew button pressed.");
                PatronServices.renewItem(getController(), this.item); // TODO
            }
        }
    }

}

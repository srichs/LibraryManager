/*
 * Filename: PaginationPanel.java
 * Author: Scott
 * Date Created: 12/5/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.Pagination;
import edu.umgc.librarymanager.data.access.SearchData;
import edu.umgc.librarymanager.gui.DialogUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * This class creates a panel that is used to control the pagination of the
 * results of a search or query.
 * @param <T> The Type of elements that will be displayed with the pagination. The types can be BaseItem,
 * BaseUser, or BaseTransaction.
 * @author Scott
 */
public class PaginationPanel<T> extends JPanel {

    /**
     * The action command String for the press of the Previous button.
     */
    public static final String PREVIOUS_PRESS = "previous_page_press";
    /**
     * The action command String for the press of the Go To button.
     */
    public static final String GOTO_PRESS = "goto_page_press";
    /**
     * The action command String for the press of the Next button.
     */
    public static final String NEXT_PRESS = "next_page_press";
    private static final long serialVersionUID = 7321570363090135698L;

    private SearchData<T> searchData;
    private JTextField pageField;
    private JTextField totalPagesField;
    private JButton nextButton;
    private JButton previousButton;
    private JButton goToButton;
    private JLabel resultsLabel;
    private ActionListener actionListener;

    /**
     * The constructor of the class.
     * @param clazz The Class of the Type of elements that will be displayed with the pagination class.
     * @param listener An Action Listener that will listen for the button presses of the panel.
     */
    public PaginationPanel(Class<T> clazz, ActionListener listener) {
        this.searchData = new SearchData<T>(null, null, new Pagination(20, 0, 1), clazz);
        this.actionListener = listener;
        createPanel();
    }

    /**
     * The constructor of the class.
     * @param searchData The SearchData that the pagination panel will use to control paging.
     * @param listener An Action Listener that will listen for the button presses of the panel.
     */
    public PaginationPanel(SearchData<T> searchData, ActionListener listener) {
        this.searchData = searchData;
        this.actionListener = listener;
        createPanel();
    }

    private void createPanel() {
        this.pageField = new JTextField(3);
        this.totalPagesField = new JTextField(3);
        this.totalPagesField.setEditable(false);
        this.nextButton = new JButton("Next");
        this.previousButton = new JButton("Previous");
        this.goToButton = new JButton("Go To");
        this.resultsLabel = new JLabel("0 results found");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(880, 50));
        this.setMinimumSize(new Dimension(880, 50));
        JPanel pagingPanel = new JPanel(new FlowLayout());
        pagingPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        this.previousButton.addActionListener(this.actionListener);
        this.previousButton.setActionCommand(PREVIOUS_PRESS);
        this.nextButton.addActionListener(this.actionListener);
        this.nextButton.setActionCommand(NEXT_PRESS);
        this.goToButton.addActionListener(this.actionListener);
        this.goToButton.setActionCommand(GOTO_PRESS);
        pagingPanel.add(previousButton);
        pagingPanel.add(Box.createRigidArea(new Dimension(15, 15)));
        pagingPanel.add(goToButton);
        pagingPanel.add(new JLabel("Page"));
        pagingPanel.add(this.pageField);
        pagingPanel.add(new JLabel(" of "));
        pagingPanel.add(this.totalPagesField);
        pagingPanel.add(Box.createRigidArea(new Dimension(15, 15)));
        pagingPanel.add(nextButton);
        JPanel resultsPanel = new JPanel(new FlowLayout());
        resultsPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        resultsPanel.add(this.resultsLabel);
        this.add(pagingPanel, BorderLayout.CENTER);
        this.add(resultsPanel, BorderLayout.EAST);
    }

    public SearchData<T> getSearchData() {
        return this.searchData;
    }

    public void setSearchData(SearchData<T> searchData) {
        this.searchData = searchData;
    }

    public JTextField getPageField() {
        return this.pageField;
    }

    public JTextField getTotalPagesField() {
        return this.totalPagesField;
    }

    public JLabel getResultsLabel() {
        return this.resultsLabel;
    }

    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    /**
     * This is to be called from the class that is the action listener when the Next button is pressed.
     */
    public void nextPressed() {
        this.searchData.getPagination().nextPage();
        this.searchData.runSearch();
        update();
    }

    /**
     * This is to be called from the class that is the action listener when the Previous button is pressed.
     */
    public void previousPressed() {
        this.searchData.getPagination().previousPage();
        this.searchData.runSearch();
        update();
    }

    /**
     * This is to be called from the class that is the action listener when the Go To button is pressed.
     * The Go To button allows the user to enter a page so they can go directly to that page of data.
     */
    public void goToPressed() {
        if (this.pageField.getText().equals("")) {
            DialogUtil.warningMessage("There is no page entered in the go to page field.",
                    "No Page Entered");
        } else {
            if (this.pageField.getText().matches("[0-9]{1,3}")) {
                if (Integer.valueOf(this.pageField.getText()) > this.searchData.getPagination().getLastPageNumber()
                        || Integer.valueOf(this.pageField.getText()) < 1) {
                    DialogUtil.warningMessage("The entered page number is outside of the page range.",
                            "Invalid Page Number");
                } else {
                    this.searchData.getPagination().setDesiredPage(Integer.valueOf(this.pageField.getText()));
                    this.searchData.runSearch();
                    update();
                }
            } else {
                DialogUtil.warningMessage("The page entered in the go to page field is invalid.",
                        "Invalid Page Number");
            }
        }
    }

    /**
     * Called to update the pagination panel, it sets the textfields with updated data and enables or disables
     * the correct GUI elements.
     */
    public void update() {
        multiPageResults();
        this.pageField.setText(String.valueOf(this.searchData.getPagination().getDesiredPage()));
        this.totalPagesField.setText(String.valueOf(this.searchData.getPagination().getLastPageNumber()));
        this.resultsLabel.setText(
                String.valueOf(this.searchData.getPagination().getTotalCount()) + " results found");
        if (this.searchData.getPagination().getLastPageNumber() == 1) {
            onePageResults();
        } else {
            multiPageResults();
        }
        if (this.searchData.getPagination().getDesiredPage() == 1) {
            onFirstPage();
        }
        if (this.searchData.getPagination().getDesiredPage() == this.searchData.getPagination().getLastPageNumber()) {
            onLastPage();
        }
        this.repaint();
    }

    /**
     * Disables all of the buttons and the page field, it's used when there is only one page of results.
     */
    public void onePageResults() {
        this.nextButton.setEnabled(false);
        this.goToButton.setEnabled(false);
        this.previousButton.setEnabled(false);
        this.pageField.setEditable(false);
    }

    /**
     * Sets all of the buttons in the panel to enabled, it's used when there are multiple pages of results.
     */
    public void multiPageResults() {
        this.nextButton.setEnabled(true);
        this.goToButton.setEnabled(true);
        this.previousButton.setEnabled(true);
        this.pageField.setEditable(true);
    }

    public void onFirstPage() {
        this.previousButton.setEnabled(false);
    }

    public void onLastPage() {
        this.nextButton.setEnabled(false);
    }

}

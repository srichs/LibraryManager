/*
 * Filename: PatronServices.java
 * Author: Scott
 * Date Created: 12/2/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.data.DatabaseTest;
import edu.umgc.librarymanager.data.access.AdvSearchPart;
import edu.umgc.librarymanager.data.access.ItemField;
import edu.umgc.librarymanager.data.access.Pagination;
import edu.umgc.librarymanager.data.access.SearchData;
import edu.umgc.librarymanager.data.access.TransactionDAO;
import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.Library;
import edu.umgc.librarymanager.data.model.TransactionType;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.search.exception.EmptyQueryException;

/**
 * This class provides methods related to the Patron services.
 * @author Scott
 */
public final class PatronServices {

    private PatronServices() {}

    public static void viewProfile(GUIController control) {
        control.getFrame().getPanelComp().getUserProfilePanel().setUser(control.getCurrentUser());
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.PROFILE);
    }

    public static void viewSearch(GUIController control) {
        control.getFrame().getPanelComp().getSearchPanel().reset();
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.SEARCH);
    }

    public static void viewAdvancedSearch(GUIController control) {
        control.getFrame().getPanelComp().getAdvancedSearchPanel().reset();
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.ADVANCED_SEARCH);
    }

    public static void clearAdvancedSearch(GUIController control) {
        control.getFrame().getPanelComp().getAdvancedSearchPanel().reset();
    }

    public static void viewAdvancedSearchResults(GUIController control) {
        List<AdvSearchPart> list = control.getFrame().getPanelComp().getAdvancedSearchPanel().buildAdvanceSearch();
        ItemStatus filter = null;
        if (control.getFrame().getPanelComp().getAdvancedSearchPanel().getStatusBox().getSelectedIndex() != 0) {
            filter = ItemStatus.stringToItemStatus((String) control.getFrame().getPanelComp()
                    .getAdvancedSearchPanel().getStatusBox().getSelectedItem());
        }
        if (list != null) {
            control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().setSearchData(
                    new SearchData<BaseItem>(list, new Pagination(20, 0, 1), BaseItem.class, filter));
            try {
                control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel()
                        .getSearchData().runSearch();
            } catch (EmptyQueryException ex) {
                control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().setSearchData(
                        new SearchData<BaseItem>(null, null, new Pagination(20, 0, 1), BaseItem.class));
                DialogUtil.informationMessage("No results were found.", "No Results Found");
                return;
            }
            if (control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().getSearchData()
                    .getResults() == null) {
                DialogUtil.informationMessage("No results were found.", "No Results Found");
                return;
            } else if (control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().getSearchData()
                    .getResults().size() == 0) {
                DialogUtil.informationMessage("No results were found.", "No Results Found");
                return;
            }
            control.getFrame().getPanelComp().getSearchResultsPanel().update();
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.SEARCH_RESULTS);
        }
    }

    /**
     * This method is used to view the search results.
     * @param control The GUIController of the application.
     */
    public static void viewSearchResults(GUIController control) {
        String[] fields = {ItemField.Title.toString(), ItemField.Genre.toString(),
                ItemField.PublishLocation.toString(), ItemField.Publisher.toString(),
                ItemField.Summary.toString(), ItemField.Description.toString()};
        String terms = control.getFrame().getPanelComp().getSearchPanel().getSearchField().getText();
        control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().setSearchData(
                new SearchData<BaseItem>(fields, terms, new Pagination(20, 0, 1), BaseItem.class));
        try {
            control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel()
                    .getSearchData().runSearch();
        } catch (EmptyQueryException ex) {
            control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().setSearchData(
                    new SearchData<BaseItem>(null, null, new Pagination(20, 0, 1), BaseItem.class));
            DialogUtil.informationMessage("No results were found.", "No Results Found");
            return;
        }
        if (control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().getSearchData()
                .getResults() == null) {
            DialogUtil.informationMessage("No results were found.", "No Results Found");
            return;
        } else if (control.getFrame().getPanelComp().getSearchResultsPanel().getPaginationPanel().getSearchData()
                .getResults().size() == 0) {
            DialogUtil.informationMessage("No results were found.", "No Results Found");
            return;
        }
        control.getFrame().getPanelComp().getSearchResultsPanel().update();
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.SEARCH_RESULTS);
    }

    /**
     * This method is used to reserve an item.
     * @param control The GUIController of the applicaton.
     * @param item The BaseItem to be reserved.
     */
    public static void reserveItem(GUIController control, BaseItem item) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to reserve:\n"
                + item.getTitle() + "?", "Reserve Item", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            item.setStatus(ItemStatus.OnHold);
            Library library = DatabaseTest.getLibrary();
            BaseTransaction transaction = new BaseTransaction(library, item, control.getCurrentUser(),
                    ZonedDateTime.now(), ZonedDateTime.now(), 0.0, ZonedDateTime.now(), 0, TransactionType.Reserve);
            TransactionDAO transDAO = new TransactionDAO();
            try {
                transDAO.openSessionwithTransaction();
                transDAO.persist(transaction);
                transDAO.closeSessionwithTransaction();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } finally {
                transDAO.closeSession();
            }
            control.getFrame().getPanelComp().getSearchResultsPanel().update();
        }
    }

    /**
     * Used to display a user's checked out items panel.
     * @param control The GUIController of the application.
     */
    public static void viewCheckedOutItems(GUIController control) {
        control.getFrame().getPanelComp().getCheckedOutPanel().update();
        if (control.getFrame().getPanelComp().getCheckedOutPanel().getItemPanel().getComponentCount() == 0) {
            DialogUtil.informationMessage("You have no items currently checked out.", "No Checked Out Items");
        } else {
            control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.CHECKED_OUT);
        }
    }

    /**
     * Gets a List of a given user's currently checked out items.
     * @param user The BaseUser to find the checked out items for.
     * @return A List of BaseTransactions that contain the checked out items.
     */
    public static List<BaseTransaction> getCheckedOutItems(BaseUser user) {
        List<BaseTransaction> results = null;
        TransactionDAO transDAO = new TransactionDAO();
        try {
            transDAO.openSessionwithTransaction();
            results = transDAO.findCheckedOutByUser(user);
            transDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            transDAO.closeSession();
        }
        return results;
    }

    /**
     * Used to renew an item that a patron has checked out.
     * @param control The GUIController of the application.
     * @param item The BaseItem that is being renewed.
     */
    public static void renewItem(GUIController control, BaseItem item) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to renew the checked out "
                + "item:\n\n" + item.getTitle() + "\n\nfor an additional two weeks? This will incur a fee of $3.00.\n",
                "Renew Item", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            TransactionDAO transDAO = new TransactionDAO();
            try {
                transDAO.openSessionwithTransaction();
                List<BaseTransaction> results = transDAO.findCheckedOutByUser(control.getCurrentUser());
                BaseTransaction trans = null;
                if (results != null) {
                    for (int i = 0; i < results.size(); i++) {
                        if (results.get(i).getItem().getId() == item.getId()) {
                            trans = results.get(i);
                            break;
                        }
                    }
                }
                if (trans.getRenewCount() == 3) {
                    DialogUtil.warningMessage("You have already renewed this item the maximum amount of times. "
                            + "Please return this item by: " + trans.getDueDate().toLocalDate(),
                            "Unable to Renew Item");
                } else {
                    trans.setFee(new BigDecimal(trans.getFee()).add(new BigDecimal("3.0")));
                    trans.setRenewCount(trans.getRenewCount() + 1);
                    trans.setRenewDate(ZonedDateTime.now());
                    trans.setTransactionType(TransactionType.Renew);
                    trans.setDueDate(trans.getDueDate().plusDays(14));
                    transDAO.update(trans);
                    DialogUtil.informationMessage("The new due date for this item is "
                            + trans.getDueDate().toLocalDate(), "Due Date");
                }
                transDAO.closeSessionwithTransaction();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } finally {
                transDAO.closeSession();
            }
            control.getFrame().getPanelComp().getCheckedOutPanel().update();
        }
    }

}

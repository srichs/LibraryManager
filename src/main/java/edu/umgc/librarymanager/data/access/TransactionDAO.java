/*
 * Filename: TransactionDAO.java
 * Author: Scott
 * Date Created: 11/26/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.TransactionType;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.util.List;
import org.hibernate.query.Query;

/**
 * This class is used to access the data in the transaction table of the
 * hibernate database.
 * @author Scott
 */
public class TransactionDAO extends BaseDAO<BaseTransaction> {

    public TransactionDAO() {
        super();
    }

    /**
     * Finds the transactions from a user.
     * @param user The user to search for.
     * @return A list of transactions for the given user.
     */
    @SuppressWarnings("unchecked")
    public List<BaseTransaction> findByUser(BaseUser user) {
        List<BaseTransaction> transactions = (List<BaseTransaction>) getSession()
                .createQuery("From BaseTransaction t Where t.user = :user")
                .setParameter("user", user).getResultList();
        if (transactions.size() > 0) {
            return transactions;
        }
        return null;
    }

    /**
     * Finds the transactions for an item.
     * @param item The item to search for.
     * @return A list of transactions for the given item.
     */
    @SuppressWarnings("unchecked")
    public BaseTransaction findByItem(BaseItem item) {
        List<BaseTransaction> transactions = (List<BaseTransaction>) getSession()
                .createQuery("From BaseTransaction t Where t.item = :item")
                .setParameter("item", item).getResultList();
        if (transactions.size() > 0) {
            return transactions.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseTransaction> findAll() {
        List<BaseTransaction> list = (List<BaseTransaction>) getSession().createQuery("From BaseTransaction")
                .getResultList();
        return list;
    }

    /**
     * Finds the BaseItems that are checked out by a specific user.
     * @param user The BaseUser to check if has items checked out.
     * @return A List of BaseItems that are checked out by the user.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> findCheckedOutByUser(BaseUser user) {
        Query<BaseItem> query = getSession().createQuery(
                "From BaseTransaction t Where t.item.status =:status And t.user = :user")
                .setParameter("status", ItemStatus.CheckedOut)
                .setParameter("user", user);
        List<BaseItem> results = query.getResultList();
        return results;
    }

    /**
     * Finds the Items that match the given user and the given ItemStatus.
     * @param user The BaseUser to search the transactions for.
     * @param status The ItemStatus of the item of the transaction to query the database for.
     * @return A List of BaseItems that match the query terms.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> findByStatusByUser(BaseUser user, ItemStatus status) {
        Query<BaseItem> query = getSession().createQuery(
                "From BaseTransaction t Where t.item.status =:status And t.user = :user")
                .setParameter("status", status)
                .setParameter("user", user);
        List<BaseItem> results = query.getResultList();
        return results;
    }

    /**
     * Finds all of the transactions for the given type.
     * @param type The Transaction Type to query the database for.
     * @return A List of Transactions that match the query.
     */
    @SuppressWarnings("unchecked")
    public List<BaseTransaction> findByTransactionType(TransactionType type) {
        Query<BaseTransaction> query = getSession().createQuery(
                "From BaseTransaction t Where t.type = :type")
                .setParameter("type", type);
        List<BaseTransaction> results = query.getResultList();
        return results;
    }

    /*@SuppressWarnings("unchecked")    // Not working
    public List<BaseItem> findPastDue() {
        Query<BaseItem> query = getSession().createQuery(
                "From BaseTransaction t Where t.due_date < :today")
                .setParameter("today", ZonedDateTime.now(), TemporalType.DATE);
        List<BaseItem> results = query.getResultList();
        return results;
    }*/

}

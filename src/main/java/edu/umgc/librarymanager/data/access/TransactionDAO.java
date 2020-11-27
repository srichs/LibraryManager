/*
 * Filename: TransactionDAO.java
 * Author: Scott
 * Date Created: 11/26/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.util.List;

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
                .setParameter("user", user).list();
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
                .setParameter("item", item).list();
        if (transactions.size() > 0) {
            return transactions.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseTransaction> findAll() {
        List<BaseTransaction> list = (List<BaseTransaction>) getSession().createQuery("From BaseUser").list();
        return list;
    }

}

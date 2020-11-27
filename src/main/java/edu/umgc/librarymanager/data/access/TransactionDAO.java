/*
 * Filename: TransactionDAO.java
 * Author: Scott
 * Date Created: 11/26/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.BaseTransaction;
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

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseTransaction> findAll() {
        List<BaseTransaction> list = (List<BaseTransaction>) getSession().createQuery("From BaseUser").list();
        return list;
    }

}

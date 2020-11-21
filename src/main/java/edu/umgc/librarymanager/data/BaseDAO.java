/*
 * Filename: BaseDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * A base class for a Data Access Object. It allows a session to be opened and a
 * transaction to be started.
 * @author Scott
 */
public abstract class BaseDAO {

    private Session session;
    private Transaction transaction;

    public BaseDAO() {}

    public Session openSession() {
        session = HibernateUtility.getSessionFactory().openSession();
        return session;
    }

    /**
     * Opens a Hibernate Session with a Transaction.
     * @return A Session Object for the opened Session.
     */
    public Session openSessionwithTransaction() {
        session = HibernateUtility.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}

/*
 * Filename: BaseDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * A base class for a Data Access Object. It allows a session to be opened and a
 * transaction to be started.
 * @param <T> The type of entity for data access.
 * @author Scott
 */
public abstract class BaseDAO<T> implements DAOInteface<T> {

    private Session session;
    private Transaction transaction;

    public BaseDAO() {
        this.session = null;
        this.transaction = null;
    }

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

    public void commit() {
        transaction.commit();
    }

    @Override
    public void persist(T entity) {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
    }

}

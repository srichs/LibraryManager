/*
 * Filename: UserLoginDAO.java
 * Author: srichs
 * Date Created: 11/10/2020
 * Purpose: This class is used to create a DAO for the User Login information.
 */

package edu.umgc.librarymanager.data;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Used to create a data access object for the user login information.
 */
public class UserLoginDAO implements DAOInteface<UserLogin> {

    private Session session;
    private Transaction transaction;

    public UserLoginDAO() {
    }

    public Session openSession() {
        session = HibernateUtility.getSessionFactory().openSession();
        return session;
    }

    /**
     * Opens a Hibernate Session with a Transaction.
     * 
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

    @SuppressWarnings("unchecked")
    public boolean searchByUsername(String username) {
        List<UserLogin> logins = (List<UserLogin>) getSession().createQuery("Select login From user_login Where login.username = :uname")
                .setParameter("uname", username).getResultList();
        if (logins.size() == 0) {
            return false;
        } else if (logins.get(0).getUsername() == username) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public UserLogin findByUsername(String username) {
        List<UserLogin> logins = (List<UserLogin>) getSession().createQuery("Select login From user_login Where login.username = :uname")
                .setParameter("uname", username).list();
        if (logins.size() > 0) {
            if (logins.get(0).getUsername() == username) {
                return logins.get(0);
            }
        }
        return null;
    }

    public void persist(UserLogin entity) {
        getSession().save(entity);
    }

    public void update(UserLogin entity) {
        getSession().update(entity);
    }

    public void delete(UserLogin entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<UserLogin> findAll() {
        List<UserLogin> logins = (List<UserLogin>) getSession().createQuery("from user_login").list();
        return logins;
    }

    /**
     * Used to delete all information from the user_login table.
     */
    public void deleteAll() {
        List<UserLogin> entityList = findAll();
        for (UserLogin entity : entityList) {
            delete(entity);
        }
    }
}

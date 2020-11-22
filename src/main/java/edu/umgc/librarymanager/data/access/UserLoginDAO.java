/*
 * Filename: UserLoginDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;
import edu.umgc.librarymanager.data.model.UserLogin;

/**
 * Used to create a data access object for the user login information.
 * @author Scott
 */
public class UserLoginDAO extends BaseDAO implements DAOInteface<UserLogin> {

    public UserLoginDAO() {}

    /**
     * Searches for whether a user exists in the database.
     * @param username The user's username.
     * @return A boolean value for whether the user exists.
     */
    @SuppressWarnings("unchecked")
    public boolean searchByUsername(String username) {
        List<UserLogin> logins = (List<UserLogin>) getSession()
                .createQuery("Select login From user_login Where login.username = :uname")
                .setParameter("uname", username).getResultList();
        if (logins.size() > 0) {
            if (logins.get(0).getUsername() == username) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a user's login information from the database given the username.
     * @param username The user's username.
     * @return The user's login credentials.
     */
    @SuppressWarnings("unchecked")
    public UserLogin findByUsername(String username) {
        List<UserLogin> logins = (List<UserLogin>) getSession()
                .createQuery("Select login From user_login Where login.username = :uname")
                .setParameter("uname", username).list();
        if (logins.size() > 0) {
            if (logins.get(0).getUsername() == username) {
                return logins.get(0);
            }
        }
        return null;
    }

    /**
     * Authenticates a user for the given username and password. The password parameter is
     * in plaintext, but it is stored in the database as an encrypted string.
     * @param username The username of the user.
     * @param password The plaintext password of the user.
     * @return A boolean value for whether the user is authenticated.
     */
    public boolean authenicateUser(String username, String password) {
        UserLogin login = findByUsername(username);
        if (login == null) {
            return false;
        }
        return login.checkPassword(password);
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
        List<UserLogin> logins = (List<UserLogin>) getSession().createQuery("From user_login").list();
        return logins;
    }

    public void deleteAll() {
        List<UserLogin> entityList = findAll();
        for (UserLogin entity : entityList) {
            delete(entity);
        }
    }

}
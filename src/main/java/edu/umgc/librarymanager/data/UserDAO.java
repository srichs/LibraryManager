/*
 * Filename: UserDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

import java.util.List;

/**
 * The Data Access Object class to access the base_user table of the database.
 * @author Scott
 */
public class UserDAO extends BaseDAO implements DAOInteface<BaseUser> {

    public UserDAO() {}

    public void persist(BaseUser entity) {
        getSession().save(entity);
    }

    public void update(BaseUser entity) {
        getSession().update(entity);
    }

    public void delete(BaseUser entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<BaseUser> findAll() {
        List<BaseUser> users = (List<BaseUser>) getSession().createQuery("From base_user").list();
        return users;
    }

    public void deleteAll() {
        List<BaseUser> entityList = findAll();
        for (BaseUser entity : entityList) {
            delete(entity);
        }
    }

}

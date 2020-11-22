/*
 * Filename: UserDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;
import edu.umgc.librarymanager.IUser;
import edu.umgc.librarymanager.IUserService;
import edu.umgc.librarymanager.data.model.BaseUser;

/**
 * The Data Access Object class to access the base_user table of the database.
 * @author Scott
 */
public class UserDAO extends BaseDAO implements DAOInteface<BaseUser>, IUserService {

    public UserDAO() {
    }

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

    @Override
    public void createUser(IUser newUser) {
        getSession().save(newUser);
    }

    @Override
    public void updateUser(IUser user) {
        getSession().update(user);
    }

}

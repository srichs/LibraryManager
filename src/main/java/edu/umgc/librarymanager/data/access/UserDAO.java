/*
 * Filename: UserDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.service.IUserService;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.IUser;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the Data Access Object class for the BaseUser class.
 * @author Scott
 */
public class UserDAO extends BaseDAO<BaseUser> implements IUserService {

    public UserDAO() {
        super();
    }

    @Override
    public void createUser(IUser newUser) {
        getSession().save(newUser);
    }

    @Override
    public void updateUser(IUser user) {
        getSession().update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseUser> findAll() {
        List<BaseUser> list = (List<BaseUser>) getSession().createQuery("From BaseUser").list();
        return list;
    }

    /**
     * Creates a HashMap of users so they can be searched by username.
     * @return A HashMap.
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, BaseUser> getUserHashMap() {
        HashMap<String, BaseUser> map = new HashMap<String, BaseUser>();
        List<BaseUser> list = (List<BaseUser>) getSession().createQuery("From BaseUser").list();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getLogin().getUsername(), list.get(i));
        }
        return map;
    }

    /**
     * Searches for whether a user exists in the database.
     * @param username The user's username.
     * @return A user who was searched.
     */
    @SuppressWarnings("unchecked")
    public BaseUser searchByUsername(String username) {
        List<BaseUser> users = (List<BaseUser>) getSession()
                .createQuery("From BaseUser bu Where bu.username = :uname")
                .setParameter("uname", username).getResultList(); // TODO fix query
        if (users.size() > 0) {
            if (users.get(0).getLogin().getUsername() == username) {
                System.out.println(users.get(0).getEmail());
                return users.get(0);
            }
        }
        return null;
    }

}

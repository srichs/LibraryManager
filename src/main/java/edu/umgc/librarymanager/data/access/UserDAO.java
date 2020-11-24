/*
 * Filename: UserDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.IUserService;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.IUser;
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

}

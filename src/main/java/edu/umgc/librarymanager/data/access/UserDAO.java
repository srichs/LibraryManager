/*
 * Filename: UserDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.IUser;
import edu.umgc.librarymanager.service.IUserService;
import java.util.HashMap;
import java.util.List;
import org.hibernate.query.Query;

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
        List<BaseUser> list = (List<BaseUser>) getSession().createQuery("From BaseUser").getResultList();
        return list;
    }

    /**
     * Creates a HashMap of users so they can be searched by username.
     * @return A HashMap.
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, BaseUser> getUserHashMap() {
        HashMap<String, BaseUser> map = new HashMap<String, BaseUser>();
        List<BaseUser> list = (List<BaseUser>) getSession().createQuery("From BaseUser").getResultList();
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
                .createQuery("From BaseUser u Where u.login.username = :uname")
                .setParameter("uname", username).getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * Finds a base user given the user's id.
     * @param id The id of the user.
     * @return The BaseUser that was found.
     */
    @SuppressWarnings("unchecked")
    public BaseUser findById(long id) {
        List<BaseUser> users = (List<BaseUser>) getSession()
                .createQuery("From BaseUser u Where u.id = :id")
                .setParameter("id", Integer.valueOf((int) id)).getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * Checks if a username exists in the database.
     * @param username The username to check for.
     * @return A boolean value for whether the username exists.
     */
    @SuppressWarnings("unchecked")
    public boolean doesUsernameExist(String username) {
        List<BaseUser> users = (List<BaseUser>) getSession()
                .createQuery("From BaseUser u Where u.login.username = :uname")
                .setParameter("uname", username).getResultList();
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Searches the BaseUser table for last names that contain the searchTerm.
     * @param searchTerm The term to be searched for.
     * @return The List of BaseUsers who have last names that contain the searchTerm.
     */
    @SuppressWarnings("unchecked")
    public List<BaseUser> searchByLastName(String searchTerm) {
        List<BaseUser> users = (List<BaseUser>) getSession()
                .createQuery("From BaseUser u Where u.last_name  like :searchTerm")
                .setParameter("searchTerm", "%" + searchTerm + "%").getResultList();
        if (users.size() > 0) {
            return users;
        }
        return null;
    }

    /**
     * Gets the paginated results of the users. It gets the results based on what is provided in the
     * pagination parameter. The pageSize and desiredPage fields from the pagination parameter are used
     * to determine which results should be returned.
     * @param pagination The Pagination object with information about the paging.
     * @return The List of BaseUsers for the given pagination parameters.
     */
    @SuppressWarnings("unchecked")
    public List<BaseUser> findAllPaginated(Pagination pagination) {
        Query<Long> countQuery = getSession().createQuery("Select Count (u.id) From BaseUser u");
        pagination.setTotalCount((Long) countQuery.uniqueResult());
        Query<BaseUser> selectQuery = getSession().createQuery("From BaseUser");
        selectQuery.setFirstResult((pagination.getDesiredPage() - 1) * pagination.getPageSize());
        selectQuery.setMaxResults(pagination.getPageSize());
        List<BaseUser> page = selectQuery.getResultList();
        return page;
    }

}

/*
 * Filename: DeweyCategoryDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtility;
import java.util.List;

/**
 * This class is the Data Access Object class for the DeweyCategory class.
 * @author Scott
 */
public class DeweyCategoryDAO extends BaseDAO implements DAOInteface<DeweyCategory> {

    public DeweyCategoryDAO() {}

    /**
     * Finds a Dewey Classification given the Dewey Decimal code.
     * @param code The Dewey Decimal Code in the format ###.###.
     * @return The DeweyCategory value that matches the first three numbers of the code.
     */
    @SuppressWarnings("unchecked")
    public DeweyCategory findByCode(String code) {
        if (DeweyDecimalUtility.matchesPattern(code)) {
            List<DeweyCategory> categories = (List<DeweyCategory>) getSession()
                .createQuery("Select category From dewey_category Where category.code = :code")
                .setParameter("code", code).list();
            if (categories.size() > 0) {
                if (categories.get(0).getCode() == code) {
                    return categories.get(0);
                }
            }
        }
        return null;
    }

    public void persist(DeweyCategory entity) {
        getSession().save(entity);
    }

    public void update(DeweyCategory entity) {
        getSession().update(entity);
    }

    public void delete(DeweyCategory entity) {
        getSession().delete(entity);
    }

    /**
     * Finds all of the DeweyCategory entries in the database.
     * @return A List of DeweyCategory Objects.
     */
    @SuppressWarnings("unchecked")
    public List<DeweyCategory> findAll() {
        List<DeweyCategory> users = (List<DeweyCategory>) getSession().createQuery("From dewey_category").list();
        return users;
    }

    /**
     * Deletes all of the DeweyCategory entries from the database.
     */
    public void deleteAll() {
        List<DeweyCategory> entityList = findAll();
        for (DeweyCategory entity : entityList) {
            delete(entity);
        }
    }

}

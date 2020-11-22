/*
 * Filename: DeweyCategoryDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtility;

/**
 * This class is the Data Access Object class for the DeweyCategory class.
 * @author Scott
 */
public class DeweyCategoryDAO extends BaseDAO implements DAOInteface<DeweyCategory> {

    public DeweyCategoryDAO() {}

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

    @SuppressWarnings("unchecked")
    public List<DeweyCategory> findAll() {
        List<DeweyCategory> users = (List<DeweyCategory>) getSession().createQuery("From dewey_category").list();
        return users;
    }

    public void deleteAll() {
        List<DeweyCategory> entityList = findAll();
        for (DeweyCategory entity : entityList) {
            delete(entity);
        }
    }

}

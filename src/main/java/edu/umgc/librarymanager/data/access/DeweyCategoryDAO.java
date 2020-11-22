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
public class DeweyCategoryDAO extends BaseDAO<DeweyCategory> {

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
                .createQuery("From DeweyCategory dc Where dc.code = :code")
                .setParameter("code", code).list();
            if (categories.size() > 0) {
                if (categories.get(0).getCode().equals(code)) {
                    return categories.get(0);
                }
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DeweyCategory> findAll() {
        List<DeweyCategory> list = (List<DeweyCategory>) getSession().createQuery("From DeweyCategory").list();
        return list;
    }

}

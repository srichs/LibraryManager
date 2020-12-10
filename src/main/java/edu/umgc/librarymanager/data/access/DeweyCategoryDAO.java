/*
 * Filename: DeweyCategoryDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtility;
import java.util.HashMap;
import java.util.List;
import org.hibernate.query.Query;

/**
 * This class is the Data Access Object class for the DeweyCategory class.
 * @author Scott
 */
public class DeweyCategoryDAO extends BaseDAO<DeweyCategory> {

    public DeweyCategoryDAO() {
        super();
    }

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
                .setParameter("code", code).getResultList();
            if (categories.size() > 0) {
                if (categories.get(0).getCode().equals(code)) {
                    return categories.get(0);
                }
            }
        }
        return null;
    }

    /**
     * Gets a hashmap of all of the dewey decimal categories.
     * @return A HashMap of DDC values, the code is the key and the category is the value.
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, String> getDDCHashMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        List<DeweyCategory> list = (List<DeweyCategory>) getSession().createQuery("From DeweyCategory")
                .getResultList();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getCode(), list.get(i).getCategory());
        }
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DeweyCategory> findAll() {
        List<DeweyCategory> list = (List<DeweyCategory>) getSession().createQuery("From DeweyCategory")
                .getResultList();
        return list;
    }

    /**
     * This method is used to receive a page of data given the pagination specifications. Ensure that the
     * currentPage field and pageSize field are set to requirements before passing. The other fields will be
     * used by this method.
     * @param pagination The pagination specifications.
     * @return A list of dewey categories for the current page.
     */
    @SuppressWarnings("unchecked")
    public List<DeweyCategory> findAllPaginated(Pagination pagination) {
        Query<Integer> countQuery = getSession().createQuery("Select count (dc.id) From DeweyCategory dc");
        pagination.setTotalCount((Integer) countQuery.uniqueResult());
        Query<DeweyCategory> selectQuery = getSession().createQuery("From DeweyCategory");
        selectQuery.setFirstResult((pagination.getDesiredPage() - 1) * pagination.getPageSize());
        selectQuery.setMaxResults(pagination.getPageSize());
        List<DeweyCategory> page = selectQuery.getResultList();
        return page;
    }

}

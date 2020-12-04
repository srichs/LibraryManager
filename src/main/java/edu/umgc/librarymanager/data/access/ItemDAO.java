/*
 * Filename: ItemDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import java.util.List;
import org.hibernate.query.Query;

/**
 * This class is the Data Access Object class for the BaseItem class.
 * @author Scott
 */
public class ItemDAO extends BaseDAO<BaseItem> {

    public ItemDAO() {
        super();
    }

    /**
     * Retrieves an item's information from the database given the title.
     * @param searchTerm The seach terms to be search for in the title field.
     * @return The items matching the search terms information.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> searchByTitle(String searchTerm) {
        List<BaseItem> items = (List<BaseItem>) getSession()
                .createQuery("From BaseItem i Where i.title like :searchTerm")
                .setParameter("searchTerm", "%" + searchTerm + "%").getResultList();
        if (items.size() > 0) {
            return items;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseItem> findAll() {
        List<BaseItem> list = (List<BaseItem>) getSession().createQuery("From BaseItem").getResultList();
        return list;
    }

    /**
     * Retrieves a List of BaseItems for the given pagination parameters.
     * @param pagination The Pagination parameters to get the results for.
     * @return The List of BaseItems for the given pagination parameters.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> findAllPaginated(Pagination pagination) {
        Query<Integer> countQuery = getSession().createQuery("Select Count (i.id) From BaseItem i");
        pagination.setTotalCount((Integer) countQuery.uniqueResult());
        Query<BaseItem> selectQuery = getSession().createQuery("From BaseItem");
        selectQuery.setFirstResult((pagination.getDesiredPage() - 1) * pagination.getPageSize());
        selectQuery.setMaxResults(pagination.getPageSize());
        List<BaseItem> page = selectQuery.getResultList();
        return page;
    }

}

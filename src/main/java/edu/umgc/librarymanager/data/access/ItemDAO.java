/*
 * Filename: ItemDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.item.ItemType;
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

    /**
     * Finds a BaseItem by the item's id.
     * @param id The id of the item.
     * @return The BaseItem for the given id.
     */
    @SuppressWarnings("unchecked")
    public BaseItem findById(long id) {
        List<BaseItem> items = (List<BaseItem>) getSession()
                .createQuery("From BaseItem i Where i.id = :id")
                .setParameter("id", id).getResultList();
        if (items.size() > 0) {
            return items.get(0);
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
        Query<Long> countQuery = getSession().createQuery("Select Count (i.id) From BaseItem i");
        pagination.setTotalCount((Long) countQuery.uniqueResult());
        Query<BaseItem> selectQuery = getSession().createQuery("From BaseItem");
        selectQuery.setFirstResult((pagination.getDesiredPage() - 1) * pagination.getPageSize());
        selectQuery.setMaxResults(pagination.getPageSize());
        List<BaseItem> page = selectQuery.getResultList();
        return page;
    }

    /**
     * Finds the items that are checked out.
     * @return A List of BaseItems that are checked out.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> findCheckedOut() {
        Query<BaseItem> query = getSession().createQuery("From BaseItem i Where i.status =:status")
                .setParameter("status", ItemStatus.CheckedOut);
        List<BaseItem> results = query.getResultList();
        return results;
    }

    /**
     * Finds the BaseItems for the given status.
     * @param status The ItemStatus to query the database for.
     * @return A List of BaseItems that match the given status.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> findByStatus(ItemStatus status) {
        Query<BaseItem> query = getSession().createQuery("From BaseItem i Where i.status =:status")
                .setParameter("status", status);
        List<BaseItem> results = query.getResultList();
        return results;
    }

    /**
     * Finds the Items for the given type.
     * @param type The ItemType to query the database for.
     * @return A List of BaseItems that match the given type.
     */
    @SuppressWarnings("unchecked")
    public List<BaseItem> findByType(ItemType type) {
        Query<BaseItem> query = getSession().createQuery("From BaseItem i Where i.type =:type")
                .setParameter("type", type);
        List<BaseItem> results = query.getResultList();
        return results;
    }

}

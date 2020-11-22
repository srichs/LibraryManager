/*
 * Filename: ItemDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import java.util.List;

/**
 * This class is the Data Access Object class for the BaseItem class.
 * @author Scott
 */
public class ItemDAO extends BaseDAO<BaseItem> {

    public ItemDAO() {}

    /**
     * Retrieves an item's information from the database given the title.
     * @param title The item's title.
     * @return The item's information.
     */
    @SuppressWarnings("unchecked")
    public BaseItem findByTitle(String title) {
        List<BaseItem> items = (List<BaseItem>) getSession()
                .createQuery("From BaseItem i Where i.title = :title")
                .setParameter("title", title).list();
        if (items.size() > 0) {
            if (items.get(0).getTitle() == title) {
                return items.get(0);
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseItem> findAll() {
        List<BaseItem> list = (List<BaseItem>) getSession().createQuery("From BaseItem").list();
        return list;
    }

}

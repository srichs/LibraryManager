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

    @Override
    @SuppressWarnings("unchecked")
    public List<BaseItem> findAll() {
        List<BaseItem> list = (List<BaseItem>) getSession().createQuery("From BaseItem").list();
        return list;
    }

}

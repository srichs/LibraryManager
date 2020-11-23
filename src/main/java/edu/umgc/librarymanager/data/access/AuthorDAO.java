/*
 * Filename: AuthorDAO.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.Author;
import java.util.List;

/**
 * This class is the Data Access Object class for the Author class.
 * @author Scott
 */
public class AuthorDAO extends BaseDAO<Author> {

    public AuthorDAO() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Author> findAll() {
        List<Author> list = (List<Author>) getSession().createQuery("From Author").list();
        return list;
    }

}

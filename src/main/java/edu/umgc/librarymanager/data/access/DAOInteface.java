/*
 * Filename: DAOInterface.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;

/**
 * Interface for a Data Access Object (DAO).
 * @param <T> The type of the Object.
 * @author Scott
 */
public interface DAOInteface<T> {

    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
    void deleteAll();

}

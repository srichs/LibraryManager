/*
 * Filename: Book.java
 * Author: David
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a Book Item.
 * @author David
 */
public class Book extends BaseItem {

    private List<String> authors;

    public Book() {
        super();
        this.authors = new ArrayList<String>();
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

}

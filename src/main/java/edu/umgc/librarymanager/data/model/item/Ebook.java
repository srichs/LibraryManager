/*
 * Filename: EBook.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.util.List;

/**
 * This class models an E-Book Item.
 * @author David
 */
public class Ebook extends BaseItem {

    private List<String> authors;

    public Ebook() {
        super();
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

}

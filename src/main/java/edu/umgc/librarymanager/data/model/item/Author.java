/*
 * Filename: Author.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Models an author for a book or ebook item.
 * @author Scott
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<BaseBook> books;

    /**
     * The default constructor for the class.
     */
    public Author() {
        this.name = "";
        this.books = new ArrayList<BaseBook>();
    }

    /**
     * A constructor that takes an author's name as a parameter.
     * @param name The name of the author.
     */
    public Author(String name) {
        this.name = name;
        this.books = new ArrayList<BaseBook>();
    }

    public Author(String name, List<BaseBook> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseBook> getBooks() {
        return this.books;
    }

    public void setBooks(List<BaseBook> books) {
        this.books = books;
    }

}

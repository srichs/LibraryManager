/*
 * Filename: Author.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "suffix")
    private String suffix;

    /**
     * The default constructor for the class.
     */
    public Author() {
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.suffix = "";
    }

    /**
     * A constructor with a parameter for each field.
     * @param fName The first name of the author.
     * @param mName The middle name of the author.
     * @param lName The last name of the author.
     * @param suffix The suffix of the author.
     */
    public Author(String fName, String mName, String lName, String suffix) {
        this.firstName = fName;
        this.middleName = mName;
        this.lastName = lName;
        this.suffix = suffix;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String mName) {
        this.middleName = mName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * The toString method of the class.
     */
    public String toString() {
        String name = "";
        boolean hasMiddle = !"".equals(this.middleName);
        boolean hasLast = !"".equals(this.lastName);
        boolean hasSuffix = !"".equals(this.suffix);
        name = name + this.firstName;
        if (hasMiddle) {
            name = name + " " + this.middleName;
        }
        if (hasLast) {
            name = name + " " + this.lastName;
        }
        if (hasSuffix) {
            name = name + " " + this.suffix;
        }
        return name;
    }

}

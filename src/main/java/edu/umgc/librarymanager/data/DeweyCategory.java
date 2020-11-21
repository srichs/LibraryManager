/*
 * Filename: DeweyCategory.java
 * Author: Scott
 * Date Created: 11/21/2020
 * Purpose: This class is used to model a Dewey Decimal Category.
 */

package edu.umgc.librarymanager.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Models a Dewey Decimal System category. Uses hibernate annotations to map
 * the categories code and category to the hibernate database.
 */
@Entity
@Table(name = "dewey_category")
public class DeweyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dewey_category_id")
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "category")
    private String category;

    public DeweyCategory() {
        this.code = "";
        this.category = "";
    }

    /**
     * Constructor for the class.
     * @param code The category number.
     * @param cat The category.
     */
    public DeweyCategory(String code, String cat) {
        this.code = code;
        this.category = cat;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String cat) {
        this.category = cat;
    }

}

/*
 * Filename: Book.java
 * Author: David
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This class models a Book Item.
 * @author Scott
 */
@Entity
@Table(name = "book")
public class Book extends BaseItem {

    @ManyToMany
    private List<String> authors;

    @Column(name = "isbn")
    private String isbn;

    /**
     * The default constructor for the class.
     */
    public Book() {
        super();
        super.setItemType(ItemType.BOOK);
        this.authors = new ArrayList<String>();
        this.isbn = "";
    }

    /**
     * A constructor with parameters for the super class's fields.
     * @param classGroup The classification group of the item.
     * @param purchaseDate The purchase date of the item.
     * @param description The description of the item.
     * @param price The purchase price of the item.
     * @param title The title of the item.
     * @param publisher The publisher of the item.
     * @param genre The genre of the item.
     * @param summary The summary of the item.
     * @param status The status of the item in the Library.
     * @param checkoutPeriod The checkout period of the item.
     */
    public Book(ClassificationGroup classGroup, ZonedDateTime purchaseDate, String description,
            BigDecimal price, String title, PublishData publisher, String genre, String summary,
            ItemStatus status, Period checkoutPeriod) {
        super.setClassificationGroup(classGroup);
        super.setPurchaseDate(purchaseDate);
        super.setDescription(description);
        super.setPurchasePrice(price);
        super.setTitle(title);
        super.setPublisher(publisher);
        super.setGenre(genre);
        super.setSummary(summary);
        super.setStatus(status);
        super.setCheckoutPeriod(checkoutPeriod);
        super.setItemType(ItemType.BOOK);
        this.authors = new ArrayList<String>();
        this.isbn = "";
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getISBN() {
        return this.isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

}

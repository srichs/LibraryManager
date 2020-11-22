/*
 * Filename: BaseBook.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The abstract base class for a Book in a library, it extends the BaseItem
 * abstract class and uses hibernate annotations to model the data for the database.
 * @author Scott
 */
@Entity
@Table(name = "base_book")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseBook extends BaseItem {

    @ManyToMany
    private List<Author> authors;

    @Column(name = "isbn")
    private String isbn;

    /**
     * The default constructor for the class.
     */
    public BaseBook() {
        super();
        this.authors = new ArrayList<Author>();
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
    public BaseBook(ClassificationGroup classGroup, ZonedDateTime purchaseDate, String description,
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
        this.authors = new ArrayList<Author>();
        this.isbn = "";
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getISBN() {
        return this.isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setItemType(ItemType type) {
        super.setItemType(type);
    }

    public String authorsToString() {
        String str = "";
        for (int i = 0; i < this.authors.size(); i++) {
            str += authors.get(i);
            if (i < this.authors.size() - 1) {
                str += ", ";
            }
        }
        return str;
    }

}

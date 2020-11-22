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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * This class models a Book Item.
 * @author Scott
 */
@Entity
@DiscriminatorValue("book")
public class Book extends BaseItem {

    private List<String> authors;
    private String isbn;

    public Book() {
        super();
        super.setItemType(ItemType.BOOK);
        this.authors = new ArrayList<String>();
        this.isbn = "";
    }

    public Book(long id, ClassificationGroup classGroup, ZonedDateTime purchaseDate, String description,
            BigDecimal price, String title, PublishData publisher, String genre, String summary,
            ItemStatus status, Period checkoutPeriod) {
        super.setId(id);
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
        super.setItemType(ItemType.EBOOK);
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

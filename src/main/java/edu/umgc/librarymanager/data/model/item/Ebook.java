/*
 * Filename: EBook.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * This class models an E-Book Item.
 * @author Scott
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ebook extends BaseBook {

    /**
     * The default constructor for the class.
     */
    public Ebook() {
        super();
        super.setItemType(ItemType.EBook);
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
     * @param authors A string of authors separated by commas.
     * @param isbn The 13 digit ISBN number of the book.
     */
    public Ebook(ClassificationGroup classGroup, ZonedDateTime purchaseDate, String description,
            BigDecimal price, String title, PublishData publisher, String genre, String summary,
            ItemStatus status, Period checkoutPeriod, String authors, String isbn) {
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
        super.setItemType(ItemType.Book);
        super.setAuthors(authors);
        super.setISBN(isbn);
    }

}

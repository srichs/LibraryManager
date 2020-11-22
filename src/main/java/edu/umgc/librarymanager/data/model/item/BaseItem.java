/*
 * Filename: BaseItem.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;
import edu.umgc.librarymanager.data.model.Classification;
import edu.umgc.librarymanager.data.model.ItemStatus;
import edu.umgc.librarymanager.data.model.Library;
import edu.umgc.librarymanager.data.model.PublishData;

/**
 * The abstract base class for an Item in a library, it implements the ILibraryItem
 * interface and uses hibernate annotations to model the data for the database.
 * @author Scott
 */
public abstract class BaseItem implements ILibraryItem {
    
    private long id;
    private HashMap<Classification.ClassType, Classification> classificationCode;
    private ZonedDateTime purchaseDate;
    private String description;
    private BigDecimal purchasePrice;
    private String title;
    private PublishData publisher;
    private String genre;
    private String summary;
    private ItemStatus status;
    private HashMap<Library, Integer> availability;
    private Period checkoutPeriod;

    public BaseItem() {
        this.id = -1;
        this.classificationCode = new HashMap<Classification.ClassType, Classification>();
        this.purchaseDate = null;
        this.description = "";
        this.purchasePrice = null;
        this.title = "";
        this.publisher = null;
        this.genre = "";
        this.summary = "";
        this.status = null;
        this.availability = new HashMap<Library, Integer>();
        this.checkoutPeriod = null;
    }

    public BaseItem(long id, HashMap<Classification.ClassType, Classification> classCode, ZonedDateTime purchaseDate,
            String description, BigDecimal price, String title, PublishData publisher, String genre, String summary,
            ItemStatus status, HashMap<Library, Integer> availability, Period checkoutPeriod) {
        this.id = -1;
        this.classificationCode = new HashMap<Classification.ClassType, Classification>();
        this.purchaseDate = null;
        this.description = "";
        this.purchasePrice = null;
        this.title = "";
        this.publisher = null;
        this.genre = "";
        this.summary = "";
        this.status = null;
        this.availability = new HashMap<Library, Integer>();
        this.checkoutPeriod = null;
    }

    public long getId() {
        return this.id;
    }

    public HashMap<Classification.ClassType, Classification> getClassificationCode() {
        return this.classificationCode;
    }

    public ZonedDateTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getPurchasePrice() {
        return this.purchasePrice;
    }

    public String getTitle() {
        return this.title;
    }

    public PublishData getPublisher() {
        return this.publisher;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getSummary() {
        return this.summary;
    }

    public ZonedDateTime getPublishDate() {
        return this.publisher.getPublishDate();
    }

    public ItemStatus getStatus() {
        return this.status;
    }

    public HashMap<Library, Integer> getAvailability() {
        return this.availability;
    }

    public Period getCheckoutPeriod() {
        return this.checkoutPeriod;
    }

}

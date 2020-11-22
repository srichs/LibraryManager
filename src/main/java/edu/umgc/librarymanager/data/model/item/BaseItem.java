/*
 * Filename: BaseItem.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The abstract base class for an Item in a library, it implements the ILibraryItem
 * interface and uses hibernate annotations to model the data for the database.
 * @author Scott
 */
@Entity
@Table(name = "base_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseItem implements ILibraryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_login_id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private ClassificationGroup classGroup;

    @Column(name = "purchase_date")
    private ZonedDateTime purchaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(name = "title")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private PublishData publisher;

    @Column(name = "genre")
    private String genre;

    @Column(name = "summary")
    private String summary;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private ItemStatus status;

    @Column(name = "period")
    private Period checkoutPeriod;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private ItemType itemType;

    /**
     * The default constructor of the class.
     */
    public BaseItem() {
        this.classGroup = null;
        this.purchaseDate = null;
        this.description = "";
        this.purchasePrice = null;
        this.title = "";
        this.publisher = null;
        this.genre = "";
        this.summary = "";
        this.status = null;
        this.checkoutPeriod = null;
        this.itemType = null;
    }

    /**
     * A constructor with parameters for each of the class variables.
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
     * @param type The type of item.
     */
    public BaseItem(ClassificationGroup classGroup, ZonedDateTime purchaseDate,
            String description, BigDecimal price, String title, PublishData publisher, String genre, String summary,
            ItemStatus status, Period checkoutPeriod, ItemType type) {
        this.classGroup = classGroup;
        this.purchaseDate = purchaseDate;
        this.description = description;
        this.purchasePrice = price;
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.summary = summary;
        this.status = status;
        this.checkoutPeriod = checkoutPeriod;
        this.itemType = type;
    }

    public long getId() {
        return this.id;
    }

    public ClassificationGroup getClassificationGroup() {
        return this.classGroup;
    }

    public void setClassificationGroup(ClassificationGroup classGroup) {
        this.classGroup = classGroup;
    }

    public ZonedDateTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(ZonedDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(BigDecimal price) {
        this.purchasePrice = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublishData getPublisher() {
        return this.publisher;
    }

    public void setPublisher(PublishData publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ZonedDateTime getPublishDate() {
        return this.publisher.getPublishDate();
    }

    public ItemStatus getStatus() {
        return this.status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Period getCheckoutPeriod() {
        return this.checkoutPeriod;
    }

    public void setCheckoutPeriod(Period checkoutPeriod) {
        this.checkoutPeriod = checkoutPeriod;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * Checks if an item is a Book or E-Book.
     * @return A boolean value for if the item is a Book or E-Book.
     */
    public boolean isBook() {
        if (this.itemType == ItemType.Book || this.itemType == ItemType.EBook) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Title: " + this.title + "\nPrice: $" + this.purchasePrice.toPlainString() + "\nGenre: " + this.genre;
    }

}

/*
 * Filename: BaseItem.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import edu.umgc.librarymanager.data.model.Library;
import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;
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
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

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
    @DocumentId
    @Column(name = "base_item_id")
    private long id;

    @IndexedEmbedded(includeEmbeddedObjectId = true)
    @OneToOne(cascade = CascadeType.ALL)
    private ClassificationGroup classGroup;

    @Column(name = "purchase_date")
    private ZonedDateTime purchaseDate;

    @Field(name = "description")
    @Analyzer(definition = "ngram")
    @Column(name = "description")
    private String description;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Field(name = "title")
    @Analyzer(definition = "ngram")
    @Column(name = "title")
    private String title;

    @IndexedEmbedded(includeEmbeddedObjectId = true)
    @OneToOne(cascade = CascadeType.ALL)
    private PublishData publisher;

    @Field(name = "genre")
    @Analyzer(definition = "ngram")
    @Column(name = "genre")
    private String genre;

    @Field(name = "summary")
    @Analyzer(definition = "ngram")
    @Column(name = "summary", length = 1000)
    private String summary;

    @Field(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private ItemStatus status;

    @Column(name = "period")
    private Period checkoutPeriod;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private ItemType itemType;

    @Column(name = "notified")
    private Boolean notified;

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
        this.notified = false;
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
        this.notified = false;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public HashMap<ClassType, Classification> getClassificationCode() {
        HashMap<ClassType, Classification> hMap = new HashMap<ClassType, Classification>();
        hMap.put(this.classGroup.getDewey().getClassType(), this.classGroup.getDewey());
        hMap.put(this.classGroup.getLOC().getClassType(), this.classGroup.getLOC());
        return hMap;
    }

    public ClassificationGroup getClassificationGroup() {
        return this.classGroup;
    }

    public void setClassificationGroup(ClassificationGroup classGroup) {
        this.classGroup = classGroup;
    }

    @Override
    public ZonedDateTime getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(ZonedDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BigDecimal getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(BigDecimal price) {
        this.purchasePrice = price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public PublishData getPublisher() {
        return this.publisher;
    }

    public void setPublisher(PublishData publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public ZonedDateTime getPublishDate() {
        return this.publisher.getPublishDate();
    }

    @Override
    public ItemStatus getStatus() {
        return this.status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    @Override
    public HashMap<Library, Integer> getAvailability() {
        return null; // TODO
    }

    @Override
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
    
    public Boolean wasNotified() {
        return this.notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
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

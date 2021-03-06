/*
 * Filename: PublishData.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

/**
 * A class that is used to model some information about the publishing of an item.
 * @author David
 */
@Entity
@Indexed
@Embeddable
@Table(name = "publish_data")
public class PublishData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name = "publish_data_id")
    private long id;

    @Field(name = "publisher")
    @Analyzer(definition = "ngram")
    @Column(name = "publisher")
    private String publisher;

    @Field(name = "publish_date")
    @DateBridge(resolution = Resolution.SECOND)
    @Column(name = "publish_date")
    private ZonedDateTime publishDate;

    @Field(name = "publish_location")
    @Analyzer(definition = "ngram")
    @Column(name = "publish_location")
    private String publishLocation;

    /**
     * The default constructor for the class.
     */
    public PublishData() {
        this.publisher = "";
        this.publishDate = null;
        this.publishLocation = "";
    }

    /**
     * A constructor with parameters for each field.
     * @param publisher The publisher of the item.
     * @param date The date the item was published.
     * @param location The location where the item was published.
     */
    public PublishData(String publisher, ZonedDateTime date, String location) {
        this.publisher = publisher;
        this.publishDate = date;
        this.publishLocation = location;
    }

    public long getId() {
        return this.id;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ZonedDateTime getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(ZonedDateTime date) {
        this.publishDate = date;
    }

    public String getPublishLocation() {
        return this.publishLocation;
    }

    public void setPublishLocation(String location) {
        this.publishLocation = location;
    }

}

/*
 * Filename: Movie.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * This class models a Movie Item.
 * @author David
 */
@Entity
@DiscriminatorValue("movie")
public class Movie extends BaseItem {

    public Movie() {
        super();
        super.setItemType(ItemType.MOVIE);
    }

    public Movie(long id, ClassificationGroup classGroup, ZonedDateTime purchaseDate, String description,
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
    
}

/*
 * Filename: ILibraryItem.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

import edu.umgc.librarymanager.data.model.Library;
import java.math.BigDecimal;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;

/**
 * An interface to be implemented for an Item in a Library.
 * @author David
 */
public interface ILibraryItem {

    long getId();
    HashMap<ClassType, Classification> getClassificationCode();
    ZonedDateTime getPurchaseDate();
    String getDescription();
    BigDecimal getPurchasePrice();
    String getTitle();
    PublishData getPublisher();
    String getGenre();
    String getSummary();
    ZonedDateTime getPublishDate();
    ItemStatus getStatus();
    HashMap<Library, Integer> getAvailability();
    Period getCheckoutPeriod();

}


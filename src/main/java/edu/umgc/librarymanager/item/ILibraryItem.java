/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umgc.librarymanager.item;

import edu.umgc.librarymanager.Classification;
import edu.umgc.librarymanager.ItemStatus;
import edu.umgc.librarymanager.Library;
import edu.umgc.librarymanager.PublishData;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;

/**
 *
 * @author David
 */
public interface ILibraryItem {

    long getId();
    HashMap<Classification.ClassType, Classification> getClassificationCode();
    ZonedDateTime getPurchaseDate();
    String getDescription();
    double getPurchasePrice();
    String getTitle();
    PublishData getPublisher();
    String getGenre();
    String getSummary();
    ZonedDateTime getPublishDate();
    ItemStatus getStatus();
    HashMap<Library, Integer> getAvailability();
    Period getCheckoutPeriod();

}


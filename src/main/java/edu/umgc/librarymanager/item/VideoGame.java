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
public class VideoGame implements ILibraryItem {

    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<Classification.ClassType, Classification> getClassificationCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ZonedDateTime getPurchaseDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getPurchasePrice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PublishData getPublisher() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getGenre() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSummary() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ZonedDateTime getPublishDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemStatus getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<Library, Integer> getAvailability() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Period getCheckoutPeriod() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

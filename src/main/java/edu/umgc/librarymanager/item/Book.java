/*
 * Filename: Book.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class is used to model a Book.
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
 * This class models a Book.
 */
public class Book implements ILibraryItem {

    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Classification.ClassType, Classification> getClassificationCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ZonedDateTime getPurchaseDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPurchasePrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PublishData getPublisher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGenre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSummary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ZonedDateTime getPublishDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemStatus getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    private String title;
//    private String author;
//    private String yearPublished;
//    private String iSBN;
//    private String deweyDecimal;
//    private boolean isChecked;
//
//    /**
//     * The default constructor of the Book class.
//     */
//    public Book() {
//        this.title = "";
//        this.author = "";
//        this.yearPublished = "";
//        this.iSBN = "";
//        this.deweyDecimal = "";
//        this.isChecked = false;
//    }
//
//    /**
//     * A constructor to assign each value to a Book Object.
//     * @param title The title of the book.
//     * @param author The author of the book.
//     * @param yearPublished The year published.
//     * @param iSBN The ISBN of the book.
//     * @param deweyDecimal The Dewey Decimal System value.
//     * @param isChecked A flag for whether the book is checked out.
//     */
//    public Book(String title, String author, String yearPublished, String iSBN,
//            String deweyDecimal, boolean isChecked) {
//        this.title = title;
//        this.author = author;
//        this.yearPublished = yearPublished;
//        this.iSBN = iSBN;
//        this.deweyDecimal = deweyDecimal;
//        this.isChecked = isChecked;
//    }
//
//    public String getTitle() {
//        return this.title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return this.author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getYearPublished() {
//        return this.yearPublished;
//    }
//
//    public void setYearPublished(String yearPublished) {
//        this.yearPublished = yearPublished;
//    }
//
//    public String getISBN() {
//        return this.iSBN;
//    }
//
//    public void setISBN(String str) {
//        this.iSBN = str;
//    }
//
//    public String getDeweyDecimal() {
//        return this.deweyDecimal;
//    }
//
//    public void setDeweyDecimal(String deweyDecimal) {
//        this.deweyDecimal = deweyDecimal;
//    }
//
//    public boolean getIsChecked() {
//        return this.isChecked;
//    }
//
//    public void setIsChecked(boolean isChecked) {
//        this.isChecked = isChecked;
//    }
//
//    public String toString() {
//        return "Title: " + this.title + "\nAuthor: " + this.author + "\nYear Published: "
//                + this.yearPublished + "\nISBN: " + this.iSBN + "\nDD: " + this.deweyDecimal;
//    }

    @Override
    public HashMap<Library, Integer> getAvailability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Period getCheckoutPeriod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

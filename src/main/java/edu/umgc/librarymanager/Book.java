/*
 * Filename: Book.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class is used to model a Book.
 */

package edu.umgc.librarymanager;

/**
 * This class models a Book.
 */
public class Book {

    private String title;
    private String author;
    private String yearPublished;
    private String iSBN;
    private String deweyDecimal;
    private boolean isChecked;

    /**
     * The default constructor of the Book class.
     */
    public Book() {
        this.title = "";
        this.author = "";
        this.yearPublished = "";
        this.iSBN = "";
        this.deweyDecimal = "";
        this.isChecked = false;
    }

    /**
     * A constructor to assign each value to a Book Object.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param yearPublished The year published.
     * @param iSBN The ISBN of the book.
     * @param deweyDecimal The Dewey Decimal System value.
     * @param isChecked A flag for whether the book is checked out.
     */
    public Book(String title, String author, String yearPublished, String iSBN,
            String deweyDecimal, boolean isChecked) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.iSBN = iSBN;
        this.deweyDecimal = deweyDecimal;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYearPublished() {
        return this.yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getISBN() {
        return this.iSBN;
    }

    public void setISBN(String str) {
        this.iSBN = str;
    }

    public String getDeweyDecimal() {
        return this.deweyDecimal;
    }

    public void setDeweyDecimal(String deweyDecimal) {
        this.deweyDecimal = deweyDecimal;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + this.author + "\nYear Published: "
                + this.yearPublished + "\nISBN: " + this.iSBN + "\nDD: " + this.deweyDecimal;
    }

}

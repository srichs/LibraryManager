/*
 * Filename: Book.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class is used to model a Book.
 */


/**
 * This class models a Book.
 */
public class Book {

    private String title;
    private String author;
    private String yearPublished;
    private String iSBN;
    private boolean isChecked; //TODO

    public Book() {
        this.title = "";
        this.author = "";
        this.yearPublished = "";
        this.iSBN = "";
        this.isChecked = false;
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

    public void setISBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + this.author + "\nYear Published: " + this.yearPublished + "\nISBN: " + this.iSBN;
    }
    
}

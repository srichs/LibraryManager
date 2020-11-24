/*
 * Filename: ItemType.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

/**
 * An enum for the type of Item.
 * @author Scott
 */
public enum ItemType {

    /**
     * Enum for a Book Item.
     */
    Book,
    /**
     * Enum for a E-Book Item.
     */
    EBook,
    /**
     * Enum for a Movie Item.
     */
    Movie,
    /**
     * Enum for a Game Item.
     */
    VideoGame;

    public ItemType getItemType() {
        return this;
    }

    /**
     * Returns the ItemType based on an inputted String parameter.
     * @param type The ItemType String
     * @return The ItemType value of the String.
     */
    public static ItemType stringToItemType(String type) {
        switch (type) {
            case "Book":
                return Book;
            case "E-Book":
                return EBook;
            case "Movie":
                return Movie;
            case "Video Game":
                return VideoGame;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case Book:
                return "Book";
            case EBook:
                return "E-Book";
            case Movie:
                return "Movie";
            case VideoGame:
                return "Video Game";
            default:
                return "";
        }
    }

}

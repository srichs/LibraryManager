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
    BOOK,
    /**
     * Enum for a E-Book Item.
     */
    EBOOK,
    /**
     * Enum for a Movie Item.
     */
    MOVIE,
    /**
     * Enum for a Game Item.
     */
    GAME;

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
                return BOOK;
            case "E-Book":
                return EBOOK;
            case "Movie":
                return MOVIE;
            case "Video Game":
                return GAME;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case BOOK:
                return "Book";
            case EBOOK:
                return "E-Book";
            case MOVIE:
                return "Movie";
            case GAME:
                return "Video Game";
            default:
                return "";
        }
    }

}

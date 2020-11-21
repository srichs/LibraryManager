/*
 * Filename: UserType.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

/**
 * An enum for the type of User.
 * @author Scott
 */
public enum UserType {

    /**
     * Enum for a Librarian User.
     */
    LIBRARIAN,
    /**
     * Enum for a Patron User.
     */
    PATRON;

    public UserType getUserType() {
        return this;
    }

    /**
     * Returns the UserType based on an inputted String parameter.
     * @param type The UserType String
     * @return The UserType value of the String.
     */
    public static UserType stringToUserType(String type) {
        switch (type) {
            case "Librarian":
                return LIBRARIAN;
            case "Patron":
                return PATRON;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case LIBRARIAN:
                return "Librarian";
            case PATRON:
                return "Patron";
            default:
                return "";
        }
    }

}

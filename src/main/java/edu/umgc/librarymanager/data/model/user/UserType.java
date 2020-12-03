/*
 * Filename: UserType.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

/**
 * An enum for the type of User.
 * @author Scott
 */
public enum UserType {

    /**
     * Enum for a Librarian User.
     */
    Librarian,
    /**
     * Enum for a Patron User.
     */
    Patron;

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
                return Librarian;
            case "Patron":
                return Patron;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     * @return A String value for the type.
     */
    public String toString() {
        switch (this) {
            case Librarian:
                return "Librarian";
            case Patron:
                return "Patron";
            default:
                return "";
        }
    }

}

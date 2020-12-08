/*
 * Filename: UserField.java
 * Author: Scott
 * Date Created: 12/4/2020
 */

package edu.umgc.librarymanager.data.access;

/**
 * An enum for the type of field in the BaseItem class. It is used with hibernate
 * search to help with building queries.
 * @author Scott
 */
public enum UserField {

    /**
     * An enum value for the dateTimeCreated field.
     */
    DateTimeCreated,
    /**
     * An enum value for the email field.
     */
    Email,
    /**
     * An enum value for the firstName field.
     */
    FirstName,
    /**
     * An enum value for the lastName field.
     */
    LastName,
    /**
     * An enum value for the username field.
     */
    Username;

    public UserField getUserField() {
        return this;
    }

    /**
     * Returns the UserField based on an inputted String parameter.
     * @param type The UserField String
     * @return The UserField value of the String.
     */
    public static UserField stringToUserField(String type) {
        switch (type) {
            case "date_time_created":
                return DateTimeCreated;
            case "email":
                return Email;
            case "first_name":
                return FirstName;
            case "last_name":
                return LastName;
            case "login.username":
                return Username;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case DateTimeCreated:
                return "date_time_created";
            case Email:
                return "email";
            case FirstName:
                return "first_name";
            case LastName:
                return "last_name";
            case Username:
                return "login.username";
            default:
                return "";
        }
    }

}

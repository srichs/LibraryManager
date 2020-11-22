/*
 * Filename: ClassificationType.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

/**
 * An enum for the type of Classification.
 * @author Scott
 */
public enum ClassificationType {

    /**
     * Enum for a Dewey Decimal Classification.
     */
    DEWEY_DECIMAL,
    /**
     * Enum for a Library of Congress Classification.
     */
    LIBRARY_OF_CONGRESS;

    public ClassificationType getClassificationType() {
        return this;
    }

    /**
     * Returns the ClassificationType based on an inputted String parameter.
     * @param type The ClassificationType String
     * @return The ClassificationType value of the String.
     */
    public static ClassificationType stringToClassificationType(String type) {
        switch (type) {
            case "Dewey Decimal":
                return DEWEY_DECIMAL;
            case "Library of Congress":
                return LIBRARY_OF_CONGRESS;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case DEWEY_DECIMAL:
                return "Dewey Decimal";
            case LIBRARY_OF_CONGRESS:
                return "Library of Congress";
            default:
                return "";
        }
    }

}

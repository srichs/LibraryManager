/*
 * Filename: ClassType.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

/**
 * An enum for the type of Classification.
 * @author Scott
 */
public enum ClassType {

    /**
     * Enum for a Dewey Decimal Classification.
     */
    DeweyDecimal,
    /**
     * Enum for a Library of Congress Classification.
     */
    LibraryOfCongress;

    public ClassType getClassType() {
        return this;
    }

    /**
     * Returns the ClassificationType based on an inputted String parameter.
     * @param type The ClassificationType String
     * @return The ClassificationType value of the String.
     */
    public static ClassType stringToClassType(String type) {
        switch (type) {
            case "Dewey Decimal":
                return DeweyDecimal;
            case "Library of Congress":
                return LibraryOfCongress;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case DeweyDecimal:
                return "Dewey Decimal";
            case LibraryOfCongress:
                return "Library of Congress";
            default:
                return "";
        }
    }

}

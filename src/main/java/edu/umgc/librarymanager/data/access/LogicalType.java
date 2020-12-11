/*
 * Filename: LogicalType.java
 * Author: Scott
 * Date Created: 12/9/2020
 */

package edu.umgc.librarymanager.data.access;

/**
 * This is an enum for the values of different boolean operators.
 * @author Scott
 */
public enum LogicalType {

    /**
     * An enum value for an And operator.
     */
    And,
    /**
     * An enum value for an Or operator.
     */
    Or,
    /**
     * An enum value for an Not operator.
     */
    Not;

    public LogicalType getLogicalType() {
        return this;
    }

    /**
     * Returns the LogicalType based on an inputted String parameter.
     * @param type The LogicalType String
     * @return The LogicalType value of the String.
     */
    public static LogicalType stringToLogicalType(String type) {
        switch (type) {
            case "And":
                return And;
            case "Or":
                return Or;
            case "Not":
                return Not;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case And:
                return "And";
            case Or:
                return "Or";
            case Not:
                return "Not";
            default:
                return "";
        }
    }

}

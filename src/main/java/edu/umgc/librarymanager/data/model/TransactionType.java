/*
 * Filename: TransactionType.java
 * Author: Scott
 * Date Created: 11/26/2020
 */

package edu.umgc.librarymanager.data.model;

/**
 * An enum for the type of Transaction.
 * @author Scott
 */
public enum TransactionType {

    /**
     * An Enum type for when an item is Checked Out.
     */
    CheckOut,
    /**
     * An Enum type for when a Checked Out item is extended.
     */
    Renew,
    /**
     * An Enum type for when an item is Returned.
     */
    Return,
    /**
     * An Enum type for when an item is Reserved.
     */
    Reserve;

    public TransactionType getTransactionType() {
        return this;
    }

    /**
     * Returns the TransactionType based on an inputted String parameter.
     * @param type The TransactionType String
     * @return The TransactionType value of the String.
     */
    public static TransactionType stringToTransactionType(String type) {
        switch (type) {
            case "Check Out":
                return CheckOut;
            case "Renew":
                return Renew;
            case "Return":
                return Return;
            case "Reserve":
                return Reserve;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case CheckOut:
                return "Check Out";
            case Renew:
                return "Renew";
            case Return:
                return "Return";
            case Reserve:
                return "Reserve";
            default:
                return "";
        }
    }

}

/*
 * Filename: ItemStatus.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

/**
 * An enumeration to describe the status of a Library Item.
 * @author David
 */
public enum ItemStatus {

    /**
     * Item is available for checkout.
     */
    AVAILABLE,
    /**
     * Item has been reserved by another user.
     */
    ON_HOLD,
    /**
     * Item has been checked out.
     */
    CHECKED_OUT,
    /**
     * The item is in transit.
     */
    IN_TRANSIT;

    public ItemStatus getItemStatus() {
        return this;
    }

    /**
     * Returns the ItemStatus based on an inputted String parameter.
     * @param type The ItemStatus String
     * @return The ItemStatus value of the String.
     */
    public static ItemStatus stringToItemStatus(String type) {
        switch (type) {
            case "Available":
                return AVAILABLE;
            case "On Hold":
                return ON_HOLD;
            case "Checked Out":
                return CHECKED_OUT;
            case "In Transit":
                return IN_TRANSIT;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case AVAILABLE:
                return "Available";
            case ON_HOLD:
                return "On Hold";
            case CHECKED_OUT:
                return "Checked Out";
            case IN_TRANSIT:
                return "In Transit";
            default:
                return "";
        }
    }

}

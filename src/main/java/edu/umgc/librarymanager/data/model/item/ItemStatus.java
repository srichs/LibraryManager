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
    Available,
    /**
     * Item has been reserved by another user.
     */
    OnHold,
    /**
     * Item has been checked out.
     */
    CheckedOut,
    /**
     * The item is in transit.
     */
    InTransit;

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
                return Available;
            case "On Hold":
                return OnHold;
            case "Checked Out":
                return CheckedOut;
            case "In Transit":
                return InTransit;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case Available:
                return "Available";
            case OnHold:
                return "On Hold";
            case CheckedOut:
                return "Checked Out";
            case InTransit:
                return "In Transit";
            default:
                return "";
        }
    }

}

/*
 * Filename: ItemViewType.java
 * Author: Scott
 * Date Created: 12/7/2020
 */

package edu.umgc.librarymanager.gui.panels;

/**
 * This is an enum type for the ItemView panel.
 * @author Scott
 */
public enum ItemViewType {

    /**
     * An enum value for an ItemView panel that displays 'View' and 'Delete' options.
     */
    View,
    /**
     * An enum value for an ItemView panel that displays a 'Return' button.
     */
    Return_,
    /**
     * An enum value for an ItemView panel that displays a 'Renew' button.
     */
    Renew,
    /**
     * An enum value for an ItemView panel that displays a 'Reserve' button.
     */
    Reserve,
    /**
     * An enum value for an ItemView panel that displays no buttons.
     */
    Checkout;

    public ItemViewType getItemViewType() {
        return this;
    }

    /**
     * Returns the ItemViewType based on an inputted String parameter.
     * @param type The ItemViewType String
     * @return The ItemViewType value of the String.
     */
    public static ItemViewType stringToItemViewType(String type) {
        switch (type) {
            case "View":
                return View;
            case "Return":
                return Return_;
            case "Renew":
                return Renew;
            case "Reserve":
                return Reserve;
            case "Checkout":
                return Checkout;
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
            case View:
                return "View";
            case Return_:
                return "Return";
            case Renew:
                return "Renew";
            case Reserve:
                return "Reserve";
            case Checkout:
                return "Checkout";
            default:
                return "";
        }
    }

}

/*
 * Filename: ItemField.java
 * Author: Scott
 * Date Created: 12/4/2020
 */

package edu.umgc.librarymanager.data.access;

/**
 * This is an enum for the values of the BaseItem's fields.
 * @author Scott
 */
public enum ItemField {

    /**
     * An enum value for the title field.
     */
    Title("Title"),
    /**
     * An enum value for the description field.
     */
    Description("Description"),
    /**
     * An enum value for the genre field.
     */
    Genre("Genre"),
    /**
     * An enum value for the summary field.
     */
    Summary("Summary"),
    /**
     * An enum value for the dewey code field.
     */
    DeweyCode("Dewey Code"),
    /**
     * An enum value for the loc code field.
     */
    LOCCode("LoC Code"),
    /**
     * An enum value for the publisher field.
     */
    Publisher("Publisher"),
    /**
     * An enum value for the publish location field.
     */
    PublishLocation("Publish Location"),
    /**
     * An enum value for the status field.
     */
    Status("Status");

    public final String label;

    private ItemField(String label) {
        this.label = label;
    }

    public static ItemField valueOfLabel(String label) {
        for (ItemField field : values()) {
            if (field.label.equals(label)) {
                return field;
            }
        }
        return null;
    }

    public ItemField getItemField() {
        return this;
    }

    /**
     * Returns the ItemField based on an inputted String parameter.
     * @param type The ItemField String
     * @return The ItemField value of the String.
     */
    public static ItemField stringToItemField(String type) {
        switch (type) {
            case "classGroup.dewey.code":
                return DeweyCode;
            case "classGroup.loc.code":
                return LOCCode;
            case "description":
                return Description;
            case "title":
                return Title;
            case "publisher.publisher":
                return Publisher;
            case "publisher.publish_location":
                return PublishLocation;
            case "genre":
                return Genre;
            case "summary":
                return Summary;
            case "status":
                return Status;
            default:
                return null;
        }
    }

    /**
     * ToString Method of the Enum Type.
     */
    public String toString() {
        switch (this) {
            case DeweyCode:
                return "classGroup.dewey.code";
            case LOCCode:
                return "classGroup.loc.code";
            case Description:
                return "description";
            case Title:
                return "title";
            case Publisher:
                return "publisher.publisher";
            case PublishLocation:
                return "publisher.publish_location";
            case Genre:
                return "genre";
            case Summary:
                return "summary";
            case Status:
                return "status";
            default:
                return "";
        }
    }

}

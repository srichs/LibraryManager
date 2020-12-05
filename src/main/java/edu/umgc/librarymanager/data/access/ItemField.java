/*
 * Filename: ItemField.java
 * Author: Scott
 * Date Created: 12/4/2020
 */

package edu.umgc.librarymanager.data.access;

/**
 * This class
 * @author Scott
 */
public enum ItemField {

    /**
     * An enum value for the dewey code field.
     */
    DeweyCode,
    /**
     * An enum value for the loc code field.
     */
    LOCCode,
    /**
     * An enum value for the description field.
     */
    Description,
    /**
     * An enum value for the title field.
     */
    Title,
    /**
     * An enum value for the publisher field.
     */
    Publisher,
    /**
     * An enum value for the publish date field.
     */
    PublishDate,
    /**
     * An enum value for the publish location field.
     */
    PublishLocation,
    /**
     * An enum value for the genre field.
     */
    Genre,
    /**
     * An enum value for the summary field.
     */
    Summary,
    /**
     * An enum value for the status field.
     */
    Status;

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
            case "publisher.publish_date":
                return PublishDate;
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
            case PublishDate:
                return "publisher.publish_date";
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

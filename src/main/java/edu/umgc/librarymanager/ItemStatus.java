/*
 * Filename: ItemStatus.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager;

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
    InTransit

}

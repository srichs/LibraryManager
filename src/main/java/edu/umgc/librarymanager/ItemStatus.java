/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/*
 * Filename: Classification.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model;

/**
 * An Interface to implement the Classification System for an Item.
 * @author David
 */
public interface Classification {

    /**
     * An enumeration for the type of classification system.
     */
    enum ClassType {
        DeweyDecimal,
        LibraryOfCongress
    }

    String getCode();

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umgc.librarymanager;

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umgc.librarymanager;

import edu.umgc.librarymanager.item.ILibraryItem;
import java.time.ZonedDateTime;

/**
 *
 * @author David
 */
public interface ILibraryTransaction {

    Library getLibrary();
    ILibraryItem getItem();
    IUser getUser();
    ZonedDateTime getTransactionDateTime();
    ZonedDateTime getDueDate();
    double getFee();
    ZonedDateTime getRenewDate();
    int getRenewCount();

}

/*
 * Filename: ILibraryTransaction.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager;

import edu.umgc.librarymanager.data.model.Library;
import edu.umgc.librarymanager.data.model.item.ILibraryItem;
import edu.umgc.librarymanager.data.model.user.IUser;

import java.time.ZonedDateTime;

/**
 * An interface that is used to implement a Transaction between a User and a Library.
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

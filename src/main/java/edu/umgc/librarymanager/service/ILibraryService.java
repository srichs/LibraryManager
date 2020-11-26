/*
 * Filename: ILibraryService.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.data.model.ILibraryTransaction;
import edu.umgc.librarymanager.data.model.item.ILibraryItem;
import edu.umgc.librarymanager.data.model.user.IUser;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * An Interface to implement for a Service that is provided by a Library.
 * @author David
 */
public interface ILibraryService {

    List<ILibraryItem> searchItems(SearchCriteria criteria);
    ILibraryTransaction checkoutItem(ILibraryItem item, IUser user);
    ILibraryTransaction reserveItem(ILibraryItem item, IUser user);
    ILibraryTransaction renewItem(ILibraryItem item, IUser user);
    List<ILibraryTransaction> getBorrowedItems(IUser user);
    double getFeesOwed(IUser user);
    List<ILibraryTransaction> getBorrowedItemsHistory(IUser user,
            ZonedDateTime startDate, ZonedDateTime endDate);

}

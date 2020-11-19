/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umgc.librarymanager;

import edu.umgc.librarymanager.item.ILibraryItem;
import java.time.ZonedDateTime;
import java.util.List;

/**
 *
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

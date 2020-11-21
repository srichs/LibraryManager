/*
 * Filename: IUser.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager;

import java.time.ZonedDateTime;

/**
 * An interface to be implemented for a user of the Library.
 * @author David
 */
public interface IUser {

    long getId();
    ZonedDateTime createdDateTime();
    String getUserName();
    String getPassword();
    String getEmail();
    String getAddress();
    String getPhoneNumber();

}

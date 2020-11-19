/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umgc.librarymanager;

import java.time.ZonedDateTime;

/**
 *
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

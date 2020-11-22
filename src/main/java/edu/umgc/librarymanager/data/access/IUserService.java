/*
 * Filename: IUserService.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.user.IUser;

/**
 * An interface to implement a Service for a user.
 * 
 * @author David
 */
public interface IUserService {

    void createUser(IUser newUser);
    void updateUser(IUser user);

}

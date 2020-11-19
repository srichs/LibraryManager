/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umgc.librarymanager;

import edu.umgc.librarymanager.item.ILibraryItem;
import java.util.List;

/**
 *
 * @author David
 */
public interface IUserService {
    void createUser(IUser newUser);
    void updateUser(IUser user);
}

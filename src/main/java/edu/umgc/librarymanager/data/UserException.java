/*
 * Filename: UserException.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

public class UserException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

}

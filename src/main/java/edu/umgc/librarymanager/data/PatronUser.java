/*
 * Filename: PatronUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

/**
 * This class is the concrete class for a Patron User and extends the BaseUser class.
 * @author Scott
 */
public class PatronUser extends BaseUser {

    public PatronUser() {
        super();
        super.setUserType(UserType.PATRON);
    }

    /**
     * This is a constructor with parameters for the class variables.
     * @param fName The User's First Name.
     * @param lName The User's Last Name.
     * @param login The login information for the User.
     * @param phone The User's Phone Number.
     * @param email The User's Email Address.
     */
    public PatronUser(String fName, String lName, UserLogin login, String phone, String email) {
        super(fName, lName, login, phone, email, UserType.PATRON);
    }

}

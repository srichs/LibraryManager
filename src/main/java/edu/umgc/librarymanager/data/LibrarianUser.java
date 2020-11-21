/*
 * Filename: LibrarianUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

/**
 * This class is the concrete class for a Librarian User and extends the BaseUser class.
 * @author Scott
 */
public class LibrarianUser extends BaseUser {

    /**
     * The default constructor for the class.
     */
    public LibrarianUser() {
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
    public LibrarianUser(String fName, String lName, UserLogin login, String phone, String email) {
        super(fName, lName, login, phone, email, UserType.LIBRARIAN);
    }

}

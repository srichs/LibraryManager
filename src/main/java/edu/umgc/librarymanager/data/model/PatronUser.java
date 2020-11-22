/*
 * Filename: PatronUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model;

import java.time.ZonedDateTime;

/**
 * This class is the concrete class for a Patron User and extends the BaseUser
 * class.
 * 
 * @author Scott
 */
public class PatronUser extends BaseUser {

    public PatronUser() {
        super();
        super.setUserType(UserType.PATRON);
    }

    /**
     * This is a constructor with parameters for the class variables.
     * @param cDateTime The Date and Time of creation.
     * @param fName The User's First Name.
     * @param lName The User's Last Name.
     * @param login The login information for the User.
     * @param email The User's Email Address.
     * @param address The User's Address.
     * @param phone The User's Phone Number.
     */
    public PatronUser(ZonedDateTime cDateTime, String fName, String lName, UserLogin login, String email,
            String address, String phone) {
        super(cDateTime, fName, lName, login, email, address, phone, UserType.PATRON);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public ZonedDateTime createdDateTime() {
        return super.getCreatedDateTime();
    }

    @Override
    public String getUserName() {
        return super.getLogin().getUsername();
    }

    @Override
    public String getPassword() {
        return super.getLogin().getPassword();
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhone();
    }

}

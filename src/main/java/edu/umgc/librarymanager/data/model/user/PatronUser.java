/*
 * Filename: PatronUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import org.hibernate.search.annotations.Indexed;

/**
 * This class is the concrete class for a Patron User and extends the BaseUser
 * class.
 * @author Scott
 */
@Entity
@Indexed
public class PatronUser extends BaseUser {

    public PatronUser() {
        super();
        super.setUserType(UserType.Patron);
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
        super(cDateTime, fName, lName, login, email, address, phone, UserType.Patron);
    }

}

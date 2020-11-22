/*
 * Filename: LibrarianUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class is the concrete class for a Librarian User and extends the
 * BaseUser class.
 * @author Scott
 */
@Entity
@Table(name = "librarian_user")
public class LibrarianUser extends BaseUser {

    /**
     * The default constructor for the class.
     */
    public LibrarianUser() {
        super();
        super.setUserType(UserType.LIBRARIAN);
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
    public LibrarianUser(ZonedDateTime cDateTime, String fName, String lName, UserLogin login, String email,
            String address, String phone) {
        super(cDateTime, fName, lName, login, email, address, phone, UserType.LIBRARIAN);
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

/*
 * Filename: BaseUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Models a library management system user. Uses hibernate annotations to map the
 * user's data to the hibernate database.
 * @author Scott
 */
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "base_user")
public abstract class BaseUser implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "base_user_id")
    private int id;

    @Column(name = "created_date_time")
    private ZonedDateTime createdDateTime;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private UserLogin login;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private UserType userType;

    /**
     * Default constructor of the class.
     */
    public BaseUser() {
        this.id = -1;
        this.createdDateTime = ZonedDateTime.now();
        this.firstName = "";
        this.lastName = "";
        this.login = new UserLogin();
        this.email = "";
        this.address = "";
        this.phone = "";
        this.userType = null;
    }

    /**
     * Constructor of the class.
     * @param cDateTime The Date and Time of creation.
     * @param fName The User's First Name.
     * @param lName The User's Last Name.
     * @param login The login information for the User.
     * @param email The User's Email Address.
     * @param address The User's Address.
     * @param phone The User's Phone Number.
     * @param uType The type of user.
     */
    public BaseUser(ZonedDateTime cDateTime, String fName, String lName, UserLogin login, String email,
            String address, String phone, UserType uType) {
        this.createdDateTime = cDateTime;
        this.firstName = fName;
        this.lastName = lName;
        this.login = login;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.userType = uType;
    }

    public long getId() {
        return this.id;
    }

    public ZonedDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(ZonedDateTime zdt) {
        this.createdDateTime = zdt;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public UserLogin getLogin() {
        return this.login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType type) {
        this.userType = type;
    }

    /**
     * Creates a String with the base user information.
     */
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\nUsername: " + this.login.getUsername()
                + "\nPassword: " + this.login.getPassword() + "\nPhone: " + this.phone + "\nEmail: " + this.email
                + "\nUser Type: " + this.userType.toString();
    }

}

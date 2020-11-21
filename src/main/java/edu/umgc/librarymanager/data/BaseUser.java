/*
 * Filename: BaseUser.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data;

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
 */
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "base_user")
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abstract_user_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private UserLogin login;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private UserType userType;

    /**
     * Default constructor of the class.
     */
    public BaseUser() {
        this.firstName = "";
        this.lastName = "";
        this.login = new UserLogin();
        this.phone = "";
        this.email = "";
        this.userType = null;
    }

    /**
     * Constructor of the class.
     * @param fName The User's First Name.
     * @param lName The User's Last Name.
     * @param login The login information for the User.
     * @param phone The User's Phone Number.
     * @param email The User's Email Address.
     * @param uType The type of user.
     */
    public BaseUser(String fName, String lName, UserLogin login, String phone, String email, UserType uType) {
        this.firstName = fName;
        this.lastName = lName;
        this.login = login;
        this.phone = phone;
        this.email = email;
        this.userType = uType;
    }

    public int getID() {
        return this.id;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

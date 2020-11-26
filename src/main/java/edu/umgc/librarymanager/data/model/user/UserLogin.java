/*
 * Filename: UserLogin.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Models a user's login credentials. Uses the jbcrypt library to encrypt a
 * user's password. Uses hibernate annotations that will map the state of the
 * class to the hibernate database.
 * @author Scott
 */
@Entity
@Table(name = "user_login")
public class UserLogin {

    private static final int WORK_FACTOR = 12;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_login_id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    /**
     * The default constructor for the class.
     */
    public UserLogin() {
        this.username = "";
        this.password = "";
    }

    /**
     * A constructor with parameters for each field.
     * @param username The username.
     * @param password The password in plaintext.
     */
    public UserLogin(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public void setHashedPassword(String hashedPw) {
        this.password = hashedPw;
    }

    /**
     * Hashes a password and returns the hashed value.
     * @param pword The password in plaintext.
     * @return The hashed password.
     */
    private String hashPassword(String pword) {
        return BCrypt.hashpw(pword, BCrypt.gensalt(WORK_FACTOR));
    }

    /**
     * Checks a plaintext password against the hashed password.
     * @param pword The password in plaintext.
     * @return A boolean value for whether the password matches.
     */
    public boolean checkPassword(String pword) {
        if (BCrypt.checkpw(pword, this.password)) {
            return true;
        }
        return false;
    }

    /**
     * Checks a plaintext password against the hashed password.
     * @param pword The password in plaintext as a char array.
     * @return A boolean value for whether the password matches.
     */
    public boolean checkPassword(char[] pword) {
        String pw = String.valueOf(pword);
        if (BCrypt.checkpw(pw, this.password)) {
            return true;
        }
        return false;
    }

}

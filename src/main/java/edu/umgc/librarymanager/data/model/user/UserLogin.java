/*
 * Filename: UserLogin.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import java.util.HashMap;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Models a user's login credentials. Uses the jbcrypt library to encrypt a
 * user's password. Uses hibernate annotations that will map the state of the
 * class to the hibernate database.
 * @author Scott
 */
@Entity
@Indexed
@Table(name = "user_login")
public class UserLogin {

    private static final int WORK_FACTOR = 12;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_login_id")
    private long id;

    @Field
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

    public UserLogin(String username, char[] password) {
        this.username = username;
        this.password = hashPassword(String.valueOf(password));
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

    public void setPassword(char[] password) {
        this.password = hashPassword(String.valueOf(password));
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

    /**
     * Generates a random password with the given number of characters.
     * @param numChars The number of characters to make the password.
     * @return A String containing the password.
     */
    public static String genPasswordStr(int numChars) {
        String password = "";
        for (int i = 0; i < numChars; i++) {
            Random r = new Random();
            int result = r.nextInt(90 - 33) + 33;
            password += Character.toString(result);
        }
        return password;
    }

    /**
     * Generates a random password with the given number of characters.
     * @param numChars The number of characters to make the password.
     * @return A char array containing the password.
     */
    public static char[] genPasswordChar(int numChars) {
        char[] password = new char[numChars];
        for (int i = 0; i < numChars; i++) {
            Random r = new Random();
            int result = r.nextInt(90 - 33) + 33;
            password[i] = (char) result;
        }
        return password;
    }

    /**
     * Generates a username based on the existing usernames and the users first and last name. It creates a
     * username that is the initial of the first name, then the last name, then a number for however many
     * people had the same username.
     * @param users A HashMap of the users in the database.
     * @param firstName The first name of the user to add.
     * @param lastName The last name of the user to add.
     * @return A username of the the proper format that is unique.
     */
    public static String genUsername(HashMap<String, BaseUser> users, String firstName, String lastName) {
        int count = 1;
        String unameBase = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        unameBase = unameBase.replace(" ", "");
        unameBase = unameBase.replace("'", "");
        unameBase = unameBase.replace("-", "");
        String username = unameBase + count;
        while (users.containsKey(username)) {
            count++;
            username = unameBase + count;
        }
        return username;
    }

}

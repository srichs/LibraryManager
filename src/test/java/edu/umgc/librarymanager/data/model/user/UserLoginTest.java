/*
 * Filename: UserLoginTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A Test Class to test the UserLogin class.
 * @author Scott
 */
public class UserLoginTest {

    private UserLogin login;
    private UserLogin login2;

    @Before
    public void setUp() {
        login = new UserLogin("jdoe1", "12345678");
        login2 = new UserLogin();
        login2.setUsername("dsmith1");
        login2.setPassword("87654321");
    }

    @Test
    public void getUsername_Test() {
        assertEquals("jdoe1", login.getUsername());
    }

    @Test
    public void setUsername_Test() {
        assertEquals("dsmith1", login2.getUsername());
    }

    @Test
    public void getPassword_Test() {
        assertNotEquals("12345678", login.getPassword());
    }

    @Test
    public void setPassword_Test() {
        assertNotEquals("87654321", login2.getPassword());
    }

    @Test
    public void checkPassword_Test() {
        assertEquals(true, login.checkPassword("12345678"));
    }

}

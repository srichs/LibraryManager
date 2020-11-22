/*
 * Filename: UserLoginDAOTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.user.UserLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class for the UserDAO class.
 * @author Scott
 */
public class UserLoginDAOTest {

    private UserLoginDAO loginDAO;
    private UserLogin login;

    @Before
    public void setUp() {
        login = new UserLogin("jdoe1", "12345678");
        loginDAO = new UserLoginDAO();
        loginDAO.openSessionwithTransaction();
        loginDAO.persist(login);
        login = loginDAO.findAll().get(0);
    }

    @After
    public void tearDown() {
        loginDAO.closeSessionwithTransaction();
    }

    @Test
    public void findAll_Test() {
        assertEquals(1, loginDAO.findAll().size());
    }

    @Test
    public void searchByUsername_Test() {
        assertEquals(true, loginDAO.searchByUsername("jdoe1"));
    }

    @Test
    public void authenticate_Test() {
        assertEquals(true, loginDAO.authenicateUser("jdoe1", "12345678"));
    }

    @Test
    public void update_Test() {
        login.setUsername("dsmith1");
        login.setPassword("87654321");
        loginDAO.update(login);
        assertEquals("dsmith1", loginDAO.findAll().get(0).getUsername());
        assertEquals(true, loginDAO.findAll().get(0).checkPassword("87654321"));
    }

    @Test
    public void deleteAll_Test() {
        loginDAO.deleteAll();
        assertEquals(0, loginDAO.findAll().size());
    }

}

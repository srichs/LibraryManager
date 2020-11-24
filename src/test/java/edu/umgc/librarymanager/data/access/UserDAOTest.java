/*
 * Filename: UserDAOTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.PatronUser;
import edu.umgc.librarymanager.data.model.user.UserLogin;
import java.time.ZonedDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class for the UserDAO class.
 * @author Scott
 */
public class UserDAOTest {

    private UserDAO userDAO;
    private BaseUser user;

    @Before
    public void setUp() {
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        UserLogin login = new UserLogin("jdoe1", "12345678");
        user = new PatronUser(zdt, "John", "Doe", login, "john.doe@gmail.com", "123 Fake St, Los Angeles, CA 90220",
                "555-867-5309");
        userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        userDAO.createUser(user);
        user = userDAO.findAll().get(0);
        userDAO.commit();
    }

    @After
    public void tearDown() {
        userDAO.getTransaction().begin();
        userDAO.deleteAll();
        userDAO.closeSessionwithTransaction();
    }

    @Test
    public void findAll_Test() {
        assertEquals(1, userDAO.findAll().size());
    }

    @Test
    public void update_Test() {
        userDAO.getTransaction().begin();
        user.setFirstName("David");
        user.setLastName("Smith");
        userDAO.update(user);
        assertEquals("David", userDAO.findAll().get(0).getFirstName());
        userDAO.commit();
    }

    @Test
    public void deleteAll_Test() {
        userDAO.getTransaction().begin();
        userDAO.deleteAll();
        assertEquals(0, userDAO.findAll().size());
        userDAO.commit();
    }

}

/*
 * Filename: BaseUserTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.user;

import java.time.ZonedDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the BaseUser class.
 * @author Scott
 */
public class BaseUserTest {

    private PatronUser user;

    @Before
    public void setUp() {
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        UserLogin login = new UserLogin("jdoe1", "12345678");
        user = new PatronUser(zdt, "John", "Doe", login, "john.doe@gmail.com", "123 Fake St, Los Angeles, CA 90220",
                "555-867-5309");
    }

    @Test
    public void getCreatedDateTime_Test() {
        assertEquals("2019-12-05T10:48-05:00[America/New_York]", user.getCreatedDateTime().toString());
    }

    @Test
    public void getFirstName_Test() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void getLastName_Test() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void getLogin_Test() {
        assertEquals("jdoe1", user.getLogin().getUsername());
    }

    @Test
    public void getUsername_Test() {
        assertEquals("jdoe1", user.getUserName());
    }

    @Test
    public void getEmail_Test() {
        assertEquals("john.doe@gmail.com", user.getEmail());
    }

    @Test
    public void getAddress_Test() {
        assertEquals("123 Fake St, Los Angeles, CA 90220", user.getAddress());
    }

    @Test
    public void getPhone_Test() {
        assertEquals("555-867-5309", user.getPhone());
    }

    @Test
    public void getUserType_Test() {
        assertEquals(UserType.PATRON, user.getUserType());
        BaseUser user2 = new LibrarianUser();
        assertEquals(UserType.LIBRARIAN, user2.getUserType());
    }

}

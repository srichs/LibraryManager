/*
 * Filename: AuthorTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the Author class.
 * @author Scott
 */
public class AuthorTest {

    private Author author;
    private Author author2;

    @Before
    public void setUp() {
        author = new Author("John", "Thomas", "Jackson", "Jr.");
        author2 = new Author();
        author2.setFirstName("David");
        author2.setMiddleName("L.");
        author2.setLastName("Smith");
    }

    @Test
    public void getFirstName_Test() {
        assertEquals("John", author.getFirstName());
        assertEquals("David", author2.getFirstName());
    }

    @Test
    public void getMiddleName_Test() {
        assertEquals("Thomas", author.getMiddleName());
        assertEquals("L.", author2.getMiddleName());
    }

    @Test
    public void getLastName_Test() {
        assertEquals("Jackson", author.getLastName());
        assertEquals("Smith", author2.getLastName());
    }

    @Test
    public void getSuffix_Test() {
        assertEquals("Jr.", author.getSuffix());
        assertEquals("", author2.getSuffix());
    }

    @Test
    public void toString_Test() {
        assertEquals("John Thomas Jackson Jr.", author.toString());
        assertEquals("David L. Smith", author2.toString());
    }

}

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
        author = new Author("John Thomas Jackson Jr.");
        author2 = new Author();
        author2.setName("David L. Smith");
    }

    @Test
    public void getName_Test() {
        assertEquals("John Thomas Jackson Jr.", author.getName());
        assertEquals("David L. Smith", author2.getName());
    }

}

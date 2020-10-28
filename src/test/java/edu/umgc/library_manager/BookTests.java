/*
 * Filename: BookTests.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class is used to test the Book class.
 */

package edu.umgc.library_manager;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 * This class tests the Book Class.
 */
public class BookTests {
    private Book book;
    private Book book2;

    @Before
    public void setUp() {
        book = new Book("1984", "George Orwell", "1949", "9781443434973", "823.912", false);
        book2 = new Book();
        book2.setTitle("1984");
        book2.setAuthor("George Orwell");
        book2.setYearPublished("1949");
        book2.setISBN("9781443434973");
        book2.setDeweyDecimal("823.912");
        book2.setIsChecked(false);
        System.out.println("BookTests.setUp Complete");
    }

    @Test
    public void toString_Test() {
        assertEquals("Title: 1984\nAuthor: George Orwell\nYear Published: 1949\nISBN: 9781443434973\nDD: 823.912", book.toString());
        assertEquals("Title: 1984\nAuthor: George Orwell\nYear Published: 1949\nISBN: 9781443434973\nDD: 823.912", book2.toString());
        System.out.println("BookTests.toString_Test Complete");
    }

}
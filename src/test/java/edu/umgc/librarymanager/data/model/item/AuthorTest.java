/*
 * Filename: AuthorTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void getBooks_Test() {
        ClassificationGroup classGroup = new ClassificationGroup();
        classGroup.setDewey(new Classification("004.32", ClassificationType.DeweyDecimal));
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        ZonedDateTime zdt2 = ZonedDateTime.parse("2019-12-15T12:48:00-05:00[America/New_York]");
        PublishData publish = new PublishData("A Publisher", zdt2, "Denver, CO");
        LocalDate checkDate = LocalDate.of(2020, Month.NOVEMBER, 5);
        LocalDate dueDate = LocalDate.of(2020, Month.NOVEMBER, 26);
        Period period = Period.between(checkDate, dueDate);
        List<Author> authors = new ArrayList<Author>();
        authors.add(new Author(author2.getName()));
        BaseBook book = new Book(classGroup, zdt, "Description.", new BigDecimal("23.48"), "Some Title",
                publish, "A Genre", "The summary.", ItemStatus.Available, period, authors, "9283923231865");
        author2.getBooks().add(book);
        assertEquals("Some Title", author2.getBooks().get(0).getTitle());
    }

}

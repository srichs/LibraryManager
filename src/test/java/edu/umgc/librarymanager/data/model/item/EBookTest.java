/*
 * Filename: EBookTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the Book class.
 * @author Scott
 */
public class EBookTest {

    private Ebook ebook;

    @Before
    public void setUp() {
        ClassificationGroup classGroup = new ClassificationGroup();
        classGroup.setDewey(new Classification("004.32", ClassType.DeweyDecimal));
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        ZonedDateTime zdt2 = ZonedDateTime.parse("2019-12-15T12:48:00-05:00[America/New_York]");
        PublishData publish = new PublishData("A Publisher", zdt2, "Denver, CO");
        LocalDate checkDate = LocalDate.of(2020, Month.NOVEMBER, 5);
        LocalDate dueDate = LocalDate.of(2020, Month.NOVEMBER, 26);
        Period period = Period.between(checkDate, dueDate);

        ebook = new Ebook(classGroup, zdt, "Description.", new BigDecimal("23.48"), "Some Title", publish, "A Genre",
        "The summary.", ItemStatus.Available, period, "John Doe, David Smith", "9283923231865");
    }

    @Test
    public void getAuthors_Test() {
        assertEquals("John Doe, David Smith", ebook.getAuthors());
        assertEquals("David Smith", ebook.getAuthorList().get(1));
    }

    @Test
    public void getISBN_Test() {
        assertEquals("9283923231865", ebook.getISBN());
    }

}

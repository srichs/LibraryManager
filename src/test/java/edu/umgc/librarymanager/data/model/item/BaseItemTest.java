/*
 * Filename: BaseItemTest.java
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
 * A Test Class to test the BaseItem class.
 * @author Scott
 */
public class BaseItemTest {

    private Book book;

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
        book = new Book(classGroup, zdt, "Description.", new BigDecimal("23.48"), "Some Title", publish, "A Genre",
                "The summary.", ItemStatus.Available, period, "John Doe", "9382658374958");
    }

    @Test
    public void getClassificationGroup_Test() {
        assertEquals("004.32", book.getClassificationGroup().getDewey().getCode());
    }

    @Test
    public void getPurchaseDate_Test() {
        assertEquals("2019-12-05T10:48-05:00[America/New_York]", book.getPurchaseDate().toString());
    }

    @Test
    public void getDescription_Test() {
        assertEquals("Description.", book.getDescription());
    }

    @Test
    public void getPurchasePrice_Test() {
        assertEquals("23.48", book.getPurchasePrice().toString());
    }

    @Test
    public void getTitle_Test() {
        assertEquals("Some Title", book.getTitle());
    }

    @Test
    public void getPublisher_Test() {
        assertEquals("A Publisher", book.getPublisher().getPublisher());
    }

    @Test
    public void getGenre_Test() {
        assertEquals("A Genre", book.getGenre());
    }

    @Test
    public void getSummary_Test() {
        assertEquals("The summary.", book.getSummary());
    }

    @Test
    public void getPublishDate_Test() {
        assertEquals("2019-12-15T12:48-05:00[America/New_York]", book.getPublishDate().toString());
    }

    @Test
    public void getStatus_Test() {
        assertEquals(ItemStatus.Available, book.getStatus());
    }

    @Test
    public void getCheckoutPeriod_Test() {
        assertEquals(21, book.getCheckoutPeriod().getDays());
    }

    @Test
    public void getItemType_Test() {
        assertEquals(ItemType.Book, book.getItemType());
        Ebook ebook = new Ebook();
        Movie movie = new Movie();
        VideoGame game = new VideoGame();
        assertEquals(ItemType.EBook, ebook.getItemType());
        assertEquals(ItemType.Movie, movie.getItemType());
        assertEquals(ItemType.VideoGame, game.getItemType());
    }

}

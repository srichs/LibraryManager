/*
 * Filename: ItemDAOTest.java
 * Author: Scott
 * Date Created: 11/22/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.Author;
import edu.umgc.librarymanager.data.model.item.BaseBook;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.Book;
import edu.umgc.librarymanager.data.model.item.Classification;
import edu.umgc.librarymanager.data.model.item.ClassificationGroup;
import edu.umgc.librarymanager.data.model.item.ClassificationType;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.item.PublishData;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class for the ItemDAO class.
 * @author Scott
 */
public class ItemDAOTest {

    private ItemDAO itemDAO;
    private BaseItem item;

    @Before
    public void setUp() {
        ClassificationGroup classGroup = new ClassificationGroup();
        classGroup.setDewey(new Classification("004.32", ClassificationType.DeweyDecimal));
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        ZonedDateTime zdt2 = ZonedDateTime.parse("2019-12-15T12:48:00-05:00[America/New_York]");
        PublishData publish = new PublishData("A Publisher", zdt2, "Denver, CO");
        LocalDate checkDate = LocalDate.of(2020, Month.NOVEMBER, 5);
        LocalDate dueDate = LocalDate.of(2020, Month.NOVEMBER, 26);
        Period period = Period.between(checkDate, dueDate);
        List<Author> authors = new ArrayList<Author>();
        authors.add(new Author("John Doe"));
        authors.add(new Author("David Smith"));
        BaseItem item = new Book(classGroup, zdt, "Description.", new BigDecimal("23.48"), "Some Title",
                publish, "A Genre", "The summary.", ItemStatus.Available, period, authors, "9283923231865");
        itemDAO = new ItemDAO();
        itemDAO.openSessionwithTransaction();
        itemDAO.persist(item);
        //item = itemDAO.findAll().get(0);
        itemDAO.closeSessionwithTransaction();
    }

    @After
    public void tearDown() {
        itemDAO.getTransaction().begin();
        itemDAO.deleteAll();
        itemDAO.closeSessionwithTransaction();
    }

    @Test
    public void findAll_Test() {
        assertEquals(1, itemDAO.findAll().size());
    }

    @Test
    public void findByTitle_Test() {
        itemDAO.getTransaction().begin();
        assertEquals(item, itemDAO.findByTitle("Some Title"));
        itemDAO.commit();
    }

    @Test
    public void item_Test() {
        BaseBook book = (BaseBook) itemDAO.findAll().get(0);
        assertEquals("9283923231865", book.getISBN());
        assertEquals("The summary.", book.getSummary());
    }

}

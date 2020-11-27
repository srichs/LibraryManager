/*
 * Filename: BaseTransactionTest.java
 * Author: Scott
 * Date Created: 11/26/2020
 */

package edu.umgc.librarymanager.data.model;

import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.Book;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.Classification;
import edu.umgc.librarymanager.data.model.item.ClassificationGroup;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.item.PublishData;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.PatronUser;
import edu.umgc.librarymanager.data.model.user.UserLogin;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the BaseTransaction class.
 * @author Scott
 */
public class BaseTransactionTest {

    private BaseTransaction transaction;

    @Before
    public void setUp() {
        Library library = new Library("1234 Fake Avenue, Nashville, TN 37011",
                "Nashville Public Library", "615-867-5309");
        ClassificationGroup classGroup = new ClassificationGroup();
        classGroup.setDewey(new Classification("004.32", ClassType.DeweyDecimal));
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        ZonedDateTime zdt2 = ZonedDateTime.parse("2019-12-15T12:48:00-05:00[America/New_York]");
        PublishData publish = new PublishData("A Publisher", zdt2, "Denver, CO");
        LocalDate checkDate = LocalDate.of(2020, Month.NOVEMBER, 26);
        LocalDate dueDate = LocalDate.of(2020, Month.DECEMBER, 10);
        Period period = Period.between(checkDate, dueDate);
        BaseItem item = new Book(classGroup, zdt, "Description.", new BigDecimal("23.48"), "Some Title", publish,
                "A Genre", "The summary.", ItemStatus.Available, period, "John Doe, David Smith", "9283923231865");
        ZonedDateTime zdt3 = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        UserLogin login = new UserLogin("jdoe1", "12345678");
        BaseUser user = new PatronUser(zdt3, "John", "Doe", login, "john.doe@gmail.com",
                "123 Fake St, Los Angeles, CA 90220", "555-867-5309");
        ZonedDateTime transDate = ZonedDateTime.parse("2020-11-26T10:48:00-05:00[America/New_York]");
        ZonedDateTime dueDate2 = ZonedDateTime.parse("2020-12-10T10:48:00-05:00[America/New_York]");
        double fee = 3.57;
        transaction = new BaseTransaction(library, item, user, transDate, dueDate2, fee, null, 0,
                TransactionType.CheckOut);
    }

    @Test
    public void getLibrary_Test() {
        assertEquals("Nashville Public Library", transaction.getLibrary().getName());
    }

}

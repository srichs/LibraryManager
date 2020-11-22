/*
 * Filename: PublishDataTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import org.junit.Before;
import org.junit.Test;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the PublishData class.
 * @author Scott
 */
public class PublishDataTest {

    private PublishData publish;
    private PublishData publish2;

    @Before
    public void setUp() {
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        publish = new PublishData("A Publisher", zdt, "New York, NY");
        publish2 = new PublishData();
    }

    @Test
    public void getPublisher_Test() {
        assertEquals("A Publisher", publish.getPublisher());
    }

    @Test
    public void setPublisher_Test() {
        publish2.setPublisher("Some Publisher");
        assertEquals("Some Publisher", publish2.getPublisher());
    }

    @Test
    public void getPublishDate_Test() {
        assertEquals("2019-12-05T10:48-05:00[America/New_York]", publish.getPublishDate().toString());
    }

    @Test
    public void setPublishDate_Test() {
        ZonedDateTime zdt = ZonedDateTime.parse("2020-05-23T07:23:00-05:00[America/New_York]");
        publish2.setPublishDate(zdt);
        assertEquals("2020-05-23T08:23-04:00[America/New_York]", publish2.getPublishDate().toString());
    }

    @Test
    public void getPublishLocation_Test() {
        assertEquals("New York, NY", publish.getPublishLocation());
    }

    @Test
    public void setPublisherLocation_Test() {
        publish2.setPublishLocation("Los Angeles, CA");
        assertEquals("Los Angeles, CA", publish2.getPublishLocation());
    }

}

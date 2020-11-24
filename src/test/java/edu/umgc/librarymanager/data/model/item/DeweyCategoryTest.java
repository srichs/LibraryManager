/*
 * Filename: DeweyCategoryTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the DeweyCategory class.
 * @author Scott
 */
public class DeweyCategoryTest {

    private DeweyCategory deweyCat;
    private DeweyCategory deweyCat2;

    @Before
    public void setUp() {
        deweyCat = new DeweyCategory("000", "Computer science, information, general works");
        deweyCat2 = new DeweyCategory();
    }

    @Test
    public void getCode_Test() {
        assertEquals("000", deweyCat.getCode());
    }

    @Test
    public void setCode_Test() {
        deweyCat2.setCode("004");
        assertEquals("004", deweyCat2.getCode());
    }

    @Test
    public void getCategory_Test() {
        assertEquals("Computer science, information, general works", deweyCat.getCategory());
    }

    @Test
    public void setCategory_Test() {
        deweyCat2.setCategory("Computer science");
        assertEquals("Computer science", deweyCat2.getCategory());
    }

}

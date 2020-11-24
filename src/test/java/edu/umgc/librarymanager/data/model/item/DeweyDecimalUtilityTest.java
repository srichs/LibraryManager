/*
 * Filename: DeweyCategoryTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the DeweyDecimalUtility class.
 * @author Scott
 */
public class DeweyDecimalUtilityTest {

    @Test
    public void parseCode_Test() {
        String str = DeweyDecimalUtility.parseCode("843.382");
        assertEquals("843", str);
        String str2 = DeweyDecimalUtility.parseCode("032.84");
        assertEquals("032", str2);
    }

    @Test
    public void matchesPattern_Test() {
        boolean bool = DeweyDecimalUtility.matchesPattern("843.382");
        assertEquals(true, bool);
        boolean bool2 = DeweyDecimalUtility.matchesPattern("43.382");
        assertEquals(false, bool2);
        boolean bool3 = DeweyDecimalUtility.matchesPattern("843");
        assertEquals(true, bool3);
        boolean bool4 = DeweyDecimalUtility.matchesPattern("8438.2");
        assertEquals(false, bool4);
    }

}

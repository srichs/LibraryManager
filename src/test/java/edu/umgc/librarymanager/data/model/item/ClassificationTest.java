/*
 * Filename: ClassificationTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the Classification class.
 * @author Scott
 */
public class ClassificationTest {

    private Classification classification;
    private Classification classification2;

    @Before
    public void setUp() {
        classification = new Classification("000", ClassType.DeweyDecimal);
        classification2 = new Classification();
    }

    @Test
    public void getCode_Test() {
        assertEquals("000", classification.getCode());
    }

    @Test
    public void setCode_Test() {
        classification2.setCode("AB");
        assertEquals("AB", classification2.getCode());
    }

    @Test
    public void getClass_Test() {
        assertEquals(ClassType.DeweyDecimal, classification.getClassType());
    }

    @Test
    public void setCategory_Test() {
        classification2.setClassType(ClassType.LibraryOfCongress);
        assertEquals(ClassType.LibraryOfCongress, classification2.getClassType());
    }

}

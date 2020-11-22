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
        classification = new Classification("000", ClassificationType.DeweyDecimal);
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
    public void getClassification_Test() {
        assertEquals(ClassificationType.DeweyDecimal, classification.getClassificationType());
    }

    @Test
    public void setCategory_Test() {
        classification2.setClassificationType(ClassificationType.LibraryOfCongress);
        assertEquals(ClassificationType.LibraryOfCongress, classification2.getClassificationType());
    }

}

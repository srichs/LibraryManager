/*
 * Filename: ClassificationGroupTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class to test the ClassificationGroup class.
 * @author Scott
 */
public class ClassificationGroupTest {

    private ClassificationGroup classGroup;
    private ClassificationGroup classGroup2;

    @Before
    public void setUp() {
        classGroup = new ClassificationGroup();
        classGroup2 = new ClassificationGroup();
    }

    @Test
    public void getDewey_Test() {
        Classification classification = new Classification("328.8", ClassificationType.DEWEY_DECIMAL);
        classGroup.setDewey(classification);
        assertEquals("328.8", classGroup.getDewey().getCode());
    }

    @Test
    public void setDewey_Test() {
        classGroup2.setDewey(new Classification("842.32", ClassificationType.DEWEY_DECIMAL));
        assertEquals("842.32", classGroup2.getDewey().getCode());
        assertEquals(ClassificationType.DEWEY_DECIMAL, classGroup2.getDewey().getClassificationType());
    }

    @Test
    public void getLOC_Test() {
        Classification classification = new Classification("AB", ClassificationType.LIBRARY_OF_CONGRESS);
        classGroup.setLOC(classification);
        assertEquals("AB", classGroup.getLOC().getCode());
    }

    @Test
    public void setLOC_Test() {
        classGroup2.setLOC(new Classification("AB", ClassificationType.LIBRARY_OF_CONGRESS));
        assertEquals(ClassificationType.LIBRARY_OF_CONGRESS, classGroup2.getLOC().getClassificationType());
    }

}

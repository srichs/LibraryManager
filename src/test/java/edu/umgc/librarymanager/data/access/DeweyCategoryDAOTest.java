/*
 * Filename: DeweyCategoryDAOTest.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;

import static org.junit.Assert.assertEquals;

/**
 * A Test Class for the DeweyCategoryDAO class.
 * @author Scott
 */
public class DeweyCategoryDAOTest {

    private DeweyCategoryDAO deweyDAO;
    private DeweyCategory dewey;

    @Before
    public void setUp() {
        dewey = new DeweyCategory("004", "Computer science");
        deweyDAO = new DeweyCategoryDAO();
        deweyDAO.openSessionwithTransaction();
        deweyDAO.persist(dewey);
        dewey = deweyDAO.findByCode("004");
    }

    @After
    public void tearDown() {
        deweyDAO.closeSessionwithTransaction();
    }

    @Test
    public void findByCode_Test() {
        assertEquals(dewey, deweyDAO.findByCode("004"));
    }

    @Test
    public void update_Test() {
        dewey.setCode("032");
        dewey.setCategory("General encyclopedic works in English");
        deweyDAO.update(dewey);
        assertEquals(dewey, deweyDAO.findByCode("004"));
    }

    @Test
    public void deleteAll_Test() {
        deweyDAO.deleteAll();
        assertEquals(null, deweyDAO.findByCode("004"));
    }

}

/*
 * Filename: App.java
 * Author: Scott
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.access.HibernateUtility;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import java.util.List;

/**
 * The main class of the application.
 * @author Scott
 */
public final class App {

    private App() {}

    /**
     * This is the main method for the Application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\nLibraryManager\n");
        databaseTest();
    }

    /**
     * Practice method.
     */
    public static void databaseTest() {
        DeweyCategory dewey = new DeweyCategory("004", "Computer science");
        DeweyCategoryDAO deweyDAO = new DeweyCategoryDAO();
        deweyDAO.openSessionwithTransaction();
        deweyDAO.persist(dewey);

        DeweyCategory dewey2 = deweyDAO.findByCode("004");
        System.out.println("\n" + dewey2.getCategory() + "\n");

        List<DeweyCategory> categories = deweyDAO.findAll();
        System.out.println("\n" + categories.get(0).getCode() + " - " + categories.get(0).getCategory() + "\n");

        deweyDAO.closeSessionwithTransaction();
        HibernateUtility.shutdown();
    }

}

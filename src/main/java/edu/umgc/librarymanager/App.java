/*
 * Filename: App.java
 * Author: Scott
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager;

import java.util.List;
import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.access.HibernateInit;
import edu.umgc.librarymanager.data.access.HibernateUtility;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.user.BaseUser;

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
     * Loads the database with Dewey Decimal categories and User information.
     */
    public static void databaseTest() {
        HibernateInit.initHibernate();

        DeweyCategoryDAO deweyDAO = new DeweyCategoryDAO();
        DeweyCategory dewey = deweyDAO.findByCode("452");
        List<DeweyCategory> categories = deweyDAO.findAll();
        System.out.println("\n" + dewey.getCategory() + "\n");
        for (int i = 0; i < 31; i++) {
            System.out.println(categories.get(i).getCategory());
        }
        System.out.println();

        UserDAO userDAO = new UserDAO();
        List<BaseUser> users = userDAO.findAll();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getLogin().getUsername() + " - " + users.get(i).getLogin().getPassword());
        }
        System.out.println();

        HibernateUtility.shutdown();
    }

}

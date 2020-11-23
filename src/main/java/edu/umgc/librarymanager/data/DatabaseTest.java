/*
 * Filename: DatabaseTest.java
 * Author: Scott
 * Date Created: 11/23/2020
 */

package edu.umgc.librarymanager.data;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.access.ItemDAO;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.item.BaseBook;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.util.List;

/**
 * Used to show some basic functionality of the database.
 * @author Scott
 */
public final class DatabaseTest {

    private DatabaseTest() {}

    /**
     * Provides and example of retrieving information from the database.
     */
    public static void runTest() {
        // HibernateInit.initHibernate();

        // Retrieves the categories of Dewey Decimal then prints Codes 000 - 030
        DeweyCategoryDAO deweyDAO = new DeweyCategoryDAO();
        deweyDAO.openSessionwithTransaction();
        DeweyCategory dewey = deweyDAO.findByCode("452");
        List<DeweyCategory> categories = deweyDAO.findAll();
        deweyDAO.closeSessionwithTransaction();
        System.out.println("\n" + dewey.getCategory() + "\n");
        for (int i = 0; i < 31; i++) {
            System.out.println(categories.get(i).getCode() + " - " + categories.get(i).getCategory());
        }
        System.out.println();

        // Retrieves the User Information then prints the usernames and encrypted passwords
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        List<BaseUser> users = userDAO.findAll();
        userDAO.closeSessionwithTransaction();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getLogin().getUsername() + " - " + users.get(i).getLogin().getPassword());
        }
        System.out.println();

        // Retrieves the Books in the database then prints the titles of index 0 - 30. Then it prints the dewey decimal 
        // code of index 23.
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.openSessionwithTransaction();
        List<BaseItem> items = itemDAO.findAll();
        itemDAO.closeSessionwithTransaction();
        for (int i = 0; i < 31; i++) {
            System.out.println(items.get(i).getTitle());
        }
        System.out.println();
        System.out.println(((BaseBook) items.get(23)).getClassificationCode().get(ClassType.DeweyDecimal).getCode());
        System.out.println();

        // HibernateUtility.shutdown();
    }

}

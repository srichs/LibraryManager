/*
 * Filename: DatabaseTest.java
 * Author: Scott
 * Date Created: 11/23/2020
 */

package edu.umgc.librarymanager.data;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.access.HibernateUtility;
import edu.umgc.librarymanager.data.access.ItemDAO;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.model.item.BaseBook;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

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
        System.out.println(DatabaseTest.getHeader());
        System.out.println(createHeader("Database Test"));
        // HibernateInit.initHibernate();

        // Retrieves the categories of Dewey Decimal then prints Codes 000 - 030
        DeweyCategoryDAO deweyDAO = new DeweyCategoryDAO();
        deweyDAO.openSessionwithTransaction();
        DeweyCategory dewey = deweyDAO.findByCode("452");
        List<DeweyCategory> categories = deweyDAO.findAll();
        deweyDAO.closeSessionwithTransaction();
        System.out.println(createHeader("Dewey Categories"));
        System.out.println("452 - " + dewey.getCategory() + "\n");
        for (int i = 0; i < 31; i++) {
            System.out.println(categories.get(i).getCode() + " - " + categories.get(i).getCategory());
        }

        // Retrieves the User Information then prints the usernames and encrypted passwords
        System.out.println(createHeader("User Information  -  Username and Encrypted Password"));
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        //BaseUser aUser = userDAO.searchByUsername("srichards1");
        List<BaseUser> users = userDAO.findAll();
        userDAO.closeSessionwithTransaction();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getLogin().getUsername() + " - " + users.get(i).getLogin().getPassword());
        }
        //System.out.println(aUser.getEmail());

        // Retrieves the Books in the database then prints the titles of index 0 - 30. Then it prints the dewey decimal
        // code of index 23.
        System.out.println(createHeader("Book Items  -  Titles"));
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

        HibernateUtility.shutdown();
        System.out.println(createHeader("Database Test Complete"));
    }

    /**
     * Performs a search using hibernate search.
     */
    @SuppressWarnings("unchecked")
    public static void search() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        session.getTransaction().begin();

        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(BaseUser.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("login.username")
                .matching("s*")
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery query = fullTextSession.createFullTextQuery(luceneQuery, BaseUser.class);
        List<BaseUser> result = query.getResultList();

        if (result.size() == 0) {
            System.out.println("Not found.");
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).getUserName() + " - " + result.get(i).getFirstName() + " " + result.get(i).getLastName());
            }
        }

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Gets a String header that says "Library Manager".
     * @return A String with the header.
     */
    public static String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------------------------------------------------------------------------------------------\n");
        sb.append("       __    _ __                             __  ___\n");
        sb.append("      / /   (_) /_  _________  _______  __   /  |/  /___  ____  ____  ____  ___  _____\n");
        sb.append("     / /   / / __ \\/ ___/ __ `/ ___/ / / /  / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\/ ___/\n");
        sb.append("    / /___/ / /_/ / /  / /_/ / /  / /_/ /  / /  / / /_/ / / / / /_/ / /_/ /  __/ /\n");
        sb.append("   /_____/_/_.___/_/   \\__,_/_/   \\__, /  /_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/_/\n");
        sb.append("                                 /____/                            /____/\n");
        sb.append("\n---------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }

    /**
     * Creates a header to be used in console.
     * @param title The title to include in the header.
     * @return The complete String header.
     */
    public static String createHeader(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------------------------------------------------------------------------------------------\n");
        sb.append("   " + title + " ");
        sb.append("\n---------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }

}

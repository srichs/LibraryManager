/*
 * Filename: DatabaseTest.java
 * Author: Scott
 * Date Created: 11/23/2020
 */

package edu.umgc.librarymanager.data;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAO;
import edu.umgc.librarymanager.data.access.HibernateUtility;
import edu.umgc.librarymanager.data.access.ItemDAO;
import edu.umgc.librarymanager.data.access.ItemField;
import edu.umgc.librarymanager.data.access.Pagination;
import edu.umgc.librarymanager.data.access.SearchData;
import edu.umgc.librarymanager.data.access.TransactionDAO;
import edu.umgc.librarymanager.data.access.UserDAO;
import edu.umgc.librarymanager.data.access.UserField;
import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.Library;
import edu.umgc.librarymanager.data.model.item.BaseBook;
import edu.umgc.librarymanager.data.model.item.BaseItem;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
                .onFields(UserField.Username.toString(), UserField.FirstName.toString(),
                        UserField.LastName.toString(), UserField.Email.toString())
                .matching("reyn")
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery query = fullTextSession
                .createFullTextQuery(luceneQuery, BaseUser.class);
        List<BaseUser> result = query.getResultList();

        if (result.size() == 0) {
            System.out.println("Not found.");
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).getFirstName() + " " + result.get(i).getLastName()
                        + " - " + result.get(i).getId());
            }
        }

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Used to provide an example and to test the SearchData class.
     */
    public void searchTest() {
        String[] fields = {UserField.Username.toString(), UserField.FirstName.toString(),
            UserField.LastName.toString(), UserField.Email.toString()};
        SearchData<BaseUser> searchUsers = new SearchData<BaseUser>(fields, "Rich", null, BaseUser.class);
        searchUsers.runSearch();
        searchUsers.printResult();

        String[] fields2 = {ItemField.Title.toString(), ItemField.Description.toString(),
                ItemField.DeweyCode.toString(), ItemField.Summary.toString()};
        SearchData<BaseItem> searchItems = new SearchData<BaseItem>(fields2, "Harr", null, BaseItem.class);
        searchItems.runSearch();
        searchItems.printResult();

        Pagination paging = new Pagination(20, 0, 3);
        SearchData<BaseItem> searchItems2 = new SearchData<BaseItem>(null, null, paging, BaseItem.class);
        searchItems2.runSearch();
        searchItems2.printResult();
    }

    /**
     * The method.
     * @return A List.
     */
    public static List<BaseItem> queryCheckedOut() {
        List<BaseItem> results = null;
        ItemDAO itemDAO = new ItemDAO();
        try {
            itemDAO.openSessionwithTransaction();
            results = itemDAO.findCheckedOut();
            itemDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            itemDAO.closeSession();
        }
        return results;
    }

    public static void testCO() {
        List<BaseItem> list = queryCheckedOut();
        printResult(list);
    }

    /**
     * Performs a query of all of the transactions.
     * @return A List of all transactions.
     */
    public static List<BaseTransaction> queryAll() {
        List<BaseTransaction> results = null;
        TransactionDAO transDAO = new TransactionDAO();
        try {
            transDAO.openSessionwithTransaction();
            results = transDAO.findAll();
            transDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            transDAO.closeSession();
        }
        return results;
    }

    public static void testBT() {
        List<BaseTransaction> list = queryAll();
        printResult(list);
    }

    /**
     * Performs a query of the transactions of checkedout items of specific users.
     * @return The List of the transactions matching the query.
     */
    public static List<BaseTransaction> queryCheckedOutByUser() {
        List<BaseTransaction> results = null;
        BaseUser user = null;
        TransactionDAO transDAO = new TransactionDAO();
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.openSessionwithTransaction();
            user = userDAO.findById(6);
            userDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            userDAO.closeSession();
        }
        try {
            transDAO.openSessionwithTransaction();
            results = transDAO.findCheckedOutByUser(user);
            transDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            transDAO.closeSession();
        }
        return results;
    }

    public static void testCOBU() {
        List<BaseTransaction> list = queryCheckedOutByUser();
        printResult(list);
    }

    /*public static List<BaseItem> queryPastDue() {
        List<BaseItem> results = null;
        TransactionDAO transDAO = new TransactionDAO();
        try {
            transDAO.openSessionwithTransaction();
            results = transDAO.findPastDue();
            transDAO.closeSessionwithTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            transDAO.closeSession();
        }
        return results;
    }

    public static void testPD() {
        List<BaseItem> list = queryPastDue();
        printResult(list);
    }*/

    /**
     * A method.
     * @param <T> The type.
     * @param list A list.
     */
    public static <T> void printResult(List<T> list) {
        if (list == null) {
            System.out.println("Not found.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\n" + list.get(i).toString());
            }
            System.out.println();
        }
    }

    /**
     * Get the Library from the database.
     * @return The Library object contained in the database.
     */
    @SuppressWarnings("unchecked")
    public static Library getLibrary() {
        List<Library> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query<Library> query = session.createQuery("From Library");
            list = query.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        if (list != null) {
            return list.get(0);
        }
        return null;
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

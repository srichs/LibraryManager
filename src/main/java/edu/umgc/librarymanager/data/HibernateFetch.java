/*
 * Filename: HibernateFetch.java
 * Author: srichs
 * Date Created: 11/1/2020
 * Purpose: This class is used to fetch data from the Hibernate database.
 */

package edu.umgc.librarymanager.data;

import edu.umgc.librarymanager.item.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 * Fetches data from the Hibernate database.
 */
public final class HibernateFetch {

    private HibernateFetch() {}

    /**
     * Gets the book list from the hibernate database.
     * @return List of books.
     */
    public static List<Book> getBookList() {
        List<Book> books = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            books = session.createQuery("from Book", Book.class).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Gets the book list from the hibernate database.
     * @return List of books.
     */
    public static Map<String, String> getDeweyMap() {
        List<DeweyCategory> dCats = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            dCats = session.createQuery("from DeweyCategory", DeweyCategory.class).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> hMap = new HashMap<String, String>();
        for (DeweyCategory dc: dCats) {
            hMap.put(dc.getCode(), dc.getCategory());
        }
        return hMap;
    }

    /**
     * Gets the user map from the hibernate database.
     * @return Map of Users.
     */
    public static Map<String, BaseUser> getUserMap() {
        List<BaseUser> users = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            users = session.createQuery("from BaseUser", BaseUser.class).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, BaseUser> hMap = new HashMap<String, BaseUser>();
        for (BaseUser user: users) {
            hMap.put(user.getLogin().getUsername(), user);
        }
        return hMap;
    }

    /**
     * Gets the UserLogins from the hibernate database.
     * @return List of UserLogins.
     */
    public static List<UserLogin> getLogins() {
        List<UserLogin> logins = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            logins = session.createQuery("from UserLogin", UserLogin.class).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logins;
    }

}

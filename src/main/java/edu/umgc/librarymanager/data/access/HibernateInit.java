/*
 * Filename: HibernateInit.java
 * Author: srichs
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import com.opencsv.CSVReader;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.LibrarianUser;
import edu.umgc.librarymanager.data.model.user.PatronUser;
import edu.umgc.librarymanager.data.model.user.UserException;
import edu.umgc.librarymanager.data.model.user.UserLogin;
import java.io.FileReader;
import java.time.ZonedDateTime;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 * Initializes the data in the Hibernate database.
 * @author Scott
 */
public class HibernateInit {

    /**
     * This method is used to call other methods that initialize the database from .csv data files.
     */
    public static void initHibernate() {
        initDeweyCategoryList();
        initUserList();
        //initHibernateBookList();
        //buildSearchIndex();
    }

    /**
     * This method is used to build the search index to user hiberante search features.
     */
    public static void buildSearchIndex() {
        new Runnable() {
            public void run() {
                Session session = HibernateUtility.getSessionFactory().openSession();
                FullTextSession fullTextSession = Search.getFullTextSession(session);
                try {
                    fullTextSession.createIndexer().startAndWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Initializes the hibernate database with book data.
     */
    /*public static void initHibernateBookList() {
        String file = "./src/main/resources/data/books.csv";
        CSVReader reader = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            reader.readNext();
            transaction = session.beginTransaction();
            while ((line = reader.readNext()) != null) {
                Book book = new Book(line[1], line[2], Integer.valueOf(line[3]), line[4],
                        new DeweyDecimal(line[5]), new CheckedOut());
                session.save(book);
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Initializes the hibernate database with book data.
     */
    public static void initDeweyCategoryList() {
        String file = "./src/main/resources/data/dewey.csv";
        CSVReader reader = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            transaction = session.beginTransaction();
            while ((line = reader.readNext()) != null) {
                DeweyCategory cat = new DeweyCategory(line[0], line[1]);
                session.save(cat);
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the hibernate database with user data.
     */
    public static void initUserList() {
        String file = "./src/main/resources/data/users.csv";
        CSVReader reader = null;
        UserDAO uDAO = new UserDAO();
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            uDAO.openSessionwithTransaction();
            while ((line = reader.readNext()) != null) {
                BaseUser user = null;
                if (line[6].equals("Librarian")) {
                    user = new LibrarianUser(ZonedDateTime.parse(line[0]), line[1], line[2], new UserLogin(line[3],
                            line[4]), line[5], line[6], line[7]);
                } else if (line[6].equals("Patron")) {
                    user = new PatronUser(ZonedDateTime.parse(line[0]), line[1], line[2], new UserLogin(line[3],
                            line[4]), line[5], line[6], line[7]);
                } else {
                    UserException ex = new UserException("The user: " + line[3] + " could not be added");
                    throw ex;
                }
                uDAO.persist(user);
            }
            uDAO.closeSessionwithTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves an object to the hibernate database.
     * @param obj The Object to save to the hibernate database.
     */
    public static void saveObjToHibernate(Object obj) {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Saves an Array of Book objects to the hibernate database.
     * @param array An Array of Objects to save to the hibernate database.
     */
    public static void saveArrayToHibernate(Object[] array) {
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (int i = 0; i < array.length; i++) {
                session.save((DeweyCategory) array[i]);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Shuts down the hibernate service.
     */
    public void shutdown() {
        HibernateUtility.shutdown();
    }

}

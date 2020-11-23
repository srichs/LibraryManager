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
public final class HibernateInit {

    private HibernateInit() {}

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
                String dtg = line[0].trim();
                if ((int) dtg.charAt(0) == 65279) {
                    dtg = dtg.substring(1);
                }
                ZonedDateTime zdt = ZonedDateTime.parse(dtg);
                if (line[8].equals("Librarian")) {
                    user = new LibrarianUser(zdt, line[1], line[2], new UserLogin(line[3],
                            line[4]), line[5], line[6], line[7]);
                } else if (line[8].equals("Patron")) {
                    user = new PatronUser(zdt, line[1], line[2], new UserLogin(line[3],
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

}

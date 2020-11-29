/*
 * Filename: HibernateInit.java
 * Author: srichs
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import com.opencsv.CSVReader;
import edu.umgc.librarymanager.data.model.item.Book;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.Classification;
import edu.umgc.librarymanager.data.model.item.ClassificationGroup;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.item.PublishData;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.LibrarianUser;
import edu.umgc.librarymanager.data.model.user.PatronUser;
import edu.umgc.librarymanager.data.model.user.UserException;
import edu.umgc.librarymanager.data.model.user.UserLogin;
import java.io.FileReader;
import java.math.BigDecimal;
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
        buildSearchIndex();
        DeweyCategoryDAO deweyDAO = new DeweyCategoryDAO();
        deweyDAO.openSessionwithTransaction();
        deweyDAO.deleteAll();
        deweyDAO.closeSessionwithTransaction();
        UserDAO userDAO = new UserDAO();
        userDAO.openSessionwithTransaction();
        userDAO.deleteAll();
        userDAO.closeSessionwithTransaction();
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.openSessionwithTransaction();
        itemDAO.deleteAll();
        itemDAO.closeSessionwithTransaction();
        initDeweyCategoryList();
        initUserList();
        initHibernateBookList();
    }

    /**
     * This method is used to build the search index to user hiberante search features.
     */
    public static void buildSearchIndex() {
        new Runnable() {
            public void run() {
                Session session = HibernateUtility.getSessionFactory().openSession();
                session.getTransaction().begin();
                FullTextSession fullTextSession = Search.getFullTextSession(session);
                try {
                    fullTextSession.createIndexer().startAndWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    session.getTransaction().commit();
                    session.close();
                }
            }
        };
    }

    /**
     * Initializes the hibernate database with book data.
     */
    public static void initHibernateBookList() {
        String file = "./src/main/resources/data/books.csv";
        CSVReader reader = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                ClassificationGroup classGroup = new ClassificationGroup();
                classGroup.setDewey(new Classification(line[1], ClassType.DeweyDecimal));
                classGroup.setLOC(new Classification(line[2], ClassType.LibraryOfCongress));
                ZonedDateTime zdt = ZonedDateTime.parse(line[3]);
                PublishData publish = new PublishData("A Publisher", ZonedDateTime.now(), "Denver, CO");
                Book book = new Book(classGroup, zdt, line[4], new BigDecimal(line[5]), line[6], publish, "A Genre",
                        line[8], ItemStatus.Available, null, line[10], line[11]);
                session.save(book);
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            transaction = session.beginTransaction();
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
                session.save(user);
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/*
 * Filename: HibernateInit.java
 * Author: srichs
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.access;

import com.opencsv.CSVReader;
import edu.umgc.librarymanager.data.model.BaseTransaction;
import edu.umgc.librarymanager.data.model.Library;
import edu.umgc.librarymanager.data.model.TransactionType;
import edu.umgc.librarymanager.data.model.item.Book;
import edu.umgc.librarymanager.data.model.item.ClassType;
import edu.umgc.librarymanager.data.model.item.Classification;
import edu.umgc.librarymanager.data.model.item.ClassificationGroup;
import edu.umgc.librarymanager.data.model.item.DeweyCategory;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtility;
import edu.umgc.librarymanager.data.model.item.ItemStatus;
import edu.umgc.librarymanager.data.model.item.PublishData;
import edu.umgc.librarymanager.data.model.user.BaseUser;
import edu.umgc.librarymanager.data.model.user.LibrarianUser;
import edu.umgc.librarymanager.data.model.user.PatronUser;
import edu.umgc.librarymanager.data.model.user.UserException;
import edu.umgc.librarymanager.data.model.user.UserLogin;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
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
     * This method is used to call other methods that initialize the database from
     * .csv data files.
     */
    public static void initHibernate() {
        try {
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
            initTransactionsFromCSV();
        } catch (Error ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks if a database file exists in the resources folder, if it doesn't then the lucene indexes
     * are deleted and the initHibernate method is called to generate a database and indexes.
     */
    public static void databaseCheck() {
        if (!dbFileExists()) {
            deleteIndexDirectory();
            initHibernate();
        }
    }

    private static boolean dbFileExists() {
        return new File("./src/main/resources/", "database.mv.db").exists();
    }

    private static void deleteIndexDirectory() {
        try {
            FileUtils.deleteDirectory(new File("./src/main/resources/lucene/indexes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        HashMap<String, String> deweyMap = null;
        DeweyCategoryDAO deweyCatDAO = new DeweyCategoryDAO();
        deweyCatDAO.openSessionwithTransaction();
        deweyMap = deweyCatDAO.getDDCHashMap();
        deweyCatDAO.closeSessionwithTransaction();
        LocalDate checkDate = LocalDate.of(2020, Month.NOVEMBER, 26);
        LocalDate dueDate = LocalDate.of(2020, Month.DECEMBER, 10);
        Period period = Period.between(checkDate, dueDate);
        CSVReader reader = null;
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            reader.readNext();
            transaction = session.beginTransaction();
            while ((line = reader.readNext()) != null) {
                ClassificationGroup classGroup = new ClassificationGroup();
                classGroup.setDewey(new Classification(line[1], ClassType.DeweyDecimal));
                classGroup.setLOC(new Classification(line[2], ClassType.LibraryOfCongress));
                ZonedDateTime zdt = ZonedDateTime.parse(line[3]);
                String genre = deweyMap.get(DeweyDecimalUtility.parseCode(line[1]));
                PublishData publish = new PublishData("A Publisher", ZonedDateTime.now(), "Denver, CO");
                ItemStatus status = ItemStatus.stringToItemStatus(line[9]);
                Book book = null;
                if (status == ItemStatus.CheckedOut) {
                    book = new Book(classGroup, zdt, line[4], new BigDecimal(line[5]), line[6], publish, genre,
                            line[8], status, period, line[10],
                            line[11]);
                } else {
                    book = new Book(classGroup, zdt, line[4], new BigDecimal(line[5]), line[6], publish, genre,
                            line[8], status, Period.ZERO, line[10],
                            line[11]);
                }
                session.save(book);
            }
            transaction.commit();
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
     * Used to initialize data in the Library table.
     */
    public static void initLibrary() {
        Library library = new Library("1234 Fake Avenue, Nashville, TN 37011",
                "Nashville Public Library", "615-867-5309");
        Transaction transaction = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(library);
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
                String dtg = line[1].trim();
//                if ((int) dtg.charAt(0) == 65279) {
//                    dtg = dtg.substring(1);
//                }
                ZonedDateTime zdt = null;
                int len = dtg.length();
                for (int i = 0; i < len; i++) {
                    try {
                        zdt = ZonedDateTime.parse(dtg);
                        break;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    dtg = dtg.substring(1);
                }
                if (zdt == null) continue;
                if (line[9].equals("Librarian")) {
                    user = new LibrarianUser(zdt, line[2], line[3], new UserLogin(line[4],
                            line[5]), line[6], line[7], line[8]);
                } else if (line[9].equals("Patron")) {
                    user = new PatronUser(zdt, line[2], line[3], new UserLogin(line[4],
                            line[5]), line[6], line[7], line[8]);
                } else {
                    UserException ex = new UserException("The user: " + line[4] + " could not be added");
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

    /**
     * Initializes the database with data for transactions.
     */
    public static void initTransactionList() {
        Transaction transaction = null;
        Library library = new Library("1234 Fake Avenue, Nashville, TN 37011",
                "Nashville Public Library", "615-867-5309");
        ZonedDateTime zdt = ZonedDateTime.parse("2019-12-05T10:48:00-05:00[America/New_York]");
        BaseTransaction bt = null;
        UserDAO userDAO = new UserDAO();
        ItemDAO itemDAO = new ItemDAO();
        Session session = null;
        try {
            for (int i = 1; i < 8; i++) {
                bt = new BaseTransaction();
                userDAO.openSessionwithTransaction();
                bt.setUser(userDAO.findById(i));
                userDAO.closeSessionwithTransaction();
                itemDAO.openSessionwithTransaction();
                bt.setItem(itemDAO.findById(i));
                itemDAO.closeSessionwithTransaction();
                bt.setLibrary(library);
                bt.setFee(new BigDecimal("0.00"));
                if (bt.getItem().getStatus() == ItemStatus.CheckedOut) {
                    bt.setDueDate(zdt.plusDays(14));
                } else {
                    bt.setDueDate(zdt);
                }
                bt.setTransactionDateTime(zdt);
                bt.setRenewCount(0);
                bt.setRenewDate(zdt);
                bt.setTransactionType(TransactionType.CheckOut);
                session = HibernateUtility.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(bt);
                transaction.commit();
                session.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void initTransactionsFromCSV() {
        String file = "./src/main/resources/data/transactions.csv";
        Library library = new Library("1234 Fake Avenue, Nashville, TN 37011",
                "Nashville Public Library", "615-867-5309");
        BaseTransaction bt = null;
        UserDAO userDAO = new UserDAO();
        ItemDAO itemDAO = new ItemDAO();
        CSVReader reader = null;
        Transaction transaction = null;
        Session session = null;
        try {
            reader = new CSVReader(new FileReader(file));
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                bt = new BaseTransaction();
                userDAO.openSessionwithTransaction();
                bt.setUser(userDAO.findById(Integer.valueOf(line[3])));
                userDAO.closeSessionwithTransaction();
                itemDAO.openSessionwithTransaction();
                bt.setItem(itemDAO.findById(Integer.valueOf(line[2])));
                itemDAO.closeSessionwithTransaction();
                bt.setLibrary(library);
                bt.setFee(new BigDecimal(line[6]));
                bt.setTransactionType(TransactionType.stringToTransactionType(line[9]));
                bt.setDueDate(ZonedDateTime.parse(line[5]));
                bt.setTransactionDateTime(ZonedDateTime.parse(line[4]));
                bt.setRenewCount(0);
                bt.setRenewDate(ZonedDateTime.parse(line[7]));
                session = HibernateUtility.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(bt);
                transaction.commit();
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

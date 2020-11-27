/*
 * Filename: App.java
 * Author: Scott
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import edu.umgc.librarymanager.data.DatabaseTest;
import edu.umgc.librarymanager.data.access.HibernateUtility;
import edu.umgc.librarymanager.data.model.item.Book;
import edu.umgc.librarymanager.data.access.HibernateInit;
import edu.umgc.librarymanager.gui.AppGUI;

/**
 * The main class of the application.
 * 
 * @author Scott
 */
public final class App {

    private App() {
    }

    /**
     * This is the main method for the Application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println(DatabaseTest.getHeader());
        search();
        //HibernateInit.buildSearchIndex();
        AppGUI app = new AppGUI();
        app.run();
        //DatabaseTest.runTest();
    }

    @SuppressWarnings("unchecked")
    public static void search() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        session.getTransaction().begin();

        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Book.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields()
                .matching("Hunger")
                .createQuery();

        Query query = fullTextSession.createFullTextQuery(luceneQuery, Book.class);
        List<Book> result = query.getResultList();

        if (result.size() == 0) {
            System.out.println("No Hunger found.");
        } else {
            System.out.println(result.get(0).getTitle());
        }

        session.getTransaction().commit();
        session.close();
    }

}

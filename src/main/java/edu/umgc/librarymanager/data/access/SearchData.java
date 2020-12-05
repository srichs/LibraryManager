/*
 * Filename: Search.java
 * Author: Scott
 * Date Created: 12/4/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * This is a utility class that provides methods to search the database using the hibernate search
 * indexes.
 * @author Scott
 */
public final class SearchData {

    private SearchData() {}

    /**
     * Searches the database and retrieves a List of the matches for the given Type.
     * @param <T> The Type to search for.
     * @param fields An Array of Strings that are the fields to be searched in the given Type.
     * @param searchTerms The search terms that were entered by the user.
     * @param pagination A Pagination object to returned a specific page of results.
     * @param clazz The class that will be queried during the search, will match the Type.
     * @return A List of the matching results of the given Type.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> search(String[] fields, String searchTerms, Pagination pagination, Class<T> clazz) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        List<T> result = null;
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            session.getTransaction().begin();

            QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(clazz).get();
            org.apache.lucene.search.Query luceneQuery = qb
                    .keyword()
                    .onFields(fields)
                    .matching(searchTerms)
                    .createQuery();

            org.hibernate.search.jpa.FullTextQuery query = fullTextSession
                    .createFullTextQuery(luceneQuery, clazz);
            if (pagination != null) {
                query.setFirstResult((pagination.getDesiredPage() - 1) * pagination.getPageSize());
                query.setMaxResults(pagination.getPageSize());
            }
            result = query.getResultList();

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Prints the List to the console.
     * @param <T> The type of the List
     * @param result The List to be printed.
     */
    public static <T> void printResult(List<T> result) {
        if (result.size() == 0) {
            System.out.println("Not found.");
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).toString());
            }
        }
    }

}

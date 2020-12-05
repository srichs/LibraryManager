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
 * This class
 * @author Scott
 */
public final class SearchData {

    private SearchData() {}

    @SuppressWarnings("unchecked")
    public static <T> List<T> search(String[] fields, String searchTerms, Pagination pagination, Class<T> cls) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        List<T> result = null;
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            session.getTransaction().begin();

            QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(cls).get();
            org.apache.lucene.search.Query luceneQuery = qb
                    .keyword()
                    .onFields(fields)
                    .matching(searchTerms)
                    .createQuery();

            org.hibernate.search.jpa.FullTextQuery query = fullTextSession
                    .createFullTextQuery(luceneQuery, cls);
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

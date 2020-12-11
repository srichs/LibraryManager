/*
 * Filename: Search.java
 * Author: Scott
 * Date Created: 12/4/2020
 */

package edu.umgc.librarymanager.data.access;

import edu.umgc.librarymanager.data.model.item.ItemStatus;
import java.util.List;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.exception.EmptyQueryException;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * This is a utility class that provides methods to search the database using the hibernate search
 * indexes.
 * @param <T> The Type of data that will be searched.
 * @author Scott
 */
public final class SearchData<T> {

    private String[] fields;
    private Object terms;
    private Pagination pagination;
    private Class<T> clazz;
    private List<T> results;
    private List<AdvSearchPart> advSearchList;
    private ItemStatus advStatusFilter;

    /**
     * The default constructor of the class.
     */
    public SearchData() {
        this.fields = null;
        this.terms = "";
        this.pagination = new Pagination();
        this.clazz = null;
        this.results = null;
        this.advSearchList = null;
        this.advStatusFilter = null;
    }

    /**
     * The constructor of the class with parameters for each field.
     * @param fields The fields to be searched.
     * @param terms The terms of the search that are retrieved from the search bar.
     * @param pagination The pagination information to fetch the results for the specified page.
     * @param clazz The Class of the Type of data that will be searched.
     */
    public SearchData(String[] fields, Object terms, Pagination pagination, Class<T> clazz) {
        this.fields = fields;
        this.terms = terms;
        this.pagination = pagination;
        this.clazz = clazz;
        this.results = null;
        this.advSearchList = null;
        this.advStatusFilter = null;
    }

    /**
     * The constructor of the class for when an Advanced Search will be conducted.
     * @param advSearchList The List of AdvSearchParts to build the advance search query from.
     * @param pagination The pagination information to fetch the results for the specified page.
     * @param clazz The Class of the Type of data that will be searched.
     * @param advStatusFilter The ItemStatus to filter the results by.
     */
    public SearchData(List<AdvSearchPart> advSearchList, Pagination pagination, Class<T> clazz, ItemStatus advStatusFilter) {
        this.advSearchList = advSearchList;
        this.advStatusFilter = advStatusFilter;
        this.pagination = pagination;
        this.clazz = clazz;
        this.results = null;
    }

    public String[] getFields() {
        return this.fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public Object getTerms() {
        return this.terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getResults() {
        return this.results;
    }

    public List<AdvSearchPart> getAdvSearchList() {
        return this.advSearchList;
    }

    public void setAdvSearchList(List<AdvSearchPart> advSearchList) {
        this.advSearchList = advSearchList;
    }

    public ItemStatus getAdvStatusFilter() {
        return this.advStatusFilter;
    }

    public void setAdvStatusFilter(ItemStatus advStatusFilter) {
        this.advStatusFilter = advStatusFilter;
    }

    /**
     * Runs the search with the given SearchData parameters. If the fields information is null then it will find
     * all of the records for the given information. If the pagination information is null then it will display
     * all results in one list.
     */
    @SuppressWarnings("unchecked")
    public void runSearch() throws EmptyQueryException {
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            session.getTransaction().begin();

            if (this.advSearchList != null) { // Checks if advSearchList is not null and then does advanced search.
                QueryBuilder qb = fullTextSession.getSearchFactory()
                        .buildQueryBuilder().forEntity(this.clazz).get();
                org.apache.lucene.search.Query luceneQuery = buildAdvancedSearchQuery(qb);

                FullTextQuery query = fullTextSession
                        .createFullTextQuery(luceneQuery, this.clazz);
                if (this.pagination != null) {
                    this.pagination.setTotalCount(query.getResultSize());
                    query.setFirstResult((this.pagination.getDesiredPage() - 1) * this.pagination.getPageSize());
                    query.setMaxResults(this.pagination.getPageSize());
                }
                this.results = query.getResultList();
            } else if (this.fields == null) { // if searchData is null then findAll records of the given Type
                Query<Long> countQuery = null;
                Query<T> selectQuery = null;
                if (this.clazz.getSimpleName().equals("BaseItem")) {
                    countQuery = session.createQuery("Select Count (i.id) From BaseItem i");
                    selectQuery = session.createQuery("From BaseItem");
                } else if (this.clazz.getSimpleName().equals("BaseUser")) {
                    countQuery = session.createQuery("Select Count (u.id) From BaseUser u");
                    selectQuery = session.createQuery("From BaseUser");
                } else {
                    return;
                }
                if (this.pagination != null) {
                    this.pagination.setTotalCount((Long) countQuery.uniqueResult());
                    selectQuery.setFirstResult((this.pagination.getDesiredPage() - 1) * this.pagination.getPageSize());
                    selectQuery.setMaxResults(this.pagination.getPageSize());
                }
                this.results = selectQuery.getResultList();
            } else {
                QueryBuilder qb = fullTextSession.getSearchFactory()
                        .buildQueryBuilder().forEntity(this.clazz).get();
                org.apache.lucene.search.Query luceneQuery = qb
                        .keyword()
                        .onFields(this.fields)
                        .matching(this.terms)
                        .createQuery();

                FullTextQuery query = fullTextSession
                        .createFullTextQuery(luceneQuery, this.clazz);
                if (this.pagination != null) {
                    this.pagination.setTotalCount(query.getResultSize());
                    query.setFirstResult((this.pagination.getDesiredPage() - 1) * this.pagination.getPageSize());
                    query.setMaxResults(this.pagination.getPageSize());
                }
                this.results = query.getResultList();
            }

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Used to build an Advanced Search query, which uses a BooleanQuery to form a complex search from user input.
     * @param qb The QueryBuilder Object that will be used to build the query.
     * @return The Query that can be used to get the results of the advanced search parameters.
     */
    public org.apache.lucene.search.Query buildAdvancedSearchQuery(QueryBuilder qb) {
        BooleanQuery.Builder bqb = new BooleanQuery.Builder();
        for (int i = 0; i < advSearchList.size(); i++) {
            AdvSearchPart asp = advSearchList.get(i);
            org.apache.lucene.search.Query q = qb.keyword().onField(asp.getField().toString())
                    .matching(asp.getSearchTerm()).createQuery();
            if (asp.getLogicOperator() == LogicalType.And) {
                bqb.add(q, BooleanClause.Occur.MUST);
            } else if (asp.getLogicOperator() == LogicalType.Or) {
                bqb.add(q, BooleanClause.Occur.SHOULD);
            } else if (asp.getLogicOperator() == LogicalType.Not) {
                bqb.add(q, BooleanClause.Occur.MUST_NOT);
            }
        }
        if (this.advStatusFilter != null) {
            bqb.add(qb.keyword().onField(ItemField.Status.toString()).matching(this.advStatusFilter).createQuery(),
                    BooleanClause.Occur.MUST);
        }
        return bqb.build();
    }

    /**
     * Prints the results list to the console.
     */
    public void printResult() {
        if (this.results == null) {
            System.out.println("Not found.");
        } else {
            for (int i = 0; i < this.results.size(); i++) {
                System.out.println("\n" + this.results.get(i).toString());
            }
            System.out.println();
        }
    }

}

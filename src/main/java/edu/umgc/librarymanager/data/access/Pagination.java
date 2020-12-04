/*
 * Filename: Pagination.java
 * Author: Scott
 * Date Created: 12/3/2020
 */

package edu.umgc.librarymanager.data.access;

/**
 * This class is used to track information about the pagination.
 * @author Scott
 */
public class Pagination {

    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageSize;
    private long totalCount;
    private int desiredPage;
    
    public Pagination() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.totalCount = 0;
        this.desiredPage = 1;
    }

    public Pagination(int pageSize, int totalCount, int desiredPage) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.desiredPage = desiredPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getDesiredPage() {
        return this.desiredPage;
    }

    public void setDesiredPage(int desiredPage) {
        this.desiredPage = desiredPage;
    }

    public int getLastPageNumber() {
        return (int) (Math.ceil(this.totalCount / this.pageSize));
    }

    public void nextPage() {
        this.desiredPage++;
    }

    public void previousPage() {
        this.desiredPage--;
    }

    public static Pagination getDefault() {
        Pagination p = new Pagination(20, 0, 1);
        return p;
    }
    
}

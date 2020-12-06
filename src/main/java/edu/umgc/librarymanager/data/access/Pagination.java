/*
 * Filename: Pagination.java
 * Author: Scott
 * Date Created: 12/3/2020
 */

package edu.umgc.librarymanager.data.access;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class is used to track information about the pagination.
 * @author Scott
 */
public class Pagination {

    /**
     * The default page size of a Pagination Object.
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageSize;
    private long totalCount;
    private int desiredPage;

    /**
     * The default constructor of the class.
     */
    public Pagination() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.totalCount = 0;
        this.desiredPage = 1;
    }

    /**
     * The constructor of the class with parameters for each field.
     * @param pageSize The number of elements to be displayed on a page.
     * @param totalCount The total number of elements.
     * @param desiredPage The desired page to display.
     */
    public Pagination(int pageSize, long totalCount, int desiredPage) {
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
        return new BigDecimal(this.totalCount).divide(new BigDecimal(this.pageSize),
                RoundingMode.CEILING).toBigInteger().intValue();
    }

    public void nextPage() {
        this.desiredPage++;
    }

    /**
     * Decrements the desired page value if it is greater than 1.
     */
    public void previousPage() {
        if (this.desiredPage > 1) {
            this.desiredPage--;
        }
    }

    public static Pagination getDefault() {
        Pagination p = new Pagination(20, 0, 1);
        return p;
    }

}

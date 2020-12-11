/*
 * Filename: AdvSearchPart.java
 * Author: Scott
 * Date Created: 12/10/2020
 */

package edu.umgc.librarymanager.data.access;

/**
 * This class is used to model part of an advanced search.
 * @author Scott
 */
public class AdvSearchPart {
    
    private LogicalType logicOperator;
    private ItemField field;
    private String searchTerm;

    public AdvSearchPart() {
        this.logicOperator = null;
        this.field = null;
        this.searchTerm = "";
    }

    public LogicalType getLogicOperator() {
        return this.logicOperator;
    }

    public void setLogicOperator(LogicalType logicOperator) {
        this.logicOperator = logicOperator;
    }

    public ItemField getField() {
        return this.field;
    }

    public void setField(ItemField field) {
        this.field = field;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}

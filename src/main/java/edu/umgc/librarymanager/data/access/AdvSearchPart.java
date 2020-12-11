/*
 * Filename: AdvSearchPart.java
 * Author: Scott
 * Date Created: 12/10/2020
 */

package edu.umgc.librarymanager.data.access;

import java.util.List;

/**
 * This class is used to model part of an advanced search.
 * @author Scott
 */
public class AdvSearchPart {
    
    private LogicalType logicOperator;
    private ItemField field;
    private String searchTerm;

    /**
     * The default constructor of the class.
     */
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

    public String toString() {
        return this.logicOperator.toString() + " " + this.field.label + " " + this.searchTerm;
    }

    /**
     * Creates a string of the AdvSearchParts contained in a List.
     * @param list The List of AdvSearchParts.
     * @return A String value of all of the AdvSearchParts.
     */
    public static String listToString(List<AdvSearchPart> list) {
        String str = "";
        for (AdvSearchPart asp : list) {
            str += asp.toString() + "\n";
        }
        return str;
    }

}

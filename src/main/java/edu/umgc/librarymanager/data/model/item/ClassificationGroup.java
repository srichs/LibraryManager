/*
 * Filename: ClassificationGroup.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

/**
 * A Composite Class that stores information for Dewey Decimal and
 * Library of Congress Classifications.
 * @author Scott
 */
public class ClassificationGroup {

    private long id;
    private Classification dewey;
    private Classification loc;

    public ClassificationGroup() {
        this.id = -1;
        this.dewey = null;
        this.loc = null;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Classification getDewey() {
        return this.dewey;
    }

    public void setDewey(Classification dewey) {
        this.dewey = dewey;
    }

    public Classification getLOC() {
        return this.loc;
    }

    public void setLOC(Classification loc) {
        this.loc = loc;
    }

}

/*
 * Filename: ClassificationGroup.java
 * Author: Scott
 * Date Created: 11/21/2020
 */

package edu.umgc.librarymanager.data.model.item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A Composite Class that stores information for Dewey Decimal and
 * Library of Congress Classifications.
 * @author Scott
 */
@Entity
@Table(name = "classification_group")
public class ClassificationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classification_group_id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Classification dewey;

    @OneToOne(cascade = CascadeType.ALL)
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

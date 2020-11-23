/*
 * Filename: Classification.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An Interface to implement the Classification System for an Item.
 * @author David
 */
@Entity
@Table(name = "classification")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classification_id")
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "class_type")
    private ClassType classType;

    /**
     * The default constructor of the class.
     */
    public Classification() {
        this.code = "";
        this.classType = null;
    }

    /**
     * A constructor with parameters for each field.
     * @param code The code of the given classification system.
     * @param classType The type of classification system.
     */
    public Classification(String code, ClassType classType) {
        this.code = code;
        this.classType = classType;
    }

    public long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClassType getClassType() {
        return this.classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

}

/*
 * Filename: Library.java
 * Author: David
 * Date Created: 11/17/2020
 */

package edu.umgc.librarymanager.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class that models some basic information for the Library.
 * @author David
 */
@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id")
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    /**
     * The default constructor for the class.
     */
    public Library() {
        this.address = "";
        this.name = "";
        this.phone = "";
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return this.name + "\n" + this.address + "\n" + this.phone;
    }

}

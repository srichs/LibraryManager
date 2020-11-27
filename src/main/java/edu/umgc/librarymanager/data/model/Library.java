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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * A class that models some basic information for the Library.
 * @author David
 */
@Entity
@Indexed
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id")
    private long id;

    @Column(name = "address")
    private String address;

    @Field
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

    /**
     * A constructor with a parameter for each field of the class.
     * @param address The address of the Library.
     * @param name The name of the Library.
     * @param phone The phone number of the Library.
     */
    public Library(String address, String name, String phone) {
        this.address = address;
        this.name = name;
        this.phone = phone;
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

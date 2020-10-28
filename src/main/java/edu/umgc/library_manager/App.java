/*
 * Filename: App.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class contains the main method of the App application.
 */

package edu.umgc.library_manager;

/**
 * The main class of the App application
 */
public class App {

    public static void main(String[] args) {
        System.out.println("LibraryManager");
        Book book = new Book("1984", "George Orwell", "1949", "9781443434973", "823.912", false);
        System.out.println(book.toString());
    }

}

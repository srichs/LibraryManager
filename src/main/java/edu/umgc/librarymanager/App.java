/*
 * Filename: App.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class contains the main method of the App application.
 */

package edu.umgc.librarymanager;

/**
 * The main class of the application.
 */
public final class App {

    /**
     * The default constructor for the class.
     */
    private App() {}

    /**
     * This is the main method for the Application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("LibraryManager\n");
        Book book = new Book("1984", "George Orwell", "1949", "9781443434973", "823.912", false);
        System.out.println(book.toString());
    }

}

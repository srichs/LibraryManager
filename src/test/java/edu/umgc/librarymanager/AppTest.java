/*
 * Filename: AppTest.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class runs the unit tests for the project.
 */

package edu.umgc.librarymanager;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class runs the unit tests for the project.
 */
public final class AppTest {

    /**
     * Default constructor for the class.
     */
    private AppTest() {}

    /**
     * The main method of the Test Runner.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

}

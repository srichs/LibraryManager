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
 * @author Scott
 */
public final class AppTest {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private AppTest() {}

    /**
     * The main method of the Test Runner.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("\n---------------------------\n  T E S T S\n---------------------------\n");
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println(result.getRunCount() + " TESTS RUN - " + ANSI_GREEN + "SUCCESS"
                    + ANSI_RESET + " - " + result.getRunTime() + " ms\n" + ANSI_RESET);
        } else {
            System.out.println("\n" + result.getRunCount() + " TESTS RUN - " + ANSI_RED
                    + result.getFailureCount() + " FAILED" + ANSI_RESET + " - " + result.getRunTime()
                    + " ms\n" + ANSI_RESET);
        }
    }

}

/*
 * Filename: AppTest.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class runs the unit tests for the project.
 */

package edu.umgc.library_manager;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class runs the unit tests for the project.
 */
public class AppTest {
    public static void main(String[] args) {
        System.out.println("Testing...");
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println("Testing Complete");
    }
}
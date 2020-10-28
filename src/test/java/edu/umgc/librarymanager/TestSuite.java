/*
 * Filename: TestSuite.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class defines a test suite to run the unit tests.
 */

package edu.umgc.librarymanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The Suite class to use.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
    BookTests.class
})

/**
 * This class defines a test suite to run the unit tests.
 */
public class TestSuite {

}

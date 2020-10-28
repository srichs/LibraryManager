/*
 * Filename: TestSuite.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class defines a test suite to run the unit tests.
 */

package edu.umgc.library_manager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    BookTests.class
})

/**
 * This class defines a test suite to run the unit tests.
 */
public class TestSuite {  

}
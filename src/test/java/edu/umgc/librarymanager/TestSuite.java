/*
 * Filename: TestSuite.java
 * Author: srichs
 * Date Created: 10/28/2020
 * Purpose: This class defines a test suite to run the unit tests.
 */

package edu.umgc.librarymanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import edu.umgc.librarymanager.data.model.item.BaseItemTest;
import edu.umgc.librarymanager.data.model.item.ClassificationGroupTest;
import edu.umgc.librarymanager.data.model.item.ClassificationTest;
import edu.umgc.librarymanager.data.model.item.DeweyCategoryTest;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtilityTest;
import edu.umgc.librarymanager.data.model.item.PublishDataTest;

/**
 * The Suite class to use.
 */
@RunWith(Suite.class)

/**
 * The Test classes to run.
 */
@Suite.SuiteClasses({
    DeweyCategoryTest.class,
    DeweyDecimalUtilityTest.class,
    ClassificationTest.class,
    ClassificationGroupTest.class,
    PublishDataTest.class,
    BaseItemTest.class
})

/**
 * This class defines a test suite to run the unit tests.
 * @author Scott
 */
public class TestSuite {}

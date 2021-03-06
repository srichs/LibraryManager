/*
 * Filename: TestSuite.java
 * Author: srichs
 * Date Created: 10/28/2020
 */

package edu.umgc.librarymanager;

import edu.umgc.librarymanager.data.access.DeweyCategoryDAOTest;
import edu.umgc.librarymanager.data.access.UserDAOTest;
import edu.umgc.librarymanager.data.access.UserLoginDAOTest;
import edu.umgc.librarymanager.data.model.item.BaseItemTest;
import edu.umgc.librarymanager.data.model.item.BookTest;
import edu.umgc.librarymanager.data.model.item.ClassificationGroupTest;
import edu.umgc.librarymanager.data.model.item.ClassificationTest;
import edu.umgc.librarymanager.data.model.item.DeweyCategoryTest;
import edu.umgc.librarymanager.data.model.item.DeweyDecimalUtilityTest;
import edu.umgc.librarymanager.data.model.item.EBookTest;
import edu.umgc.librarymanager.data.model.item.PublishDataTest;
import edu.umgc.librarymanager.data.model.user.BaseUserTest;
import edu.umgc.librarymanager.data.model.user.UserLoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The Suite class to use.
 */
@RunWith(Suite.class)

/**
 * The Test classes to run.
 */
@Suite.SuiteClasses({
    DeweyCategoryDAOTest.class,
    UserLoginDAOTest.class,
    UserDAOTest.class,
    DeweyCategoryTest.class,
    DeweyDecimalUtilityTest.class,
    ClassificationTest.class,
    ClassificationGroupTest.class,
    PublishDataTest.class,
    BaseItemTest.class,
    BookTest.class,
    EBookTest.class,
    BaseUserTest.class,
    UserLoginTest.class
})

/**
 * This class defines a test suite to run the unit tests. Maven Test
 * is preferred over manually running the AppTest main method. To use
 * Maven install and then use the command mvn test.
 * @author Scott
 */
public class TestSuite {}

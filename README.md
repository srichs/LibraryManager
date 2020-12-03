# LibraryManager

![](https://github.com/srichs/LibraryManager/workflows/build/badge.svg)

A system for managing a library.

The project is built using the Maven Build Tool with JUnit tests integrated. Continuous integration is conducted using GitHub actions and the status of the current build is displayed in the badge above. Hibernate is used with an H2 database to store the information that is used for the project.

## Source Directory Structure

### Packages
* edu.umgc.librarymanager
    * edu.umgc.librarymanager.data
        * edu.umgc.librarymanager.data.access
        * edu.umgc.librarymanager.data.model
            * edu.umgc.librarymanager.data.model.item
            * edu.umgc.librarymanager.data.model.user
    * edu.umgc.librarymanager.gui
        * edu.umgc.librarymanager.gui.panels
    * edu.umgc.librarymanager.service
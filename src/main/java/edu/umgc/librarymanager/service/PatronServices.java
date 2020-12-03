/*
 * Filename: PatronServices.java
 * Author: Scott
 * Date Created: 12/2/2020
 */

package edu.umgc.librarymanager.service;

import edu.umgc.librarymanager.gui.GUIController;
import edu.umgc.librarymanager.gui.panels.PanelComposite;

/**
 * This class provides methods related to the Patron services.
 * @author Scott
 */
public final class PatronServices {

    private PatronServices() {}

    public static void viewProfile(GUIController control) {
        control.getFrame().getPanelComp().getUserProfilePanel().setUser(control.getCurrentUser());
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.PROFILE);
    }

    public static void viewSearch(GUIController control) {
        control.getFrame().getPanelComp().getSearchPanel().reset();
        control.getFrame().getLayout().show(control.getFrame().getPanels(), PanelComposite.SEARCH);
    }

}

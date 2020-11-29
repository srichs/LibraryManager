/*
 * Filename: UserView.java
 * Author: Scott
 * Date Created: 11/28/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.model.user.BaseUser;
import javax.swing.JPanel;

/**
 * This class is used to display a single user in a list of users.
 * @author Scott
 */
public class UserView extends JPanel {

    private static final long serialVersionUID = 1L;

    private BaseUser user;

    public UserView() {
        this.user = null;
    }

    public UserView(BaseUser user) {
        this.user = user;
    }


}

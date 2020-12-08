/*
 * Filename: MainFrame.java
 * Author: Scott
 * Date Created: 11/25/2020
 * Icon Image From: https://www.flaticon.com/free-icon/open-book_171322?term=book&page=1&position=12
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.access.HibernateUtility;
import edu.umgc.librarymanager.gui.panels.PanelComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates the main JFrame of the application. The class uses the panels provided by
 * the PanelComposite class and places each panel into an instance of the CardLayout
 * class so that they can be shown as needed. There is also an instance of the MenuBar
 * class that can be used to create different types of menu bars depending on the
 * context.
 * @author Scott
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private MenuBar menuBar;
    private JPanel panels;
    private CardLayout layout;
    private PanelComposite panelComp;

    /**
     * Constructor for the MainFrame Class.
     * @param control The GUIController to be the ActionListener.
     */
    public MainFrame(GUIController control) {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900, 500));
        this.setMinimumSize(new Dimension(900, 500));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                HibernateUtility.shutdown();
                System.exit(0);
            }
        });
        this.setIconImages(getIconList());
        initComponents(control);
    }

    // Initializes the components of the MainFrame.
    private void initComponents(GUIController control) {
        this.menuBar = new MenuBar(control);
        this.setJMenuBar(menuBar);
        this.panels = new JPanel();
        this.layout = new CardLayout();
        this.panelComp = new PanelComposite(control);
        this.panels.setLayout(this.layout);
        initPanels();
        this.getContentPane().add(this.panels, BorderLayout.CENTER);
        this.layout.show(this.panels, PanelComposite.LOGIN);
        //this.layout.show(this.panels, PanelComposite.EDIT_ITEM); // TODO for testing gui panels remove
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // This is used to add panels for use with the card layout.
    private void initPanels() {
        this.panels.add(this.panelComp.getLoginPanel(), PanelComposite.LOGIN);
        this.panels.add(this.panelComp.getLibrarianMenuPanel(), PanelComposite.LIBRARIAN_MENU);
        this.panels.add(this.panelComp.getPatronMenuPanel(), PanelComposite.PATRON_MENU);
        this.panels.add(this.panelComp.getSearchPanel(), PanelComposite.SEARCH);
        this.panels.add(this.panelComp.getUserProfilePanel(), PanelComposite.PROFILE);
        this.panels.add(this.panelComp.getAddUserPanel(), PanelComposite.ADD_USER);
        this.panels.add(this.panelComp.getAllUsersPanel(), PanelComposite.ALL_USERS);
        this.panels.add(this.panelComp.getEditUserPanel(), PanelComposite.EDIT_USER);
        this.panels.add(this.panelComp.getAllItemsPanel(), PanelComposite.ALL_ITEMS);
        this.panels.add(this.panelComp.getAddItemPanel(), PanelComposite.ADD_ITEM);
        this.panels.add(this.panelComp.getEditItemPanel(), PanelComposite.EDIT_ITEM);
        this.panels.add(this.panelComp.getSearchResultsPanel(), PanelComposite.SEARCH_RESULTS);
        this.panels.add(this.panelComp.getCheckedOutPanel(), PanelComposite.CHECKED_OUT);
    }

    // This is used to get a List of the image icons from the resources folder.
    private List<Image> getIconList() {
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/icon/16.png"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/icon/32.png"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/icon/64.png"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/icon/128.png"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/icon/256.png"))).getImage());
        return icons;
    }

    public MenuBar getTheMenuBar() {
        return this.menuBar;
    }

    public void setTheMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JPanel getPanels() {
        return this.panels;
    }

    public void setPanels(JPanel panel) {
        this.panels = panel;
    }

    public CardLayout getLayout() {
        return this.layout;
    }

    public void setLayout(CardLayout layout) {
        this.layout = layout;
    }

    public PanelComposite getPanelComp() {
        return this.panelComp;
    }

    public void setPanelComp(PanelComposite panelComp) {
        this.panelComp = panelComp;
    }

}

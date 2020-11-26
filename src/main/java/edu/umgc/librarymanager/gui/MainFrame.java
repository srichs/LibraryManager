/*
 * Filename: MainFrame.java
 * Author: Scott
 * Date Created: 11/25/2020
 */

package edu.umgc.librarymanager.gui;

import edu.umgc.librarymanager.data.access.HibernateUtility;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates the main window of the application.
 * @author Scott
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GUIController control;
    private MenuBar menuBar;
    private JPanel panels;
    private CardLayout layout;
    private PanelComposite panelComp;

    /**
     * Constructor for the NVMainFrame Class.
     * @param control The GUIController to be the ActionListener.
     */
    public MainFrame(GUIController control) {
        super();
        this.control = control;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(720, 480);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(900, 500));
        this.setLayout(new BorderLayout());
        this.setBackground(AppGUI.BACKGROUND_COLOR);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                HibernateUtility.shutdown();
                System.exit(0);
            }
        });
        //this.setIconImages(getIconList());
        this.initComponents();
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

    private void initComponents() {
        this.menuBar = new MenuBar(control);
        this.setJMenuBar(menuBar);
        this.panels = new JPanel();
        this.layout = new CardLayout();
        this.panelComp = new PanelComposite(control);
        this.panels.setLayout(this.layout);
        panels.add(panelComp.getLoginPanel(), AppGUI.LOGIN);
        panels.add(panelComp.getBlankPanel(), AppGUI.BLANK);
        this.getContentPane().add(this.panels, BorderLayout.CENTER);
        this.layout.show(this.panels, AppGUI.LOGIN);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /*private List<Image> getIconList() {
        // TODO add icon images
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("./src/main/resources/data/"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("./src/main/resources/data/"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("./src/main/resources/data/"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("./src/main/resources/data/"))).getImage());
        icons.add(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("./src/main/resources/data/"))).getImage());
        return icons;
        return null;
    }*/

}

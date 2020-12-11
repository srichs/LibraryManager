/*
 * Filename: SearchPanel.java
 * Author: Scott
 * Date Created: 11/27/2020
 */

package edu.umgc.librarymanager.gui.panels;

import edu.umgc.librarymanager.data.access.AdvSearchPart;
import edu.umgc.librarymanager.data.access.LogicalType;
import edu.umgc.librarymanager.gui.Command;
import edu.umgc.librarymanager.gui.DialogUtil;
import edu.umgc.librarymanager.gui.GUIController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to create a Search panel.
 * @author Scott
 */
public class AdvancedSearchPanel extends JPanel {

    private static final long serialVersionUID = -5577134853276029920L;

    private LogicalFieldPanel lfPanelOne;
    private LogicalFieldPanel lfPanelTwo;
    private LogicalFieldPanel lfPanelThree;
    private LogicalFieldPanel lfPanelFour;

    /**
     * The constructor for the class.
     * @param control The GUIController of the application.
     */
    public AdvancedSearchPanel(GUIController control) {
        super();
        this.lfPanelOne = new LogicalFieldPanel();
        this.lfPanelOne.hideLogicBox();
        this.lfPanelTwo = new LogicalFieldPanel();
        this.lfPanelThree = new LogicalFieldPanel();
        this.lfPanelFour = new LogicalFieldPanel();
        createPanel(control);
    }

    private void createPanel(GUIController control) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(900, 450));
        mainPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setPreferredSize(new Dimension(900, 60));
        JLabel titleLabel = new JLabel("Advanced Search");
        titleLabel.setFont(new Font(titleLabel.getName(), Font.BOLD, 24));
        titlePanel.add(titleLabel);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel fieldHolder = new JPanel();
        fieldHolder.setPreferredSize(new Dimension(900, 200));
        fieldHolder.setLayout(new BoxLayout(fieldHolder, BoxLayout.Y_AXIS));
        fieldHolder.add(this.lfPanelOne);
        fieldHolder.add(this.lfPanelTwo);
        fieldHolder.add(this.lfPanelThree);
        fieldHolder.add(this.lfPanelFour);
        searchPanel.add(fieldHolder);

        JPanel footerPanel = new JPanel(new BorderLayout());
        JPanel backPanel = new JPanel(new FlowLayout());
        backPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        JButton backButton = new JButton("Back");
        backButton.setActionCommand(Command.SEARCH);
        backButton.addActionListener((ActionListener) control);
        JPanel searchBtnPanel = new JPanel(new FlowLayout());
        searchBtnPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        JButton searchButton = new JButton("Search");
        searchButton.setActionCommand(Command.ADVANCED_SEARCH);
        searchButton.addActionListener((ActionListener) control);
        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand(Command.ADV_SEARCH_CLEAR);
        clearButton.addActionListener((ActionListener) control);
        backPanel.add(backButton);
        searchBtnPanel.add(searchButton);
        searchBtnPanel.add(clearButton);
        footerPanel.add(backPanel, BorderLayout.WEST);
        footerPanel.add(searchBtnPanel, BorderLayout.EAST);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        this.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setVisible(true);
    }

    public LogicalFieldPanel getLFPanelOne() {
        return this.lfPanelOne;
    }

    public LogicalFieldPanel getLFPanelTwo() {
        return this.lfPanelTwo;
    }

    public LogicalFieldPanel getLFPanelThree() {
        return this.lfPanelThree;
    }

    public LogicalFieldPanel getLFPanelFour() {
        return this.lfPanelFour;
    }

    public List<AdvSearchPart> buildAdvanceSearch() {
        if (this.lfPanelOne.getSearchField().getText().equals("")) {
            DialogUtil.warningMessage("Please enter select a field and enter the search terms.", "Unable to Complete Search");
            return null;
        }
        List<AdvSearchPart> list = new ArrayList<AdvSearchPart>();
        AdvSearchPart advSearch = new AdvSearchPart();
        advSearch.setField(this.lfPanelOne.getFieldBoxValue());
        advSearch.setSearchTerm(this.lfPanelOne.getSearchField().getText());
        list.add(advSearch);
        addSearchPart(this.lfPanelTwo, list);
        addSearchPart(this.lfPanelThree, list);
        addSearchPart(this.lfPanelFour, list);
        return list;
    }

    public void addSearchPart(LogicalFieldPanel panel, List<AdvSearchPart> list) {
        AdvSearchPart advSearch = checkForSearchPart(panel);
        if (advSearch != null) {
            list.add(advSearch);
        }
    }

    public AdvSearchPart checkForSearchPart(LogicalFieldPanel panel) {
        if (!panel.getSearchField().getText().equals("")) {
            AdvSearchPart advSearch = new AdvSearchPart();
            advSearch.setLogicOperator((LogicalType) panel.getLogicBox().getSelectedItem());
            advSearch.setField(panel.getFieldBoxValue());
            advSearch.setSearchTerm(panel.getSearchField().getText());
            return advSearch;
        }
        return null;
    }

    public void reset() {
        this.lfPanelOne.reset();
        this.lfPanelTwo.reset();
        this.lfPanelThree.reset();
        this.lfPanelFour.reset();
    }

}

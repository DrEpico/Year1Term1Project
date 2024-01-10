/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c2197694.phase_4;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * GUIApp class represents the main application window for seeing all the 
 * itineraries in a list after reading them from the text file.
 * 
 * It displays a table of itineraries and allows users to view details by clicking on a row.
 * 
 * @author C2197694
 */
public class GUIApp extends JFrame {
    
    /**
     * List of itineraries to be displayed in the application.
     */
    List<Itinerary> itineraries;
    
    /**
     * Constructs a GUIApp with the given list of itineraries.
     *
     * @param itineraries List of itineraries to be displayed.
     */
    public GUIApp(List<Itinerary> itineraries) {
        this.itineraries = itineraries;

        initComponents();

        setTitle("Exciting Activities Ltd - Management Screen");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Initializes the components of the GUI, including the table of itineraries.
     */
    private void initComponents() {
        String[] columns = {"Itinerary ref", "Lead Attendee", "Total Attendees", 
                "Total Activities", "Total Cost"};

        DefaultTableModel itineraryTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable itineraryTable = new JTable(itineraryTableModel);

        int rowHeight = 25;
        itineraryTable.setRowHeight(rowHeight);
        int itineraryTableHeight = (itineraries.size() < 10) ? rowHeight * itineraries.size() : rowHeight * 10;
//        itineraryTable.setPreferredScrollableViewportSize(new Dimension(600, itineraryTableHeight));
        for (Itinerary itinerary : itineraries) {
            Object[] rowData = {
                itinerary.getId(),
                itinerary.getAttendeeInitialSurname(),
                itinerary.getAttendee().getMembers(),
                itinerary.getActivities().size(),
                itinerary.getFinalCost()
            };
            itineraryTableModel.addRow(rowData);
        }

        itineraryTable.addMouseListener((new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = itineraryTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Handle the double-click event here
                    Itinerary selectedItinerary = itineraries.get(selectedRow);
                    openDetailsWindow(selectedItinerary);
                }
            }
        }));

        JScrollPane scrollPane = new JScrollPane(itineraryTable);
        // Set the preferred size of the JTable
        itineraryTable.setPreferredScrollableViewportSize(new Dimension(600, itineraryTableHeight));
        JLabel note = new JLabel("Hint: click on a row to see details of the itinerary.");
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        panel.add(note);
        add(panel);
        setResizable(false);
    }
    
    /**
     * Opens a new window displaying details of the selected itinerary.
     *
     * @param itinerary The selected itinerary.
     */
    private void openDetailsWindow(Itinerary itinerary) {
        // Implement the logic to open a new window with details of the selected itinerary
        JFrame detailsFrame = new JFrame("Exciting Activities Ltd - Itinerary Screen");
        detailsFrame.setSize(500, 300);

        JPanel detailsPanel = new JPanel();

        String labelText = "<html><div style='border: 1px solid #999; padding: 10px;'><table>"
                + "<tr><td><b>Lead Attendee</b></td><td>" + itinerary.getAttendee().getName() + "</td></tr>"
                + "<tr><td><b>Total attendees</b></td><td>" + itinerary.getAttendee().getMembers() + "</td></tr>"
                + "<tr><td><b>Total cost</b></td><td>" + itinerary.getFinalCost() + "</td></tr>"
                + "</table></html>";

        JLabel itineraryInfo = new JLabel(labelText);

        detailsPanel.add(itineraryInfo);

        // Set the preferred size of detailsPanel to make it wider
//        detailsPanel.setPreferredSize(new Dimension(550, 400));
        displayItineraryAddOnsTable(itinerary, detailsPanel, detailsFrame);
        displayaActivityTable(itinerary, detailsPanel, detailsFrame);
        detailsFrame.add(detailsPanel);

        detailsFrame.setLocationRelativeTo(this);
        detailsFrame.setResizable(false);
        detailsFrame.setVisible(true);
    }
    
    /**
     * Displays the table of itinerary add-ons in a details window.
     *
     * @param itinerary    The itinerary for which to display add-ons.
     * @param detailsPanel The panel in which to display the add-ons table.
     * @param detailsFrame The frame for the details window.
     */
    public void displayItineraryAddOnsTable(Itinerary itinerary, JPanel detailsPanel, JFrame detailsFrame) {
        String[] listTitle = {"Itinerary Add-on(s)"};
        DefaultTableModel addonsTableModel = new DefaultTableModel(listTitle, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable addonsTable = new JTable(addonsTableModel);
        addonsTable.setRowSelectionAllowed(false);
        int rowHeight = 21;
        addonsTable.setRowHeight(rowHeight);

        //fit row size to number of rows if less <= 3 rows
        int itineraryAddOnTableHeight = (itinerary.getAddOns().size() <= 3) ? rowHeight * itinerary.getAddOns().size() : rowHeight * 3;

        if (!itinerary.getAddOns().isEmpty()) {
            for (AddOn addOn : itinerary.getAddOns()) {
                Object[] rowData = {
                    addOn.getName()
                };
                addonsTableModel.addRow(rowData);
            }
            // display upto 3 addons at the same time if the itinerary has add-ons
            addonsTable.setPreferredScrollableViewportSize(new Dimension(addonsTable.getPreferredScrollableViewportSize().width, itineraryAddOnTableHeight));
        } else {
            Object[] rowData = {
                "N/A"
            };
            addonsTableModel.addRow(rowData);
            // display only 1 row (to display "N/A") for itinerary addons if the itinerary has add-ons
            addonsTable.setPreferredScrollableViewportSize(new Dimension(addonsTable.getPreferredScrollableViewportSize().width, rowHeight * 1));
        }

        JScrollPane scrollPane = new JScrollPane(addonsTable);
        detailsPanel.add(scrollPane);  // Add the scrollPane directly to detailsPanel

    }
    
    /**
     * Displays the table of activities in a details window.
     *
     * @param itinerary    The itinerary for which to display activities.
     * @param detailsPanel The panel in which to display the activities table.
     * @param detailsFrame The frame for the details window.
     */
    public void displayaActivityTable(Itinerary itinerary, JPanel detailsPanel, JFrame detailsFrame) {
        String[] listTitle = {"Activities", "Add-ons"};
        DefaultTableModel activitiesTableModel = new DefaultTableModel(listTitle, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable activitiesTable = new JTable(activitiesTableModel);
        activitiesTable.setRowSelectionAllowed(false);
        int rowHeight = 21;
        activitiesTable.setRowHeight(rowHeight);

        int itineraryAddOnTableHeight = (itinerary.getAddOns().size() <= 4) ? rowHeight * itinerary.getActivities().size() : rowHeight * 4;

        for (Activity activity : itinerary.getActivities()) {
            String addOnsString = "";
            if (activity.getAddOns().isEmpty()) {
                addOnsString = "N/A";

            } else {
                int loopCounter = 1;
                for (AddOn addOn : activity.getAddOns()) {
                    loopCounter++;
                    //if counter is less than addOns list size 
                    if (loopCounter > activity.getAddOns().size()) {
                        addOnsString += addOn.getName();
                    } else {
                        addOnsString += addOn.getName() + ", ";
                    }
                }
            }
            Object[] rowData = {
                activity.getTitle(),
                addOnsString
            };
            activitiesTableModel.addRow(rowData);

        }
        // display upto 3 addons at the same time if the itinerary has add-ons
        activitiesTable.setPreferredScrollableViewportSize(new Dimension(activitiesTable.getPreferredScrollableViewportSize().width, itineraryAddOnTableHeight));

        JScrollPane scrollPane = new JScrollPane(activitiesTable);
        detailsPanel.add(scrollPane);  // Add the scrollPane directly to detailsPanel

    }
}


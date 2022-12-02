package gui;


import model.EventLog;
import model.List;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

// Main menu GUI where Edit Equipment and View Equipment can be accessed
public class CubingSimulatorGUI extends JFrame {
    private static final String JSON_STORE = "./data/testWriterGeneralEquipmentList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JDesktopPane desktop;
    private JInternalFrame mainMenu;


    private static final int WIDTH = 800;

    private static final int HEIGHT = 700;

    private List list;

    public CubingSimulatorGUI() {
        init();
        displayMenu();

    }

    /* Requires:
       Modifies: this
       Effects: initialises equipment/cube lists, writer, reader
     */
    private void init() {
        list = new List();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /* Requires:
       Modifies: this
       Effects: Initializes main menu
     */
    private void displayMenu() {
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopClick());

        initMainMenu();

        setContentPane(desktop);
        setTitle("Cubing Simulator Main Menu");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        mainMenu.pack();
        mainMenu.setVisible(true);
        mainMenu.setSize(WIDTH / 2, HEIGHT / 2);
        desktop.add(mainMenu);

        loadFileAndImage();
    }

    /* Requires:
       Modifies: displayMenu
       Effects: creates pop up with load option with image
     */
    private void loadFileAndImage() {
        ImageIcon icon = new ImageIcon("./data/MapleStory.png");
        int loadList = JOptionPane.showConfirmDialog(null, "Load list?",
                "Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        if (loadList == JOptionPane.YES_OPTION) {
            loadFunction();
            list.logLoadEvent();
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                list.printLog(EventLog.getInstance());
                System.exit(0);
            }
        });

        setVisible(true);
    }

    /* Requires:
       Modifies: displayMenu
       Effects: creates the main menu
     */
    private void initMainMenu() {
        mainMenu = new JInternalFrame("Main Menu", false, false, false, false);
        mainMenu.setLayout(new BorderLayout());
        addMenuOptions();
    }


    /* Requires:
       Modifies: displayMenu
       Effects: adds menu options to main menu
     */
    private void addMenuOptions() {
        JPanel menuButtons = new JPanel();
        JButton viewEquipmentButton = new JButton("View/Edit Equipment List");
        JButton editEquipmentButton = new JButton("Edit Equipment Stats");
        JButton saveList = new JButton("Save list");
        menuButtons.setLayout(new FlowLayout());
        menuButtons.add(viewEquipmentButton);
        menuButtons.add(editEquipmentButton);
        menuButtons.add(saveList);
        viewEquipmentButton.addActionListener(e -> viewEquipmentList());
        editEquipmentButton.addActionListener(e -> editEquipmentList());
        saveList.addActionListener(e -> saveFunction());
        mainMenu.add(menuButtons);

    }

    /* Requires:
       Modifies: this
       Effects: creates new pop up within menu frame to view equipment list
     */
    private void viewEquipmentList() {
        //Create and set up the window.
        JInternalFrame frame = new JInternalFrame("View Equipment", false, true,
                false, false);
        desktop.add(frame);
        frame.toFront();
        //Create and set up the content pane.
        JComponent newContentPane = new gui.ViewEquipment(list);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                list = ViewEquipment.getList();
            }
        });
    }

    /* Requires:
       Modifies: this
       Effects:  creates pop up within main menu frame to edit equipment list
     */
    private void editEquipmentList() {
        //Create and set up the window.
        JInternalFrame frame = new JInternalFrame("Edit Equipment Stats", false, true,
                false, false);
        desktop.add(frame);
        frame.toFront();
        //Create and set up the content pane.

        JComponent newContentPane = new gui.EditEquipment(list);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                list = EditEquipment.getList();
            }
        });
    }

    /* Requires:
       Modifies: testWriterGeneralEquipmentList.json
       Effects: saves current list to json file
     */
    public void saveFunction() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            list.logSaveEvent();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: if load function is successful, it loads the zoo from the json file to be viewed
    // else, provides error message
    private void loadFunction() {
        try {
            list = jsonReader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Unable to write to file: " + JSON_STORE);
        }
    }

    private class DesktopClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            CubingSimulatorGUI.this.requestFocusInWindow();
        }
    }

}


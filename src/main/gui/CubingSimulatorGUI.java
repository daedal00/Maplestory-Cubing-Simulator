package gui;


import model.List;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CubingSimulatorGUI extends JFrame {
    private static final String JSON_STORE = "./data/testWriterGeneralEquipmentList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JDesktopPane desktop;
    private JInternalFrame mainMenu;


    private static final int WIDTH = 800;

    private static final int HEIGHT = 700;

    private List list;

    private JList<List> jlist;

    public CubingSimulatorGUI() throws IOException {
        init();
        loadPopUp();
        loadPopUp2();
        displayMenu();

    }

    private void init() {
        list = new List();
        jlist = new JList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    private void loadPopUp() {
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Click me to load a file!");

        parent.add(button);
        parent.pack();
        parent.setVisible(true);
        setVisible(true);
        toFront();

        button.addActionListener(evt -> loadFunction());
    }

    private void loadPopUp2() throws IOException {
        final JFrame parent = new JFrame();

        BufferedImage myPicture = ImageIO.read(new File("./data/MapleStory.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        parent.add(picLabel);
        parent.pack();
        parent.setVisible(true);
        setVisible(true);
        toFront();

    }

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initMainMenu() {
        mainMenu = new JInternalFrame("Main Menu", false, false, false, false);
        mainMenu.setLayout(new BorderLayout());
        addMenuOptions();
    }


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

    private void viewEquipmentList() {
        //Create and set up the window.
        JInternalFrame frame = new JInternalFrame("View Equipment", false, true,
                false, false);
        desktop.add(frame);
        frame.toFront();
        //Create and set up the content pane.
        JComponent newContentPane = new gui.ViewEquipment(jlist, list);
        list = ViewEquipment.getList();
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

    public void saveFunction() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
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


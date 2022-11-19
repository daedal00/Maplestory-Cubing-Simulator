package gui;


import model.Cubes;
import model.Equipment;
import model.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CubingSimulatorGUI extends JFrame {


    private JDesktopPane desktop;
    private JInternalFrame mainMenu;

    private static final int WIDTH = 800;

    private static final int HEIGHT = 700;

    private List list;

    private JList<List> jlist;

    public CubingSimulatorGUI() {
        init();
        displayMenu();

    }

    private void init() {
        list = new List();
        jlist = new JList<>();
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
        menuButtons.setLayout(new FlowLayout());
        menuButtons.add(viewEquipmentButton);
        menuButtons.add(editEquipmentButton);
        viewEquipmentButton.addActionListener(e -> viewEquipmentList());
        editEquipmentButton.addActionListener(e -> editEquipmentList());
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

    private class DesktopClick extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            CubingSimulatorGUI.this.requestFocusInWindow();
        }
    }

}


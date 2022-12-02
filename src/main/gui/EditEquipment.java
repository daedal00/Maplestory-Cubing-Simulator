package gui;

import javax.swing.*;

import model.Cubes;
import model.Equipment;
import model.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.lang.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Edit equipment pop up where user can edit equipment flame and cube stats
public class EditEquipment extends JPanel {
    public static List getList() {
        return list;
    }

    private static List list;

    JTextArea output;
    JList jlist;

    ArrayList<String> equipmentNames;
    String[] listData;

    JPanel controlPane;

    JScrollPane outputPane;

    JSplitPane splitPane;

    JButton addFlameButton;

    public EditEquipment(List mainList) {
        super(new BorderLayout());
        list = mainList;

        initList();

        jlist = new JList(listData);

        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        initLayout();

        jlist.addListSelectionListener(new MyListListener());

        initFlameButton();

        String cubeString = "Cube Equipment";
        JButton cubeButton = new JButton(cubeString);
        cubeButton.setActionCommand(cubeString);
        cubeButton.addActionListener(new CubeEquipment());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(cubeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(addFlameButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(buttonPane, BorderLayout.PAGE_END);
    }

    /* Requires:
       Modifies: this
       Effects: initializes flame button
     */
    private void initFlameButton() {
        String flameString = "Flame Equipment";
        addFlameButton = new JButton(flameString);
        addFlameButton.setActionCommand(flameString);
        addFlameButton.addActionListener(new FlameEquipment());
    }

    /* Requires:
       Modifies: this
       Effects:  initializes layout for Edit equipment menu
     */
    private void initLayout() {

        JScrollPane listPane = new JScrollPane(jlist);

        output = new JTextArea(1, 10);
        output.setEditable(false);
        outputPane = new JScrollPane(output,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        controlPane = new JPanel();

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        add(splitPane, BorderLayout.CENTER);

        JPanel topHalf = new JPanel();
        topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
        JPanel listContainer = new JPanel(new GridLayout(1, 1));
        listContainer.setBorder(BorderFactory.createTitledBorder(
                "List"));
        listContainer.add(listPane);

        topHalf.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        topHalf.add(listContainer);

        topHalf.setMinimumSize(new Dimension(100, 50));
        topHalf.setPreferredSize(new Dimension(100, 110));
        splitPane.add(topHalf);

        bottomHalfInit();
    }

    /* Requires:
       Modifies: initLayout
       Effects: adds bottomHalf to the initialized main menu for edit equipment
     */
    private void bottomHalfInit() {

        JPanel bottomHalf = new JPanel(new BorderLayout());
        bottomHalf.add(controlPane, BorderLayout.PAGE_START);
        bottomHalf.add(outputPane, BorderLayout.CENTER);

        bottomHalf.setPreferredSize(new Dimension(450, 135));
        splitPane.add(bottomHalf);
    }

    /* Requires: List mainList
       Modifies: equipmentNames, listData
       Effects: gets list from main menu and passes into equipmentNames and listData
     */
    private void initList() {
        equipmentNames = new ArrayList<>();

        for (Equipment e : list.getEquipmentList()) {
            equipmentNames.add(e.getName());
        }

        listData = new String[equipmentNames.size()];

        for (int i = 0; i < equipmentNames.size(); i++) {
            listData[i] = equipmentNames.get(i);
        }
    }

    // Flame equipment class which flames equipment stats on button click
    class FlameEquipment implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int index = jlist.getSelectedIndex();
                Equipment tempEquip = list.getEquipment(index);
                tempEquip.rerollFlame();
                list.replaceEquipment(tempEquip, index);
                output.setText("");
                outputChangesFlame(index);
                outputChangesCube(index);
                list.logFlameEquipment();
            } catch (IndexOutOfBoundsException exception) {
                output.setText("");
                output.append("List is empty! Add equipment first.");
            }

        }
    }

    // Cube equipment class which cubes equipment stats on button click
    class CubeEquipment implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int index = jlist.getSelectedIndex();
                Cubes tempCube = list.getCube(index);
                tempCube.rerollCube();
                list.replaceCube(tempCube, index);
                output.setText("");
                outputChangesFlame(index);
                outputChangesCube(index);
                list.logRollCube();
            } catch (IndexOutOfBoundsException exception) {
                output.setText("");
                output.append("List is empty! Add equipment first.");
            }
        }

    }

    // List selection Listener class
    class MyListListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int index = jlist.getSelectedIndex();
                output.setText("");
                outputChangesFlame(index);
                outputChangesCube(index);
            }
        }
    }


    /* Requires: equipment/cube
       Modifies: JTextArea
       Effects: outputs new flame stats into JTextArea output
     */
    public void outputChangesFlame(int index) {
        int dexterityInt = list.getEquipment(index).getDexterity();
        String dexterityString = String.valueOf(dexterityInt);

        int strengthInt = list.getEquipment(index).getStrength();
        String strengthString = String.valueOf(strengthInt);

        int luckInt = list.getEquipment(index).getLuck();
        String luckString = String.valueOf(luckInt);

        int intelligenceInt = list.getEquipment(index).getIntelligence();
        String intelligenceString = String.valueOf(intelligenceInt);

        output.append(list.getEquipmentNameIndex(index) + "\n");
        output.append("Dex =" + dexterityString + "\n");
        output.append("Strength = " + strengthString + "\n");
        output.append("Luck =" + luckString + "\n");
        output.append("Int =" + intelligenceString + "\n");

    }

    /* Requires: equipment/cube
       Modifies: JTextArea
       Effects: outputs new cube stats into JTextArea output
     */
    private void outputChangesCube(int index) {
        output.append("Cube Names =" + list.getCube(index).getCubeNames().toString() + "\n");
        output.append("Cube Values =" + list.getCube(index).getCubeValues().toString() + "\n");
    }

}

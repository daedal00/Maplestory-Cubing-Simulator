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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditEquipment extends JPanel {
    public static List getList() {
        return list;
    }

    private static List list;

    JTextArea output;
    JList jlist;
    String newline = "\n";

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public EditEquipment(List mainList) {
        super(new BorderLayout());
        list = mainList;

        ArrayList<String> equipmentNames = new ArrayList<>();
        for (Equipment e : list.getEquipmentList()) {
            equipmentNames.add(e.getName());
        }

        String[] listData = new String[equipmentNames.size()];

        for (int i = 0; i < equipmentNames.size(); i++) {
            listData[i] = equipmentNames.get(i);
        }

        jlist = new JList(listData);

        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane listPane = new JScrollPane(jlist);

        output = new JTextArea(1, 10);
        output.setEditable(false);
        JScrollPane outputPane = new JScrollPane(output,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel controlPane = new JPanel();

        //Do the layout.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
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

        JPanel bottomHalf = new JPanel(new BorderLayout());
        bottomHalf.add(controlPane, BorderLayout.PAGE_START);
        bottomHalf.add(outputPane, BorderLayout.CENTER);

        bottomHalf.setPreferredSize(new Dimension(450, 135));
        splitPane.add(bottomHalf);

        jlist.addListSelectionListener(e -> {
            int index = jlist.getSelectedIndex();
            outputChangesFlame(index);
            outputChangesCube(index);
        });

        String flameString = "Flame Equipment";
        JButton addFlameButton = new JButton(flameString);
        addFlameButton.setActionCommand(flameString);
        addFlameButton.addActionListener(new FlameEquipment());

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

    class FlameEquipment implements ActionListener {
        int index = jlist.getSelectedIndex();
        Equipment tempEquip = list.getEquipment(index + 2);

        public void actionPerformed(ActionEvent e) {
            tempEquip.rerollFlame();
            list.replaceEquipment(tempEquip, index + 2);
            outputChangesFlame(index + 2);
        }
    }

    class CubeEquipment implements ActionListener {
        int index = jlist.getSelectedIndex();
        Cubes tempCube = list.getCube(index + 2);

        public void actionPerformed(ActionEvent e) {
            tempCube.rerollCube();
            list.replaceCube(tempCube, index + 2);
            outputChangesCube(index + 2);
        }

    }


    public void outputChangesFlame(int index) {
        int dexterityInt = list.getEquipment(index + 1).getDexterity();
        String dexterityString = String.valueOf(dexterityInt);

        int strengthInt = list.getEquipment(index + 1).getStrength();
        String strengthString = String.valueOf(strengthInt);

        int luckInt = list.getEquipment(index + 1).getDexterity();
        String luckString = String.valueOf(luckInt);

        int intelligenceInt = list.getEquipment(index + 1).getDexterity();
        String intelligenceString = String.valueOf(intelligenceInt);

        output.append(list.getEquipmentNameIndex(index) + "\n");
        output.append("Dex =" + dexterityString + "\n");
        output.append("Strength = " + strengthString + "\n");
        output.append("Luck =" + luckString + "\n");
        output.append("Int =" + intelligenceString + "\n");

    }

    private void outputChangesCube(int index) {
        output.append("Cube Names =" + list.getCube(index + 1).printCubeNameString() + "\n");
        output.append("Cube Values =" + list.getCube(index + 1).printCubeValueString() + "\n");
    }

}

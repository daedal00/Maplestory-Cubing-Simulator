package gui;

import model.Cubes;
import model.Equipment;
import model.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEquipment extends JPanel implements ListSelectionListener {
    private JList jlist;
    private final DefaultListModel listModel;

    public static List getList() {
        return list;
    }

    private static List list;

    private static final String addString = "Add Equipment";
    private static final String removeString = "Remove Equipment";
    private final JButton removeEquipmentButton;
    private final JTextField equipmentName;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public ViewEquipment(JList myList, List mainList) {
        super(new BorderLayout());
        jlist = myList;
        list = mainList;
        listModel = new DefaultListModel();
        for (Equipment e : list.getEquipmentList()) {
            listModel.addElement(e.getName());
        }
        jlist = new JList(listModel);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setSelectedIndex(0);
        jlist.addListSelectionListener(this);
        jlist.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(jlist);

        JButton addEquipmentButton = new JButton(addString);
        AddEquipment addEquipment = new AddEquipment(addEquipmentButton);
        addEquipmentButton.setActionCommand(addString);
        addEquipmentButton.addActionListener(addEquipment);
        addEquipmentButton.setEnabled(false);

        removeEquipmentButton = new JButton(removeString);
        removeEquipmentButton.setActionCommand(removeString);
        removeEquipmentButton.addActionListener(new RemoveEquipment());

        equipmentName = new JTextField(10);
        equipmentName.addActionListener(addEquipment);
        equipmentName.getDocument().addDocumentListener(addEquipment);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeEquipmentButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(equipmentName);
        buttonPane.add(addEquipmentButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    class RemoveEquipment implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = jlist.getSelectedIndex();
            listModel.remove(index);
            list.removeEquipment(index + 1);
            list.removeCube(index);

            int size = listModel.getSize();

            if (size == 0) {
                removeEquipmentButton.setEnabled(false);

            } else {
                if (index == listModel.getSize()) {
                    index--;
                }

                jlist.setSelectedIndex(index);
                jlist.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class AddEquipment implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddEquipment(JButton button) {
            this.button = button;
        }


        public void actionPerformed(ActionEvent e) {
            String name = equipmentName.getText();
            Equipment tempEquip = new Equipment(name);
            Cubes tempCube = new Cubes();


            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(new JFrame(), "Equipment already exists!");
                equipmentName.requestFocusInWindow();
                equipmentName.selectAll();
                return;
            }

            int index = jlist.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            listModel.insertElementAt(equipmentName.getText(), index);
            list.saveEquipment(tempEquip, index);
            list.saveCube(tempCube, index);

            equipmentName.requestFocusInWindow();
            equipmentName.setText("");

            jlist.setSelectedIndex(index);
            jlist.ensureIndexIsVisible(index);
        }

        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }


    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            removeEquipmentButton.setEnabled(jlist.getSelectedIndex() != -1);
        }
    }


}

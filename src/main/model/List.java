package model;

import java.util.ArrayList;

// Creates array list that contains equipment and cube objects
public class List {
    private final ArrayList<Equipment> equipmentList = new ArrayList<>();
    private final ArrayList<Cubes> cubeList = new ArrayList<>();
    Cubes currentCube;
    Equipment currentEquipment;

    // Requires:
    // Modifies:
    // Effects:
    public void saveCube(Cubes cube) {
        this.currentCube = cube;
        cubeList.add(cube);
    }

    // Requires:
    // Modifies:
    // Effects:
    public void saveEquipment(Equipment equipment) {
        this.currentEquipment = equipment;
        equipmentList.add(equipment);
    }

    // Requires:
    // Modifies:
    // Effects:
    public void removeEquipment(int i) {
        this.equipmentList.remove(i - 1);
    }

    // Requires:
    // Modifies:
    // Effects:
    public void printEquipmentList() {
        for (int i = 1; i < equipmentList.size() + 1; i++) {
            System.out.println(i + "." + currentEquipment.getName());
            equipmentList.get(i - 1).printEquipment();
            cubeList.get(i - 1).printCube();
            System.out.println("\n");
        }
    }

    // Requires:
    // Modifies:
    // Effects:
    public int getSize() {
        return equipmentList.size();
    }

    // Requires:
    // Modifies:
    // Effects:
    public void replaceEquipment(Equipment equipment, int i) {
        equipmentList.set(i, equipment);
    }

    // Requires:
    // Modifies:
    // Effects:
    public void replaceCube(Cubes cubes, int i) {
        cubeList.set(i, cubes);
    }

    // Requires:
    // Modifies:
    // Effects:
    public String getEquipmentName() {
        return currentEquipment.getName();
    }

    // Requires:
    // Modifies:
    // Effects:
    public Equipment getEquipment(int i) {
        return equipmentList.get(i - 1);
    }

    // Requires:
    // Modifies:
    // Effects:
    public Cubes getCube(int i) {
        return cubeList.get(i - 1);
    }
}

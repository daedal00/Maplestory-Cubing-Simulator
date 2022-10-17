package model;

import java.util.ArrayList;

public class List {
    private final ArrayList<Equipment> equipmentList = new ArrayList<>();
    private final ArrayList<Cubes> cubeList = new ArrayList<>();
    Cubes currentCube;
    Equipment currentEquipment;

    public void saveCube(Cubes cube) {
        this.currentCube = cube;
        cubeList.add(cube);
    }

    public void saveEquipment(Equipment equipment) {
        this.currentEquipment = equipment;
        equipmentList.add(equipment);
    }

    public void removeEquipment(int i) {
        this.equipmentList.remove(i - 1);
    }

    public void printEquipmentList() {
        for (int i = 1; i < equipmentList.size() + 1; i++) {
            System.out.println(i + "." + currentEquipment.getName());
            equipmentList.get(i - 1).printEquipment();
            cubeList.get(i - 1).printCube();
            System.out.println("\n");
        }
    }

    public int getSize() {
        if (equipmentList.size() == cubeList.size()) {
            return equipmentList.size();
        }
        return 0;
    }

    public void replaceEquipment(Equipment equipment, int i) {
        equipmentList.set(i, equipment);
    }

    public void replaceCube(Cubes cubes, int i) {
        cubeList.set(i, cubes);
    }

    public String getEquipmentName() {
        return currentEquipment.getName();
    }

    public Equipment getEquipment(int i) {
        return equipmentList.get(i - 1);
    }

    public Cubes getCube(int i) {
        return cubeList.get(i - 1);
    }
}

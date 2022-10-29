package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Creates array list that contains equipment and cube objects
public class List {
    public ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    private final ArrayList<Equipment> equipmentList = new ArrayList<>();

    public ArrayList<Cubes> getCubeList() {
        return cubeList;
    }

    private final ArrayList<Cubes> cubeList = new ArrayList<>();
    Cubes currentCube;
    Equipment currentEquipment;

    // Requires: Cube
    // Modifies: cube List
    // Effects: saves cube to cube list arraylist
    public void saveCube(Cubes cube) {
        this.currentCube = cube;
        cubeList.add(cube);
    }

    // Requires: equipment
    // Modifies: equipment List
    // Effects: saves equipment to equipment list arraylist
    public void saveEquipment(Equipment equipment) {
        this.currentEquipment = equipment;
        equipmentList.add(equipment);
    }

    // Requires: index
    // Modifies: equipment list
    // Effects: removes the equipment at index - 1 in the equipment list
    public void removeEquipment(int i) {
        this.equipmentList.remove(i - 1);
    }

    // Effects: prints both equipment list and cube list as one
    public void printList() {
        for (int i = 1; i < equipmentList.size() + 1; i++) {
            System.out.println(i + "." + currentEquipment.getName());
            equipmentList.get(i - 1).printEquipment();
            cubeList.get(i - 1).printCube();
            System.out.println("\n");
        }
    }

    // Effects: returns equipment list size
    public int getSize() {
        return equipmentList.size();
    }

    // Requires: equipment and index
    // Modifies: equipment list
    // Effects: replaces the equipment at index i in equipment list with the equipment in parameter
    public void replaceEquipment(Equipment equipment, int i) {
        equipmentList.set(i, equipment);
    }

    // Requires: cubes and index
    // Modifies: cubes list
    // Effects: replaces the cube at index i in cube list with the cube in parameter
    public void replaceCube(Cubes cubes, int i) {
        cubeList.set(i, cubes);
    }

    // Effects: returns equipment name in equipment list
    public String getEquipmentName() {
        return currentEquipment.getName();
    }

    // Effects: returns equipment at index i in equipment list
    public Equipment getEquipment(int i) {
        return equipmentList.get(i - 1);
    }


    // Effects: returns cube at index i in cube list
    public Cubes getCube(int i) {
        return cubeList.get(i - 1);
    }


    // EFFECTS: returns things in this workroom as a JSON array
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray equipList = new JSONArray();
        JSONArray cubeArrayList = new JSONArray();
        for (Equipment e : equipmentList) {
            JSONObject equipment = new JSONObject();
            equipment.put("Name", e.getName());
            equipment.put("Strength", e.getStrength());
            equipment.put("Luck", e.getLuck());
            equipment.put("Dexterity", e.getDexterity());
            equipment.put("Intelligence", e.getIntelligence());
            equipList.put(equipment);
        }
        for (Cubes c : cubeList) {
            JSONObject cube = new JSONObject();
            for (int i = 0; i < 3; i++) {
                cube.put(c.getCubeNames().get(i), c.getCubeValues().get(i));
            }
            cubeArrayList.put(cube);
        }
        json.put("equipment", equipList);
        json.put("cube", cubeArrayList);

        return json;
    }
}

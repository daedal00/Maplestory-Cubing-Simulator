package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CubingSimulatorUI {
    private Scanner input;
    private List list;

    public CubingSimulatorUI() {
        runCubingSimulator();
    }

    private void runCubingSimulator() {
        boolean keepRunning = true;
        int userAnswer;
        init();
        System.out.println("Would you like to load from a file?\n 1. Yes \n 2. No\n");
        userAnswer = input.nextInt();
        if (userAnswer == 1) {
            cubeReader();
        }
        while (keepRunning) {
            displayMenu();
            userAnswer = input.nextInt();
            if (userAnswer == 7) {
                cubeWriter();
                keepRunning = false;
            } else {
                processAnswer(userAnswer);
            }
        }
        System.out.println("Done!");
    }


    private void cubeWriter() {
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralEquipmentList.json");
        try {
            writer.open();
            writer.write(list);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't save file");
        }
    }

    private void cubeReader() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralEquipmentList.json");
        try {
            this.list = reader.read();
        } catch (IOException e) {
            System.out.println("Couldn't read from file");
        }
    }

    private void processAnswer(int userAnswer) {
        if (userAnswer == 1) {
            doAddEquipment();
        } else if (userAnswer == 2) {
            doRemoveEquipment();
        } else if (userAnswer == 3) {
            doRerollEquipment();
        } else if (userAnswer == 4) {
            doCubeEquipment();
        } else if (userAnswer == 6) {
            printEquipmentList();
        } else {
            System.out.println("User input is not valid!");
        }
    }

    private void init() {
        input = new Scanner(System.in);
        list = new List();
    }

    private void displayMenu() {
        System.out.println("Would you like to \n1) add equipment "
                + "\n2) remove equipment \n3) reroll flame (equipment) stats \n4) reroll cube stats"
                + "\n6) view equipment \n7) save and quit");

    }

    private void doAddEquipment() {
        String equipmentName;
        System.out.println("What is the name of the equipment: ");
        equipmentName = input.next();
        Equipment equipment = new Equipment(equipmentName);
        Cubes cubes = new Cubes();
        this.list.saveEquipment(equipment);
        this.list.saveCube(cubes);
    }

    private void doRemoveEquipment() {
        int equipmentIndex;
        printEquipmentList();
        System.out.println("Which equipment do you want to remove?: ");
        equipmentIndex = input.nextInt();
        list.removeEquipment(equipmentIndex);
    }

    private void doRerollEquipment() {
        int equipmentIndex;
        printEquipmentList();
        System.out.println("Which equipment do you want to flame?: ");
        equipmentIndex = input.nextInt();
        Equipment equipment = list.getEquipment(equipmentIndex);
        equipment.rerollFlame();
        list.replaceEquipment(equipment, equipmentIndex - 1);
    }

    private void doCubeEquipment() {
        int equipmentIndex;
        printEquipmentList();
        System.out.println("Which equipment do you want to cube?: ");
        equipmentIndex = input.nextInt();
        Cubes cube = list.getCube(equipmentIndex);
        cube.rerollCube();
        list.replaceCube(cube, equipmentIndex - 1);
    }

    private void printEquipmentList() {
        System.out.println("Equipment List:\n");
        list.printList();
    }
}

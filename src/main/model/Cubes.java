package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Cubes class that stores 3 random cube names and cube values
public class Cubes {
    private final ArrayList<String> defaultCubeNames = new ArrayList<>(
            Arrays.asList("Str",
                    "Dex",
                    "Luk",
                    "Int"));
    private final ArrayList<Integer> defaultCubeValues = new ArrayList<>(
            Arrays.asList(3,
                    6,
                    9,
                    12));

    public ArrayList<String> getCubeNames() {
        return cubeNames;
    }

    private ArrayList<String> cubeNames;

    public ArrayList<Integer> getCubeValues() {
        return cubeValues;
    }

    private final ArrayList<Integer> cubeValues;

    public Cubes() {
        cubeNames = new ArrayList<>();
        cubeValues = new ArrayList<>();
        setCubeName();
        setCubeValue();
    }

    public Cubes(String name1, Integer int1, String name2, Integer int2, String name3, Integer int3) {

        cubeNames = new ArrayList<>();
        cubeValues = new ArrayList<>();
        cubeNames.add(name1);
        cubeNames.add(name2);
        cubeNames.add(name3);
        cubeValues.add(int1);
        cubeValues.add(int2);
        cubeValues.add(int3);
    }

    // Effects: returns cube names list size
    public int getSize() {
        return cubeNames.size();
    }

    // Effects: generates random number within 0-3
    public int getRandomListNumber() {
        Random rand = new Random();
        int upperbound = 4;
        return rand.nextInt(upperbound - 1);
    }

    // Effects: Sets 3 random names from cube names to cube
    public void setCubeName() {
        ArrayList<String> tempNames = defaultCubeNames;
        tempNames.remove(getRandomListNumber());

        cubeNames = tempNames;

    }

    // Effects: sets 3 random values from cube values to cube
    public void setCubeValue() {
        for (int i = 0; i < 3; i++) {
            cubeValues.add(defaultCubeValues.get(getRandomListNumber()));
        }
    }

    // Effects: prints the cube with its new 3 names with corresponding values
    public void printCube() {
        for (int i = 0; i < 3; i++) {
            System.out.println(cubeNames.get(i));
            System.out.println(cubeValues.get(i) + " %");
        }
    }

    public String printCubeNameString() {
        return cubeNames.toString();
    }

    public String printCubeValueString() {
        return cubeValues.toString();
    }

    // Requires: cube with 3 names and values
    // Modifies: cube
    // Effects: creates and replaces cube object with new randomly generated cube data values
    public void rerollCube() {
        for (int i = 0; i < 3; i++) {
            cubeNames.add(defaultCubeNames.get(getRandomListNumber()));
        }
        for (int i = 0; i < 3; i++) {
            cubeValues.add(defaultCubeValues.get(getRandomListNumber()));
        }
        removePreviousNames();
        removePreviousValues();
    }

    // Effects: Removes all previous cube names
    public void removePreviousNames() {
        cubeNames.remove(0);
        cubeNames.remove(1);
        cubeNames.remove(2);
    }

    // Effects: Removes all previous cube values
    public void removePreviousValues() {
        cubeValues.remove(0);
        cubeValues.remove(1);
        cubeValues.remove(2);
    }

}


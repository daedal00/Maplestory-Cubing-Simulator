package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Cubes class that stores 3 random cube names and cube values
public class Cubes {
    private final ArrayList<String> defaultCubeNames;
    private final ArrayList<Integer> defaultCubeValues;
    private final ArrayList<String> cubeNames;
    private final ArrayList<Integer> cubeValues;

    public Cubes() {
        defaultCubeNames = new ArrayList<>(
                Arrays.asList("Str",
                        "Dex",
                        "Luk",
                        "Int"));
        defaultCubeValues = new ArrayList<>(
                Arrays.asList(3,
                        6,
                        9,
                        12));
        cubeNames = new ArrayList<>();
        cubeValues = new ArrayList<>();
        setCubeName();
        setCubeValue();
    }

    // Requires:
    // Modifies:
    // Effects:
    public int getSize() {
        return cubeNames.size();
    }

    // Requires:
    // Modifies:
    // Effects:
    public int getRandomListNumber() {
        Random rand = new Random();
        int upperbound = 4;
        return rand.nextInt(upperbound - 1);
    }

    // Requires:
    // Modifies:
    // Effects:
    public void setCubeName() {
        for (int i = 0; i < 3; i++) {
            cubeNames.add(defaultCubeNames.get(getRandomListNumber()));
        }
    }

    // Requires:
    // Modifies:
    // Effects:
    public void setCubeValue() {
        for (int i = 0; i < 3; i++) {
            cubeValues.add(defaultCubeValues.get(getRandomListNumber()));
        }
    }

    // Requires:
    // Modifies:
    // Effects:
    public void printCube() {
        for (int i = 0; i < 3; i++) {
            System.out.println(cubeNames.get(i));
            System.out.println(cubeValues.get(i) + " %");
        }
    }

    // Requires:
    // Modifies:
    // Effects:
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

    // Requires:
    // Modifies:
    // Effects:
    public void removePreviousNames() {
        cubeNames.remove(0);
        cubeNames.remove(1);
        cubeNames.remove(2);
    }

    // Requires:
    // Modifies:
    // Effects:
    public void removePreviousValues() {
        cubeValues.remove(0);
        cubeValues.remove(1);
        cubeValues.remove(2);
    }
}


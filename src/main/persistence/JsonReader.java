package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import org.json.JSONObject;


import model.*;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list from JSON object and returns it
    private List parseList(JSONObject jsonObject) {
        JSONArray equipment = jsonObject.getJSONArray("equipment");
        JSONArray cube = jsonObject.getJSONArray("cube");
        List list = new List();
        addLists(list, equipment, cube);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses equipment and cube from JSON object and adds them to list
    private void addLists(List list, JSONArray jsonArrayEquipment, JSONArray jsonArrayCube) {
        for (Object json : jsonArrayEquipment) {
            JSONObject nextList = (JSONObject) json;
            addEquipmentToList(list, nextList);
        }
        for (Object json : jsonArrayCube) {
            JSONObject nextList = (JSONObject) json;
            addCubeToList(list, nextList);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses equipment from JSON object and adds it to list
    private void addEquipmentToList(List list, JSONObject jsonObject) {
        int equipmentStrength = jsonObject.getInt("Strength");
        int equipmentLuck = jsonObject.getInt("Luck");
        int equipmentIntelligence = jsonObject.getInt("Intelligence");
        int equipmentDexterity = jsonObject.getInt("Dexterity");
        String equipmentName = jsonObject.getString("Name");
        list.saveEquipment(new Equipment(equipmentName, equipmentStrength, equipmentIntelligence, equipmentLuck,
                +equipmentDexterity));

    }

    // MODIFIES: list
    // EFFECTS: parses cube form JSON object and adds to list
    public void addCubeToList(List list, JSONObject jsonObject) {
        Iterator<String> keys = jsonObject.keys();
        ArrayList<String> cubeName = new ArrayList<>();
        ArrayList<Integer> cubeValues = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();
            cubeName.add(key);
            int cubeValue = jsonObject.getInt(key);
            cubeValues.add(cubeValue);
        }
        Cubes cube = new Cubes(cubeName.get(0), cubeValues.get(0), cubeName.get(1), cubeValues.get(1), cubeName.get(2),
                cubeValues.get(2));
        list.saveCube(cube);
    }
}

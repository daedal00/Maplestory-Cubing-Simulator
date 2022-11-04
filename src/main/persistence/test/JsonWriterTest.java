package persistence.test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            model.List list = new model.List() {
            };
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyEquipmentList() {
        try {
            List list = new List();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEquipmentList.json");
            ArrayList<Equipment> equipment = new ArrayList<>();
            ArrayList<Cubes> cube = new ArrayList<>();
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEquipmentList.json");
            list = reader.read();
            assertEquals(0, list.getSize());
            assertEquals(0, equipment.size());
            assertEquals(0, cube.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



    @Test
    void testWriterGeneralEquipmentList() {
        try {
            List list = new List();
            Equipment equipment = new Equipment("helmet");
            Cubes cube = new Cubes();
            list.saveEquipment(equipment);
            list.saveCube(cube);
            list.toJson();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralEquipmentList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEquipmentList.json");
            list = reader.read();
            assertEquals("helmet", list.getEquipmentName());
            assertEquals(1, list.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

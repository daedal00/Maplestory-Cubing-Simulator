package persistence.test;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyEquipmentList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyEquipmentList.json");
        try {
            List list = reader.read();
            assertEquals(0, list.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEquipmentList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralEquipmentList.json");
        try {
            List list = reader.read();
            Equipment eq1 = new Equipment("helmet");
            Equipment eq2 = new Equipment("shoulder");
            list.saveEquipment(eq1);
            list.saveEquipment(eq2);
            assertEquals(3, list.getSize());
            checkEquipmentName("helmet", eq1.getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

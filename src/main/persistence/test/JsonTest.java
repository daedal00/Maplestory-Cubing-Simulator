package persistence.test;

import model.*;
import persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEquipmentName(String name, String equipment) {
        assertEquals(name, equipment);
    }

    protected void checkCube(String name, Cubes cube) {
        assertEquals(name, cube);
    }
}

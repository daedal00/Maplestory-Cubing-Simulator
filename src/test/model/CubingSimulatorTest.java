package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubingSimulatorTest {
    Cubes c1;
    Equipment e1;
    List eq1;
    Cubes c2;
    Equipment e2;

    @BeforeEach
    public void setUp(){
        c1 = new Cubes();
        e1 = new Equipment("shoulder");
        c2 = new Cubes();
        e2 = new Equipment("helmet");
        eq1 = new List();
    }

    @Test
    public void testCubeReroll(){
        assertEquals(3, c1.getSize());
        c1.rerollCube();
        assertEquals(3, c1.getSize());
        assertNotEquals(c1, c2);
    }

    @Test
    public void testFlameRoll(){
        Equipment e3;
        e3 = e1;
        assertEquals(e1, e3);
        e3 = new Equipment("shoulder");
        e1.rerollFlame();
        assertNotEquals(e1, e3);
    }


    @Test
    public void testEquipmentList(){
        eq1.saveCube(c1);
        eq1.saveEquipment(e1);
        assertEquals(eq1.getEquipmentName(), e1.getName());
        assertEquals(1, eq1.getSize());
        eq1.saveEquipment(e2);
        eq1.saveCube(c2);
        assertEquals(2, eq1.getSize());
    }

}
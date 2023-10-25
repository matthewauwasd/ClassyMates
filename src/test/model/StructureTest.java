package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StructureTest {

    private Structure structure;
    private Classroom classroom1;
    private Classroom classroom2;

    @BeforeEach
    void runBefore() {
        structure = new Structure();
        classroom1 = new Classroom("CA",1);
        classroom2 = new Classroom("CB",2);
    }

    @Test
    void testConstructor() {
        assertTrue(structure.getClassroomList().isEmpty());
    }

    @Test
    void testAddClassroom() {
        structure.addClassroom(classroom1);
        assertEquals(classroom1,structure.getClassroomList().get(0));
    }

    @Test
    void testAddClassroomTwice() {
        structure.addClassroom(classroom1);
        structure.addClassroom(classroom2);
        assertEquals(classroom1,structure.getClassroomList().get(0));
        assertEquals(classroom2,structure.getClassroomList().get(1));
    }

    @Test
    void testAddClassroomTwiceDifferentOrder() {
        structure.addClassroom(classroom2);
        structure.addClassroom(classroom1);
        assertEquals(classroom2,structure.getClassroomList().get(0));
        assertEquals(classroom1,structure.getClassroomList().get(1));
    }


}

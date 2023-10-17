package model.user;

import model.Classroom;
import model.Subgroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest extends UserTest {

    private Student studentA;
    private Student studentB;
    private Subgroup subgroup1;
    private Subgroup subgroup2;
    private Subgroup premadeSubgroup;
    private Subgroup premadeSubgroup2;
    private String subgroup1Name = "Sub1";
    private String subgroup2Name = "Sub2";

    @BeforeEach
    void runBefore() {
        classroom1 = new Classroom("CPSC 210", 210);
        classroom2 = new Classroom("CPSC 110", 110);
        studentA = new Student(student1username,student1password,studentType);
        student1 = studentA;
        studentB = new Student(student2username,student2password,studentType);
        student2 = studentB;
        instructor1 = new Instructor(instructor1username,instructor1password,instructorType);
        instructor2 = new Instructor(instructor2username,instructor2password,instructorType);
        premadeSubgroup = new Subgroup("Premade");
        premadeSubgroup2 = new Subgroup("Premade2");
    }

    @Test
    void testCreateSubgroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertTrue(subgroup1.getListOfStudents().isEmpty());
    }

    @Test
    void testCreateSubgroupTwice() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        subgroup2 = studentA.createSubgroup(subgroup2Name);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertTrue(subgroup1.getListOfStudents().isEmpty());
        assertEquals("Sub2",subgroup2.getSubgroupName());
        assertTrue(subgroup2.getSubgroupInterests().isEmpty());
        assertTrue(subgroup2.getMessages().isEmpty());
        assertTrue(subgroup2.getListOfStudents().isEmpty());
    }

    @Test
    void testJoinSubgroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        studentA.joinSubgroup(subgroup1);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertEquals(1,subgroup1.getListOfStudents().size());
        assertEquals(studentA,subgroup1.getListOfStudents().get(0));
    }

    @Test
    void testJoinSubgroupTwoDifferentSubgroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        subgroup2 = studentA.createSubgroup(subgroup2Name);
        studentB.joinSubgroup(subgroup1);
        studentB.joinSubgroup(subgroup2);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertEquals(1,subgroup1.getListOfStudents().size());
        assertEquals(studentB,subgroup1.getListOfStudents().get(0));
        assertEquals("Sub2",subgroup2.getSubgroupName());
        assertTrue(subgroup2.getSubgroupInterests().isEmpty());
        assertTrue(subgroup2.getMessages().isEmpty());
        assertEquals(1,subgroup2.getListOfStudents().size());
        assertEquals(studentB,subgroup2.getListOfStudents().get(0));
    }

    @Test
    void testLeaveSubgroup() {
        studentA.joinSubgroup(premadeSubgroup);
        studentA.leaveSubgroup(premadeSubgroup);
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getSubgroupInterests().isEmpty());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
    }

    @Test
    void testLeaveSubgroupTwoSubgroups() {
        studentA.joinSubgroup(premadeSubgroup);
        studentA.joinSubgroup(premadeSubgroup2);
        studentA.leaveSubgroup(premadeSubgroup);
        studentA.leaveSubgroup(premadeSubgroup2);
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getSubgroupInterests().isEmpty());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertEquals("Premade2",premadeSubgroup2.getSubgroupName());
        assertTrue(premadeSubgroup2.getSubgroupInterests().isEmpty());
        assertTrue(premadeSubgroup2.getMessages().isEmpty());
        assertTrue(premadeSubgroup2.getListOfStudents().isEmpty());
    }

    // CREATE TESTS FOR CREATEMESSAGE METHOD

    @Test
    void testLeaveClassroomStudent() {
        studentA.joinClassroom(classroom1);
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomTwoClassroomStudent() {
        studentB.joinClassroom(classroom1);
        studentB.joinClassroom(classroom2);
        studentB.leaveClassroom(classroom1);
        studentB.leaveClassroom(classroom2);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(classroom2.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomOneStudentLeaveOneStudentRemaining() {
        studentA.joinClassroom(classroom1);
        studentB.joinClassroom(classroom1);
        studentA.leaveClassroom(classroom1);
        assertEquals(studentB,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

    @Test
    void testLeaveClassroomStudentInSubgroup() {
        classroom1.addSubgroup(subgroup1);
        studentA.joinClassroom(classroom1);
        studentA.joinSubgroup(subgroup1);
        studentA.leaveClassroom(classroom1);
        assertTrue(subgroup1.getListOfStudents().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentInTwoSubgroup() {
        classroom1.addSubgroup(subgroup1);
        classroom1.addSubgroup(subgroup2);
        studentA.joinClassroom(classroom1);
        studentA.joinSubgroup(subgroup1);
        studentA.joinSubgroup(subgroup2);
        studentA.leaveClassroom(classroom1);
        assertTrue(subgroup1.getListOfStudents().isEmpty());
        assertTrue(subgroup2.getListOfStudents().isEmpty());
    }

}

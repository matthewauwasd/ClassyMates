package model.user;

import model.Classroom;
import model.Message;
import model.Subgroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest extends UserTest {

    private Student studentA;
    private Student studentB;
    private Subgroup subgroup1;
    private Subgroup subgroup2;
    private List<Subgroup> subgroupList = new ArrayList<Subgroup>();
    private Subgroup premadeSubgroup;
    private Subgroup premadeSubgroup2;
    private String subgroup1Name = "Sub1";
    private String subgroup2Name = "Sub2";
    private String messageBody1 = "Message1";
    private String messageBody2 = "Message2";

    @BeforeEach
    void runBefore() {
        classroom1 = new Classroom("CPSC 210", 210);
        classroom2 = new Classroom("CPSC 110", 110);
        studentA = new Student(student1username,student1password,studentType);
        student1 = studentA;
        studentB = new Student(student2username,student2password,studentType);
        student2 = studentB;
        premadeSubgroup = new Subgroup("Premade");
        premadeSubgroup2 = new Subgroup("Premade2");
        subgroupList.add(premadeSubgroup);
        subgroupList.add(premadeSubgroup2);
    }

    @Test
    void testConstructor() {
        assertEquals("Student1",studentA.getUsername());
        assertEquals("Password1",studentA.getPassword());
        assertEquals("Student",studentA.getUserType());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testCreateSubgroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertTrue(subgroup1.getListOfStudents().isEmpty());
    }

    @Test
    void testCreateSubgroupTwice() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        subgroup2 = studentA.createSubgroup(subgroup2Name);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertTrue(subgroup1.getListOfStudents().isEmpty());
        assertEquals("Sub2",subgroup2.getSubgroupName());
        assertTrue(subgroup2.getMessages().isEmpty());
        assertTrue(subgroup2.getListOfStudents().isEmpty());
    }

    @Test
    void testJoinSubgroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        studentA.joinSubgroup(subgroup1);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertEquals(1,subgroup1.getListOfStudents().size());
        assertEquals(studentA,subgroup1.getListOfStudents().get(0));
        assertEquals(subgroup1,studentA.getSubgroupsJoined().get(0));
    }

    @Test
    void testJoinSubgroupSameGroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        studentA.joinSubgroup(subgroup1);
        studentA.joinSubgroup(subgroup1);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertEquals(1,subgroup1.getListOfStudents().size());
        assertEquals(studentA,subgroup1.getListOfStudents().get(0));
        assertEquals(subgroup1,studentA.getSubgroupsJoined().get(0));
    }

    @Test
    void testJoinSubgroupTwoDifferentSubgroup() {
        subgroup1 = studentA.createSubgroup(subgroup1Name);
        subgroup2 = studentA.createSubgroup(subgroup2Name);
        studentB.joinSubgroup(subgroup1);
        studentB.joinSubgroup(subgroup2);
        assertEquals("Sub1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertEquals(1,subgroup1.getListOfStudents().size());
        assertEquals(studentB,subgroup1.getListOfStudents().get(0));
        assertEquals("Sub2",subgroup2.getSubgroupName());
        assertTrue(subgroup2.getMessages().isEmpty());
        assertEquals(1,subgroup2.getListOfStudents().size());
        assertEquals(studentB,subgroup2.getListOfStudents().get(0));
        assertEquals(subgroup1,studentB.getSubgroupsJoined().get(0));
        assertEquals(subgroup2,studentB.getSubgroupsJoined().get(1));
    }

    @Test
    void testLeaveSubgroup() {
        studentA.joinSubgroup(premadeSubgroup);
        studentA.leaveSubgroup(subgroupList, "Premade");
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveSubgroupTwoSubgroups() {
        studentA.joinSubgroup(premadeSubgroup);
        studentA.joinSubgroup(premadeSubgroup2);
        studentA.leaveSubgroup(subgroupList,"Premade");
        studentA.leaveSubgroup(subgroupList,"Premade2");
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertEquals("Premade2",premadeSubgroup2.getSubgroupName());
        assertTrue(premadeSubgroup2.getMessages().isEmpty());
        assertTrue(premadeSubgroup2.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveSubgroupJoinTwoSubgroupsLeaveOne() {
        studentA.joinSubgroup(premadeSubgroup);
        studentA.joinSubgroup(premadeSubgroup2);
        studentA.leaveSubgroup(subgroupList,"Premade");
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertEquals("Premade2",premadeSubgroup2.getSubgroupName());
        assertTrue(premadeSubgroup2.getMessages().isEmpty());
        assertEquals(1,premadeSubgroup2.getListOfStudents().size());
        assertEquals(premadeSubgroup2,studentA.getSubgroupsJoined().get(0));
    }

    @Test
    void testLeaveSubgroupNotJoined() {
        studentA.leaveSubgroup(subgroupList, "Premade");
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveSubgroupWrongName() {
        studentA.joinSubgroup(premadeSubgroup);
        studentA.leaveSubgroup(subgroupList, "WRONG");
        assertEquals("Premade",premadeSubgroup.getSubgroupName());
        assertTrue(premadeSubgroup.getMessages().isEmpty());
        assertEquals(1,premadeSubgroup.getListOfStudents().size());
        assertEquals(premadeSubgroup,studentA.getSubgroupsJoined().get(0));
    }

    @Test
    void testCreateMessage() {
        Message newMessage = studentA.createMessage(messageBody1);
        assertEquals("Student1",newMessage.getUserWhoPosted());
        assertEquals("Message1",newMessage.getMessageBody());
    }

    @Test
    void testCreateMessageTwice() {
        Message newMessage = studentA.createMessage(messageBody1);
        Message newMessage2 = studentA.createMessage(messageBody2);
        assertEquals("Student1",newMessage.getUserWhoPosted());
        assertEquals("Message1",newMessage.getMessageBody());
        assertEquals("Student1",newMessage2.getUserWhoPosted());
        assertEquals("Message2",newMessage2.getMessageBody());
    }

    @Test
    void testDeleteMessage() {
        Message newMessage = studentA.createMessage(messageBody1);
        premadeSubgroup.addMessage(newMessage);
        studentA.deleteMessage(premadeSubgroup,"Message1");
        assertTrue(premadeSubgroup.getMessages().isEmpty());
    }

    @Test
    void testDeleteMessageTwice() {
        Message newMessage = studentA.createMessage(messageBody1);
        Message newMessage2 = studentA.createMessage(messageBody2);
        premadeSubgroup.addMessage(newMessage);
        premadeSubgroup.addMessage(newMessage2);
        studentA.deleteMessage(premadeSubgroup,"Message1");
        studentA.deleteMessage(premadeSubgroup,"Message2");
        assertTrue(premadeSubgroup.getMessages().isEmpty());
    }

    @Test
    void testDeleteMessageDifferentUser() {
        Message newMessage = studentA.createMessage(messageBody1);
        premadeSubgroup.addMessage(newMessage);
        studentB.deleteMessage(premadeSubgroup,"Message1");
        assertEquals(newMessage,premadeSubgroup.getMessages().get(0));
    }

    @Test
    void testDeleteMessageDifferentUserWrongMessage() {
        Message newMessage = studentA.createMessage(messageBody1);
        premadeSubgroup.addMessage(newMessage);
        studentA.deleteMessage(premadeSubgroup,"WrongMessage");
        assertEquals(newMessage,premadeSubgroup.getMessages().get(0));
    }

    @Test
    void testDeleteMessageWrongMessage() {
        Message newMessage = studentA.createMessage(messageBody1);
        premadeSubgroup.addMessage(newMessage);
        studentA.deleteMessage(premadeSubgroup,"WrongMessage");
        assertEquals(newMessage,premadeSubgroup.getMessages().get(0));
    }

    @Test
    void testDeleteMessageNoMessages() {
        studentA.deleteMessage(premadeSubgroup,"WrongMessage");
        assertTrue(premadeSubgroup.getMessages().isEmpty());
    }

    @Test
    void testLeaveClassroomStudent() {
        studentA.joinClassroom(classroom1);
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentNotInClass() {
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomTwoClassroomStudent() {
        studentB.joinClassroom(classroom1);
        studentB.joinClassroom(classroom2);
        studentB.leaveClassroom(classroom1);
        studentB.leaveClassroom(classroom2);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(classroom2.getListOfUsers().isEmpty());
        assertTrue(studentB.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomOneStudentLeaveOneStudentRemaining() {
        studentA.joinClassroom(classroom1);
        studentB.joinClassroom(classroom1);
        studentA.leaveClassroom(classroom1);
        assertEquals(studentB,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
        assertTrue(studentB.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentInSubgroup() {
        classroom1.addSubgroup(premadeSubgroup);
        studentA.joinClassroom(classroom1);
        studentA.joinSubgroup(premadeSubgroup);
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentInTwoSubgroup() {
        classroom1.addSubgroup(premadeSubgroup);
        classroom1.addSubgroup(premadeSubgroup2);
        studentA.joinClassroom(classroom1);
        studentA.joinSubgroup(premadeSubgroup);
        studentA.joinSubgroup(premadeSubgroup2);
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertTrue(premadeSubgroup2.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentNotInSubgroup() {
        classroom1.addSubgroup(premadeSubgroup);
        studentA.joinClassroom(classroom1);
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentNotInMultipleSubgroup() {
        classroom1.addSubgroup(premadeSubgroup);
        classroom1.addSubgroup(premadeSubgroup2);
        studentA.joinClassroom(classroom1);
        studentA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(premadeSubgroup.getListOfStudents().isEmpty());
        assertTrue(premadeSubgroup2.getListOfStudents().isEmpty());
        assertTrue(studentA.getSubgroupsJoined().isEmpty());
    }

}

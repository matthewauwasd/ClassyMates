package model.user;

import model.Classroom;
import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorTest extends UserTest {

    List<Classroom> classroomList = new ArrayList<Classroom>();
    Instructor instructorA;
    Instructor instructorB;
    Post post1;
    Post post2;

    @BeforeEach
    void runBefore() {
        instructorA = new Instructor(instructor1username,instructor1password,instructorType);
        instructor1 = instructorA;
        instructorB = new Instructor(instructor2username,instructor2password,instructorType);
        instructor2 = instructorB;
        student1 = new Student(student1username,student1password,studentType);
        student2 = new Student(student2username,student2password,studentType);
        classroom1 = new Classroom("CPSC 210", 210);
        classroom2 = new Classroom("CPSC 110", 110);
        post1 = new Post(instructorA.getUsername(),"Post1","Body1");
        post2 = new Post(instructorA.getUsername(),"Post2","Body2");
    }

    @Test
    void testConstructor() {
        assertEquals(instructor1username,instructorA.getUsername());
        assertEquals(instructor1password,instructorA.getPassword());
        assertEquals(instructorType,instructorA.getUserType());
    }

    @Test
    void testCreateClassroom() {
        instructorA.createClassroom(classroomList,"CPSC 210",210);
        assertEquals("CPSC 210",classroomList.get(0).getCourseName());
        assertEquals(210,classroomList.get(0).getCourseID());
    }

    @Test
    void testCreateClassroomTwice() {
        instructorA.createClassroom(classroomList,"CPSC 210",210);
        instructorA.createClassroom(classroomList,"CPSC 110",110);
        assertEquals("CPSC 210",classroomList.get(0).getCourseName());
        assertEquals("CPSC 110",classroomList.get(1).getCourseName());
        assertEquals(210,classroomList.get(0).getCourseID());
        assertEquals(110,classroomList.get(1).getCourseID());
    }

    @Test
    void testDeletePost() {
        classroom1.addPost(post1);
        instructorA.deletePost(classroom1,"Post1");
        assertTrue(classroom1.getPosts().isEmpty());
    }

    @Test
    void testDeletePostTwice() {
        classroom1.addPost(post1);
        classroom1.addPost(post2);
        instructorA.deletePost(classroom1,"Post1");
        instructorA.deletePost(classroom1,"Post2");
        assertTrue(classroom1.getPosts().isEmpty());
    }

    @Test
    void testLeaveClassroomInstructor() {
        instructorA.joinClassroom(classroom1);
        instructorA.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomTwoClassroomInstructor() {
        instructorB.joinClassroom(classroom1);
        instructorB.joinClassroom(classroom2);
        instructorB.leaveClassroom(classroom1);
        instructorB.leaveClassroom(classroom2);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(classroom2.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomOneInstructorLeaveOneInstructorRemaining() {
        instructorA.joinClassroom(classroom1);
        instructorB.joinClassroom(classroom1);
        instructorA.leaveClassroom(classroom1);
        assertEquals(instructorB,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

}

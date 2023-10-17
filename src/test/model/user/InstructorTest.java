package model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorTest extends UserTest {

    @BeforeEach
    void runBefore() {
        instructor1 = new Instructor(instructor1username,instructor1password,instructorType);
        instructor2 = new Instructor(instructor2username,instructor2password,instructorType);
    }

    @Test
    void testConstructor() {
        assertEquals(instructor1username,instructor1.getUsername());
        assertEquals(instructor1password,instructor1.getPassword());
        assertEquals(instructorType,instructor1.getUserType());
    }

    @Test
    void testLeaveClassroomInstructor() {
        instructor1.joinClassroom(classroom1);
        instructor1.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomTwoClassroomInstructor() {
        instructor2.joinClassroom(classroom1);
        instructor2.joinClassroom(classroom2);
        instructor2.leaveClassroom(classroom1);
        instructor2.leaveClassroom(classroom2);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(classroom2.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomOneInstructorLeaveOneInstructorRemaining() {
        instructor1.joinClassroom(classroom1);
        instructor2.joinClassroom(classroom1);
        instructor1.leaveClassroom(classroom1);
        assertEquals(instructor2,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

}

package model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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



}

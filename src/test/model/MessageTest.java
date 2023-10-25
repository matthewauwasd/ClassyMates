package model;

import model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    private Student student1;
    private Message message1;
    private Message message2;
    private String message1Body = "I'm a mess mess mess";
    private String message2Body = "Message";

    @BeforeEach
    void runBefore() {
        student1 = new Student("Student1","1","Student");
        message1 = new Message(student1.getUsername(),message1Body);
    }

    @Test
    void testConstructor() {
        assertEquals("Student1",message1.getUserWhoPosted());
        assertEquals("I'm a mess mess mess",message1.getMessageBody());
    }
}

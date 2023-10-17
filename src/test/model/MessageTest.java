package model;

import model.user.Instructor;
import model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    private Student student1;
    private Instructor instructor1;
    private Message message1;
    private Message message2;
    private String message1Body = "I'm a mess mess mess";
    private String message2Body = "Message";

    @BeforeEach
    void runBefore() {
        student1 = new Student("Student1","1","Student");
        instructor1 = new Instructor("Instructor1","No","Instructor");
        message1 = new Message(student1.getUsername(),message1Body);
        message2 = new Message(instructor1.getUsername(),message2Body);
    }

    @Test
    void testConstructor() {
        assertEquals("Student1",message1.getUserWhoPosted());
        assertEquals("I'm a mess mess mess",message1.getMessageBody());
        assertEquals("Instructor1",message2.getUserWhoPosted());
        assertEquals("Message",message2.getMessageBody());
    }
}

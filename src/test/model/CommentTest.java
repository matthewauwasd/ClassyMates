package model;

import model.user.Instructor;
import model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {
    private Student student1;
    private Instructor instructor1;
    private Comment comment1;
    private Comment comment2;
    private String comment1Body = "I'm a comment";
    private String comment2Body = "Lol";

    @BeforeEach
    void runBefore() {
        student1 = new Student("Student1","1","Student");
        instructor1 = new Instructor("Instructor1","No","Instructor");
        comment1 = new Comment(student1.getUsername(),comment1Body);
        comment2 = new Comment(instructor1.getUsername(),comment2Body);
    }

    @Test
    void testConstructor() {
        assertEquals("Student1",comment1.getUserWhoPosted());
        assertEquals("I'm a comment",comment1.getCommentBody());
        assertEquals("Instructor1",comment2.getUserWhoPosted());
        assertEquals("Lol",comment2.getCommentBody());
    }

}

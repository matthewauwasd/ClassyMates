package model;

import model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {
    private Student student1;
    private Comment comment1;
    private Comment comment2;
    private String comment1Body = "I'm a comment";
    private String comment2Body = "Lol";

    @BeforeEach
    void runBefore() {
        student1 = new Student("Student1","1","Student");
        comment1 = new Comment(student1.getUsername(),comment1Body);
    }

    @Test
    void testConstructor() {
        assertEquals("Student1",comment1.getUserWhoPosted());
        assertEquals("I'm a comment",comment1.getCommentBody());
    }

}

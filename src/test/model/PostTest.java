package model;

import model.user.Instructor;
import model.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostTest {
    private Student student1;
    private Student student2;
    private Student student3;
    private Instructor instructor1;
    private Post post1;
    private String post1Title = "Question Title?";
    private String post1Body = "I have a question...";
    private Comment comment1;
    private String comment1Body = "I do not know the answer.";
    private Comment comment2;
    private String comment2Body = "The answer is...";

    @BeforeEach
    void runBefore() {
        instructor1 = new Instructor("Instructor1","12345","Instructor");
        student1 = new Student("Student1","678910","Student");
        student2 = new Student("Student2","Hey","Student");
        student3 = new Student("Student3","Bye","Student");
        post1 = new Post(student1.getUsername(),post1Title,post1Body);
        comment1 = new Comment(student2.getUsername(),comment1Body);
        comment2 = new Comment(student3.getUsername(),comment2Body);
    }

    @Test
    void testConstructor() {
        assertEquals("Student1",post1.getUserWhoPosted());
        assertEquals("Question Title?",post1.getPostTitle());
        assertEquals("I have a question...",post1.getPostBody());
        List<Comment> commentList = post1.getComments();
        assertTrue(commentList.isEmpty());
    }

    @Test
    void testAddComment() {
        post1.addComment(comment1);
        List<Comment> commentList = post1.getComments();
        assertEquals(comment1,commentList.get(0));
        assertEquals("Student2",commentList.get(0).getUserWhoPosted());
        assertEquals("I do not know the answer.",commentList.get(0).getCommentBody());
        assertEquals(1,commentList.size());
    }

    @Test
    void testAddTwoComments() {
        post1.addComment(comment1);
        post1.addComment(comment2);
        List<Comment> commentList = post1.getComments();
        assertEquals(2,commentList.size());
        assertEquals(comment1,commentList.get(0));
        assertEquals("Student2",commentList.get(0).getUserWhoPosted());
        assertEquals("I do not know the answer.",commentList.get(0).getCommentBody());
        assertEquals(comment2,commentList.get(1));
        assertEquals("Student3",commentList.get(1).getUserWhoPosted());
        assertEquals("The answer is...",commentList.get(1).getCommentBody());
    }

    @Test
    void testAddTwoCommentsInDifferentOrder() {
        post1.addComment(comment2);
        post1.addComment(comment1);
        List<Comment> commentList = post1.getComments();
        assertEquals(2,commentList.size());
        assertEquals(comment2,commentList.get(0));
        assertEquals("Student3",commentList.get(0).getUserWhoPosted());
        assertEquals("The answer is...",commentList.get(0).getCommentBody());
        assertEquals(comment1,commentList.get(1));
        assertEquals("Student2",commentList.get(1).getUserWhoPosted());
        assertEquals("I do not know the answer.",commentList.get(1).getCommentBody());
    }

}

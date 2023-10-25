package model.user;

import model.Classroom;
import model.Comment;
import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    protected User student1;
    protected User student2;
    protected String student1username = "Student1";
    protected String student2username = "Student2";
    protected String student1password = "Password1";
    protected String student2password = "Password2";
    protected String studentType = "Student";
    protected Classroom classroom1;
    protected Classroom classroom2;

    @BeforeEach
    void runBefore() {
        classroom1 = new Classroom("CPSC 210", 210);
        classroom2 = new Classroom("CPSC 110", 110);
        student1 = new Student(student1username,student1password,studentType);
        student2 = new Student(student2username,student2password,studentType);
    }

    @Test
    void testStudentConstructor() {
        assertEquals("Student1",student1.getUsername());
        assertEquals("Password1",student1.getPassword());
        assertEquals("Student",student1.getUserType());
    }

    @Test
    void testCreatePostStudent() {
        Post newPost = student1.createPost("Post Title", "Post Body");
        assertEquals("Student1",newPost.getUserWhoPosted());
        assertEquals("Post Title",newPost.getPostTitle());
        assertEquals("Post Body",newPost.getPostBody());
    }

    @Test
    void testCreatePostMultipleSameStudent() {
        Post newPost1 = student1.createPost("Post Title1", "Post Body1");
        Post newPost2 = student1.createPost("Post Title2", "Post Body2");
        assertEquals("Student1",newPost1.getUserWhoPosted());
        assertEquals("Post Title1",newPost1.getPostTitle());
        assertEquals("Post Body1",newPost1.getPostBody());
        assertEquals("Student1",newPost2.getUserWhoPosted());
        assertEquals("Post Title2",newPost2.getPostTitle());
        assertEquals("Post Body2",newPost2.getPostBody());
    }

    @Test
    void testCreateCommentStudent() {
        Comment newComment = student2.createComment("Comment body");
        assertEquals("Student2",newComment.getUserWhoPosted());
        assertEquals("Comment body",newComment.getCommentBody());
    }

    @Test
    void testCreateCommentMultipleSameStudent() {
        Comment newComment1 = student1.createComment("Comment body 1");
        Comment newComment2 = student1.createComment("Comment body 2");
        assertEquals("Student1",newComment1.getUserWhoPosted());
        assertEquals("Comment body 1",newComment1.getCommentBody());
        assertEquals("Student1",newComment2.getUserWhoPosted());
        assertEquals("Comment body 2",newComment2.getCommentBody());
    }

    @Test
    void testJoinClassroomStudent() {
        student1.joinClassroom(classroom1);
        assertEquals(student1,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

    @Test
    void testJoinClassroomStudentAlreadyIn() {
        student1.joinClassroom(classroom1);
        student1.joinClassroom(classroom1);
        assertEquals(student1,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

    @Test
    void testJoinClassroomTwoClassroomStudent() {
        student1.joinClassroom(classroom1);
        assertEquals(student1,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
        student1.joinClassroom(classroom2);
        assertEquals(student1,classroom2.getListOfUsers().get(0));
        assertEquals(1,classroom2.getListOfUsers().size());
    }

    @Test
    void testJoinClassroomTwoStudentSameClassroom() {
        student1.joinClassroom(classroom1);
        student2.joinClassroom(classroom1);
        assertEquals(student1,classroom1.getListOfUsers().get(0));
        assertEquals(student2,classroom1.getListOfUsers().get(1));
        assertEquals(2,classroom1.getListOfUsers().size());
    }

}

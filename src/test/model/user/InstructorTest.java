package model.user;

import model.Classroom;
import model.Comment;
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
    Comment comment1;
    Comment comment2;

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
        comment1 = new Comment(instructorA.getUsername(),"Comment1");
        comment2 = new Comment(instructorA.getUsername(),"Comment2");
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
    void testDeletePostNoPosts() {
        instructorA.deletePost(classroom1,"Post1");
        assertTrue(classroom1.getPosts().isEmpty());
    }

    @Test
    void testDeletePostNoPostsTwice() {
        instructorA.deletePost(classroom1,"Post1");
        instructorA.deletePost(classroom1,"Post1");
        assertTrue(classroom1.getPosts().isEmpty());
    }

    @Test
    void testDeletePostNoPostsWrongPost() {
        instructorA.deletePost(classroom1,"PostNO");
        assertTrue(classroom1.getPosts().isEmpty());
    }

    @Test
    void testDeletePostNoPostsWrongPostTwice() {
        instructorA.deletePost(classroom1,"PostNO");
        instructorA.deletePost(classroom1,"PostNB");
        assertTrue(classroom1.getPosts().isEmpty());
    }

    @Test
    void testDeletePostWrongPost() {
        classroom1.addPost(post1);
        instructorA.deletePost(classroom1,"PostNO");
        assertEquals(post1,classroom1.getPosts().get(0));
    }

    @Test
    void testDeletePostWrongPostTwice() {
        classroom1.addPost(post1);
        instructorA.deletePost(classroom1,"PostNO");
        instructorA.deletePost(classroom1,"PostNB");
        assertEquals(post1,classroom1.getPosts().get(0));
    }

    @Test
    void testDeleteComment() {
        post1.addComment(comment1);
        instructorA.deleteComment(post1,"Comment1");
        assertTrue(post1.getComments().isEmpty());
    }

    @Test
    void testDeleteCommentTwice() {
        post1.addComment(comment1);
        post1.addComment(comment2);
        instructorA.deleteComment(post1,"Comment1");
        instructorA.deleteComment(post1,"Comment2");
        assertTrue(post1.getComments().isEmpty());
    }

    @Test
    void testDeleteCommentNoComments() {
        instructorA.deleteComment(post1,"Comment1");
        assertTrue(post1.getComments().isEmpty());
    }

    @Test
    void testDeleteCommentNoCommentsTwice() {
        instructorA.deleteComment(post1,"Comment1");
        instructorA.deleteComment(post1,"Comment1");
        assertTrue(post1.getComments().isEmpty());
    }

    @Test
    void testDeleteCommentNoCommentsWrongComment() {
        instructorA.deleteComment(post1,"CommentNO");
        assertTrue(post1.getComments().isEmpty());
    }

    @Test
    void testDeleteCommentNoCommentsWrongCommentTwice() {
        instructorA.deleteComment(post1,"CommentNO");
        instructorA.deleteComment(post1,"CommentNB");
        assertTrue(post1.getComments().isEmpty());
    }

    @Test
    void testDeleteCommentWrongComment() {
        post1.addComment(comment1);
        instructorA.deleteComment(post1,"CommentNO");
        assertEquals(comment1,post1.getComments().get(0));
    }

    @Test
    void testDeleteCommentWrongCommentTwice() {
        post1.addComment(comment1);
        instructorA.deleteComment(post1,"CommentNO");
        instructorA.deleteComment(post1,"CommentNB");
        assertEquals(comment1,post1.getComments().get(0));
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

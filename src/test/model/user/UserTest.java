package model.user;

import model.Classroom;
import model.Comment;
import model.Post;
import model.Subgroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private User student1;
    private User student2;
    protected User instructor1;
    protected User instructor2;
    private String student1username = "Student1";
    private String student2username = "Student2";
    protected String instructor1username = "Instructor1";
    protected String instructor2username = "Instructor2";
    private String student1password = "Password1";
    private String student2password = "Password2";
    protected String instructor1password = "Teacher1";
    protected String instructor2password = "Teacher2";
    private String studentType = "Student";
    protected String instructorType = "Instructor";
    private Classroom classroom1;
    private Classroom classroom2;
    private Subgroup subgroup1;
    private Subgroup subgroup2;

    @BeforeEach
    void runBefore() {
        classroom1 = new Classroom("CPSC 210", 210);
        classroom2 = new Classroom("CPSC 110", 110);
        subgroup1 = new Subgroup("Subgroup1");
        subgroup2 = new Subgroup("Subgroup2");
        student1 = new User(student1username,student1password,studentType);
        student2 = new User(student2username,student2password,studentType);
        instructor1 = new User(instructor1username,instructor1password,instructorType);
        instructor2 = new User(instructor2username,instructor2password,instructorType);
    }

    @Test
    void testStudentConstructor() {
        assertEquals("Student1",student1.getUsername());
        assertEquals("Password1",student1.getPassword());
        assertEquals("Student",student1.getUserType());
    }

    @Test
    void testInstructorConstructor() {
        assertEquals("Instructor1",instructor1.getUsername());
        assertEquals("Teacher1",instructor1.getPassword());
        assertEquals("Instructor",instructor1.getUserType());
    }

    @Test
    void testCreatePostStudent() {
        Post newPost = student1.createPost("Post Title", "Post Body");
        assertEquals("Student1",newPost.getUserWhoPosted());
        assertEquals("Post Title",newPost.getPostTitle());
        assertEquals("Post Body",newPost.getPostBody());
    }

    @Test
    void testCreatePostInstructor() {
        Post newPost = instructor1.createPost("Post Title", "Post Body");
        assertEquals("Instructor1",newPost.getUserWhoPosted());
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
    void testCreatePostMultipleSameInstructor() {
        Post newPost1 = instructor1.createPost("Post Title1", "Post Body1");
        Post newPost2 = instructor1.createPost("Post Title2", "Post Body2");
        assertEquals("Instructor1",newPost1.getUserWhoPosted());
        assertEquals("Post Title1",newPost1.getPostTitle());
        assertEquals("Post Body1",newPost1.getPostBody());
        assertEquals("Instructor1",newPost2.getUserWhoPosted());
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
    void testCreateCommentInstructor() {
        Comment newComment = instructor2.createComment("Comment body");
        assertEquals("Instructor2",newComment.getUserWhoPosted());
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
    void testCreateCommentMultipleSameInstructor() {
        Comment newComment1 = instructor1.createComment("Comment body 1");
        Comment newComment2 = instructor1.createComment("Comment body 2");
        assertEquals("Instructor1",newComment1.getUserWhoPosted());
        assertEquals("Comment body 1",newComment1.getCommentBody());
        assertEquals("Instructor1",newComment2.getUserWhoPosted());
        assertEquals("Comment body 2",newComment2.getCommentBody());
    }

    @Test
    void testJoinClassroomStudent() {
        student1.joinClassroom(classroom1);
        assertEquals(student1,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

    @Test
    void testJoinClassroomInstructor() {
        instructor1.joinClassroom(classroom1);
        assertEquals(instructor1,classroom1.getListOfUsers().get(0));
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
    void testJoinClassroomTwoClassroomInstructor() {
        instructor1.joinClassroom(classroom1);
        assertEquals(instructor1,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
        instructor1.joinClassroom(classroom2);
        assertEquals(instructor1,classroom2.getListOfUsers().get(0));
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

    @Test
    void testJoinClassroomTwoInstructorSameClassroom() {
        instructor1.joinClassroom(classroom1);
        instructor2.joinClassroom(classroom1);
        assertEquals(instructor1,classroom1.getListOfUsers().get(0));
        assertEquals(instructor2,classroom1.getListOfUsers().get(1));
        assertEquals(2,classroom1.getListOfUsers().size());
    }

    @Test
    void testLeaveClassroomStudent() {
        student1.joinClassroom(classroom1);
        student1.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomInstructor() {
        instructor1.joinClassroom(classroom1);
        instructor1.leaveClassroom(classroom1);
        assertTrue(classroom1.getListOfUsers().isEmpty());
    }

    @Test
    void testLeaveClassroomTwoClassroomStudent() {
        student2.joinClassroom(classroom1);
        student2.joinClassroom(classroom2);
        student2.leaveClassroom(classroom1);
        student2.leaveClassroom(classroom2);
        assertTrue(classroom1.getListOfUsers().isEmpty());
        assertTrue(classroom2.getListOfUsers().isEmpty());
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
    void testLeaveClassroomOneStudentLeaveOneStudentRemaining() {
        student1.joinClassroom(classroom1);
        student2.joinClassroom(classroom1);
        student1.leaveClassroom(classroom1);
        assertEquals(student2,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

    @Test
    void testLeaveClassroomOneInstructorLeaveOneInstructorRemaining() {
        instructor1.joinClassroom(classroom1);
        instructor2.joinClassroom(classroom1);
        instructor1.leaveClassroom(classroom1);
        assertEquals(instructor2,classroom1.getListOfUsers().get(0));
        assertEquals(1,classroom1.getListOfUsers().size());
    }

    @Test
    void testLeaveClassroomStudentInSubgroup() {
        classroom1.addSubgroup(subgroup1);
        student1.joinClassroom(classroom1);
        student1.joinSubgroup(subgroup1);
        student1.leaveClassroom(classroom1);
        assertTrue(subgroup1.getListOfStudents().isEmpty());
    }

    @Test
    void testLeaveClassroomStudentInTwoSubgroup() {
        classroom1.addSubgroup(subgroup1);
        classroom1.addSubgroup(subgroup2);
        student1.joinClassroom(classroom1);
        student1.joinSubgroup(subgroup1);
        student1.joinSubgroup(subgroup2);
        student1.leaveClassroom(classroom1);
        assertTrue(subgroup1.getListOfStudents().isEmpty());
        assertTrue(subgroup2.getListOfStudents().isEmpty());
    }

}

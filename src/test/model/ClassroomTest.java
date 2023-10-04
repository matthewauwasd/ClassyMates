package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassroomTest {
    private Classroom course1;
    private String course1Name = "CPSC 210";
    private int course1ID = 210;
    private Post post1;
    private String post1Title = "HELP";
    private String post1Body= "I NEED HELP";
    private Post post2;
    private String post2Title = "Question 3";
    private String post2Body= "How do you do this?";

    @BeforeEach
    void runBefore() {
        course1 = new Classroom(course1Name,course1ID);
        post1 = new Post(post1Title,post1Body);
        post2 = new Post(post2Title,post2Body);
    }

    @Test
    void testConstructor() {
        assertEquals("CPSC 210",course1.getCourseName());
        assertEquals(210,course1.getCourseID());
        List<Post> postList = course1.getPosts();
        assertTrue(postList.isEmpty());
    }

    @Test
    void testAddPost() {
        course1.addPost(post1);
        List<Post> postList = course1.getPosts();
        assertEquals(1,postList.size());
        assertEquals(post1,postList.get(0));
    }

    @Test
    void testAddTwoPosts() {
        course1.addPost(post1);
        course1.addPost(post2);
        List<Post> postList = course1.getPosts();
        assertEquals(2,postList.size());
        assertEquals(post1,postList.get(0));
        assertEquals(post2,postList.get(1));
    }

    @Test
    void testAddTwoPostsAddedInDifferentOrder() {
        course1.addPost(post2);
        course1.addPost(post1);
        List<Post> postList = course1.getPosts();
        assertEquals(2,postList.size());
        assertEquals(post2,postList.get(0));
        assertEquals(post1,postList.get(1));
    }

}
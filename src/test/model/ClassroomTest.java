package model;

import model.user.Student;
import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    private Student student1;
    private Subgroup subgroup1;
    private Subgroup subgroup2;

    @BeforeEach
    void runBefore() {
        student1 = new Student("Gage","Waltuh","Student");
        course1 = new Classroom(course1Name,course1ID);
        post1 = new Post(student1.getUsername(),post1Title,post1Body);
        subgroup1 = new Subgroup("The Bois");
        subgroup2 = new Subgroup("Flowers");
    }

    @Test
    void testConstructor() {
        assertEquals("CPSC 210",course1.getCourseName());
        assertEquals(210,course1.getCourseID());
        List<User> userList = course1.getListOfUsers();
        assertTrue(userList.isEmpty());
        List<Post> postList = course1.getPosts();
        assertTrue(postList.isEmpty());
        List<Subgroup> subgroupList = course1.getSubgroups();
        assertTrue(subgroupList.isEmpty());
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

    @Test
    void testAddSubgroup() {
        course1.addSubgroup(subgroup1);
        List<Subgroup> subgroupList = course1.getSubgroups();
        assertEquals(1,subgroupList.size());
        assertEquals(subgroup1,subgroupList.get(0));
    }

    @Test
    void testAddTwoSubgroups() {
        course1.addSubgroup(subgroup1);
        course1.addSubgroup(subgroup2);
        List<Subgroup> subgroupList = course1.getSubgroups();
        assertEquals(2,subgroupList.size());
        assertEquals(subgroup1,subgroupList.get(0));
        assertEquals(subgroup2,subgroupList.get(1));
    }

    @Test
    void testAddSubgroupsInDifferentOrder() {
        course1.addSubgroup(subgroup2);
        course1.addSubgroup(subgroup1);
        List<Subgroup> subgroupList = course1.getSubgroups();
        assertEquals(2,subgroupList.size());
        assertEquals(subgroup2,subgroupList.get(0));
        assertEquals(subgroup1,subgroupList.get(1));
    }

}
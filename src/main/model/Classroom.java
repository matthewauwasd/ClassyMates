package model;

import java.util.ArrayList;
import java.util.List;


// Represents a classroom that has a course name, course ID, and list of posts
public class Classroom {
    private final String finalCourseName;
    private final int finalCourseID;
    private List<Post> posts;
    // private List<User> users;


    // REQUIRES: courseName must not be an empty String, courseID > 0 and must be unique
    // EFFECTS: creates a classroom with a courseName, courseID,
    // an empty list of posts, and an empty list of users
    public Classroom(String courseName, int courseID) {
        this.finalCourseName = courseName;
        this.finalCourseID = courseID;
        this.posts = new ArrayList<Post>();
        // users = new ArrayList<User>();
    }

    // MODIFIES: this
    // EFFECTS: adds post to list of posts
    public void addPost(Post p) {
        this.posts.add(p);
    }

    // getters

    public String getCourseName() {
        return this.finalCourseName;
    }

    public int getCourseID() {
        return this.finalCourseID;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

}

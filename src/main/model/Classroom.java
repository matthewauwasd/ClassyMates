package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private final String COURSE_NAME;
    private final int COURSE_ID;
    private List<Post> posts;
    // private List<User> users;


    // REQUIRES: courseName must not be an empty String, courseID > 0 and must be unique
    // EFFECTS: creates a classroom with a courseName, courseID,
    // an empty list of posts, and an empty list of users
    public Classroom(String courseName, int courseID) {
        this.COURSE_NAME = courseName;
        this.COURSE_ID = courseID;
        this.posts = new ArrayList<Post>();
        // users = new ArrayList<User>();
    }

    // MODIFIES: this
    // EFFECTS: adds post to list of posts
    public void addPost(Post p) {
        this.posts.add(p);
    }

    public String getCourseName() {
        return this.COURSE_NAME;
    }

    public int getCourseID() {
        return this.COURSE_ID;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

}

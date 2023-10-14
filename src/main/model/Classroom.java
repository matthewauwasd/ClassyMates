package model;

import java.util.ArrayList;
import java.util.List;


// Represents a classroom that has a course name, course ID, and list of posts
public class Classroom {
    private final String finalCourseName;
    private final int finalCourseID;
    private List<Post> posts;
    private List<Subgroup> subgroups;


    // REQUIRES: courseName must not be an empty String, courseID > 0 and must be unique
    // EFFECTS: creates a classroom with a courseName, courseID,
    // an empty list of posts, and an empty list of users
    public Classroom(String courseName, int courseID) {
        finalCourseName = courseName;
        finalCourseID = courseID;
        posts = new ArrayList<Post>();
        subgroups = new ArrayList<Subgroup>();
    }

    // MODIFIES: this
    // EFFECTS: adds post to list of posts
    public void addPost(Post p) {
        posts.add(p);
    }

    // MODIFIES: this
    // EFFECTS: adds subgroup to list of subgroups
    public void addSubgroup(Subgroup sg) {
        subgroups.add(sg);
    }

    // getters

    public String getCourseName() {
        return finalCourseName;
    }

    public int getCourseID() {
        return finalCourseID;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Subgroup> getSubgroups() {
        return subgroups;
    }

}

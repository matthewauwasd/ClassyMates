package model;

import model.user.User;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a classroom that has a course name, course ID, and list of posts
public class Classroom implements Writable {
    private final String finalCourseName;
    private final int finalCourseID;
    private List<Post> posts;
    private List<Subgroup> subgroups;
    private List<User> listOfUsers;


    // REQUIRES: courseName must not be an empty String, courseID > 0 and must be unique
    // EFFECTS: creates a classroom with a courseName, courseID,
    // an empty list of posts, and an empty list of users
    public Classroom(String courseName, int courseID) {
        finalCourseName = courseName;
        finalCourseID = courseID;
        listOfUsers = new ArrayList<User>();
        posts = new ArrayList<Post>();
        subgroups = new ArrayList<Subgroup>();
    }

    // MODIFIES: this
    // EFFECTS: adds post to list of posts
    public void addPost(Post p) {
        posts.add(p);
        EventLog.getInstance().logEvent(new Event("Added post \"" + p.getPostTitle()
                + "\" to \"" + finalCourseName + "\""));
    }

    // MODIFIES: this
    // EFFECTS: adds subgroup to list of subgroups
    public void addSubgroup(Subgroup sg) {
        subgroups.add(sg);
        EventLog.getInstance().logEvent(new Event("Added subgroup \"" + sg.getSubgroupName()
                + "\" to \"" + finalCourseName + "\""));
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

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    @Override
    // EFFECTS: returns classroom in this subgroup as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseName", finalCourseName);
        json.put("courseID", finalCourseID);
        json.put("posts", postsToJson());
        json.put("subgroups", subgroupsToJson());
        return json;
    }

    // EFFECTS: returns posts in this classroom as a JSON array
    private JSONArray postsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Post p : posts) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns subgroups in this classroom as a JSON array
    private JSONArray subgroupsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Subgroup sg : subgroups) {
            jsonArray.put(sg.toJson());
        }

        return jsonArray;
    }

}

package model.user;

import model.Classroom;
import model.Comment;
import model.Post;
import model.Subgroup;

import java.util.List;

// Represents a user who is able to post and comment, with a username, password, and user type.
public class User {
    protected final String finalUsername;
    protected final String finalPassword;
    protected final String userType;

    public User(String username, String password, String userType) {
        finalUsername = username;
        finalPassword = password;
        this.userType = userType;
    }

    // REQUIRES: postTitle and postBody must not be empty Strings
    // MODIFIES: Post
    // EFFECTS: creates a post with postTitle and postBody and creates an empty list of comment
    public Post createPost(String postTitle, String postBody) {
        Post newPost = new Post(finalUsername,postTitle,postBody);
        return newPost;
    }

    // REQUIRES: commentBody must not be an empty String
    // MODIFIES: Comment
    // EFFECTS: creates a comment with commentBody
    public Comment createComment(String commentBody) {
        Comment newComment = new Comment(finalUsername,commentBody);
        return newComment;
    }

    // MODIFIES: Classroom
    // EFFECTS: adds current instance of User to selected Classroom
    public void joinClassroom(Classroom selectedClassroom) {
        selectedClassroom.getListOfUsers().add(this);
    }

    // MODIFIES: Classroom
    // EFFECTS: removes current instance of User from selected Classroom
    //          if user is in Subgroup(s), remove them from subgroup(s)
    public void leaveClassroom(Classroom selectedClassroom) {
        List<Subgroup> subgroups = selectedClassroom.getSubgroups();
        for (Subgroup s : subgroups) {
            if (s.getListOfStudents().contains(this)) {
                s.getListOfStudents().remove(this);
            }
        }
        selectedClassroom.getListOfUsers().remove(this);
    }

    // EFFECTS: method that does nothing, purpose is to be overridden by Student subclass
    public void joinSubgroup(Subgroup sg) {
    }

    // getters

    public String getUsername() {
        return finalUsername;
    }

    public String getPassword() {
        return finalPassword;
    }

    public String getUserType() {
        return userType;
    }
}

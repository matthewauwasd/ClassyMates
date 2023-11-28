package model.user;

import model.*;

// Represents a user who is able to post and comment, with a username, password, and user type.
public abstract class User {
    protected final String finalUsername;
    protected final String finalPassword;
    protected final String userType;

    // EFFECTS: creates a user with username, password, and user type
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
        EventLog.getInstance().logEvent(new Event("Created post: " + postTitle));
        return newPost;
    }

    // REQUIRES: commentBody must not be an empty String
    // MODIFIES: Comment
    // EFFECTS: creates a comment with commentBody
    public Comment createComment(String commentBody) {
        Comment newComment = new Comment(finalUsername,commentBody);
        EventLog.getInstance().logEvent(new Event("Created comment: " + commentBody));
        return newComment;
    }

    // MODIFIES: Classroom
    // EFFECTS: adds current instance of User to selected Classroom if user isn't already in the classroom
    public void joinClassroom(Classroom selectedClassroom) {
        if (!selectedClassroom.getListOfUsers().contains(this)) {
            selectedClassroom.getListOfUsers().add(this);
        }
    }

    // MODIFIES: Classroom
    // EFFECTS: removes current instance of User from selected Classroom
    //          if user is a Student and is in Subgroup(s), remove them from subgroup(s)
    public abstract void leaveClassroom(Classroom selectedClassroom);

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

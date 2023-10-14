package model.user;

import model.Comment;
import model.Post;


public abstract class User {
    protected final String finalUsername;
    protected final String finalPassword;
    protected final String userType;

    public User(String username, String password, String userType) {
        finalUsername = username;
        finalPassword = password;
        this.userType = userType;
    }

    // REQUIRES: postTitle and postBody must not be empty Strings
    // EFFECTS: creates a post with postTitle and postBody and creates an empty list of comment
    public Post createPost(String postTitle, String postBody) {
        Post newPost = new Post(finalUsername,postTitle,postBody);
        return newPost;
    }

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a comment with commentBody
    public Comment createComment(String commentBody) {
        Comment newComment = new Comment(finalUsername,commentBody);
        return newComment;
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

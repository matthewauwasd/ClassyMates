package model;


// Represents a comment that contains a comment body
public class Comment {
    private final String userWhoPosted;
    private final String finalCommentBody;

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a comment with commentBody
    public Comment(String user,String commentBody) {
        userWhoPosted = user;
        finalCommentBody = commentBody;
    }

    // getters

    public String getCommentBody() {
        return finalCommentBody;
    }

    public String getUserWhoPosted() {
        return userWhoPosted;
    }

}
package model;


// Represents a comment that contains a comment body
public class Comment {
    private final String finalCommentBody;

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a comment with commentBody
    public Comment(String commentBody) {
        finalCommentBody = commentBody;
    }

    // getters

    public String getCommentBody() {
        return finalCommentBody;
    }

}

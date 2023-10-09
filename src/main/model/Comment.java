package model;

public class Comment {
    private final String finalCommentBody;

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a comment with commentBody
    public Comment(String commentBody) {
        finalCommentBody = commentBody;
    }

    public String getCommentBody() {
        return finalCommentBody;
    }

}

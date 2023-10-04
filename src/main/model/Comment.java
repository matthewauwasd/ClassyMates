package model;

public class Comment {
    private final String COMMENT_BODY;

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a comment with commentBody
    public Comment(String commentBody) {
        COMMENT_BODY = commentBody;
    }

    public String getCommentBody() {
        return COMMENT_BODY;
    }

}

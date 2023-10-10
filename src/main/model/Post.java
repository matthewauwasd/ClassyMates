package model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private final String finalPostTitle;
    private final String finalPostBody;
    //private final int POST_ID;
    // POST ID will be count++;
    private List<Comment> comments;

    // REQUIRES: postTitle and postBody must not be empty Strings
    // EFFECTS: creates a post with postTitle and postBody and creates an empty list of comment
    public Post(String postTitle, String postBody) {
        finalPostTitle = postTitle;
        finalPostBody = postBody;
        //POST_ID = random, unique integer
        this.comments = new ArrayList<Comment>();

    }

    // MODIFIES: this
    // EFFECTS: adds comment to list of comments
    public void addComment(Comment c) {
        this.comments.add(c);
    }

    public void deleteComment(String comment) {
        for (int i = 0; i < this.comments.size(); i++) {
            Comment currentComment = comments.get(i);
            if (comment.equals(currentComment.getCommentBody())) {
                this.comments.remove(currentComment);
                i--;
            }
        }

    }

    // getters

    public String getPostTitle() {
        return finalPostTitle;
    }

    public String getPostBody() {
        return finalPostBody;
    }

    public List<Comment> getComments() {
        return this.comments;
    }
}

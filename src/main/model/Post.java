package model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private final String POST_TITLE;
    private final String POST_BODY;
    //private final int POST_ID;
    private List<Comment> comments;

    // REQUIRES: postTitle and postBody must not be empty Strings
    // EFFECTS: creates a post with postTitle and postBody and creates an empty list of comment
    public Post(String postTitle, String postBody) {
        POST_TITLE = postTitle;
        POST_BODY = postBody;
        //POST_ID = random, unique integer
        this.comments = new ArrayList<Comment>();

    }

    // MODIFIES: this
    // EFFECTS: adds comment to list of comments
    public void addComment(Comment c) {
        this.comments.add(c);
    }

    public String getPostTitle() {
        return POST_TITLE;
    }

    public String getPostBody() {
        return POST_BODY;
    }

    public List<Comment> getComments() {
        return this.comments;
    }
}

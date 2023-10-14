package model;

import java.util.ArrayList;
import java.util.List;


// Represents a post that has a title, a body, and a list of comments
public class Post {
    private final String finalPostTitle;
    private final String finalPostBody;
    private final String userWhoPosted;
    //private final int POST_ID;
    // POST ID will be count++;
    private List<Comment> comments;

    // REQUIRES: postTitle and postBody must not be empty Strings
    // EFFECTS: creates a post with postTitle and postBody and creates an empty list of comment
    public Post(String user, String postTitle, String postBody) {
        userWhoPosted = user;
        finalPostTitle = postTitle;
        finalPostBody = postBody;
        //POST_ID = random, unique integer
        comments = new ArrayList<Comment>();

    }

    // MODIFIES: this
    // EFFECTS: adds comment to list of comments
    public void addComment(Comment c) {
        comments.add(c);
    }

    // getters

    public String getPostTitle() {
        return finalPostTitle;
    }

    public String getPostBody() {
        return finalPostBody;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getUserWhoPosted() {
        return userWhoPosted;
    }
}
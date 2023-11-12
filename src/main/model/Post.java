package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a post that has a title, a body, and a list of comments
public class Post implements Writable {
    private final String finalPostTitle;
    private final String finalPostBody;
    private final String userWhoPosted;
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

    @Override
    // EFFECTS: returns post in this subgroup as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("postTitle", finalPostTitle);
        json.put("postBody", finalPostBody);
        json.put("userWhoPosted", userWhoPosted);
        json.put("comments", commentsToJson());
        return json;
    }

    // EFFECTS: returns comments in this post as a JSON array
    private JSONArray commentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Comment c : comments) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}

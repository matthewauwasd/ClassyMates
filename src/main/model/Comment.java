package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a comment that contains a comment body
public class Comment implements Writable {
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

    @Override
    // EFFECTS: returns comment in this subgroup as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userWhoPosted", userWhoPosted);
        json.put("commentBody", finalCommentBody);
        return json;
    }

}

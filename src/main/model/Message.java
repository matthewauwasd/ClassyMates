package model;

public class Message {
    private final String userWhoPosted;
    private final String finalMessageBody;

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a comment with commentBody
    public Message(String user,String commentBody) {
        userWhoPosted = user;
        finalMessageBody = commentBody;
    }

    // getters

    public String getCommentBody() {
        return finalMessageBody;
    }

    public String getUserWhoPosted() {
        return userWhoPosted;
    }

}

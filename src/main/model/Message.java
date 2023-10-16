package model;

// Represents a message with a message boy
public class Message {
    private final String userWhoPosted;
    private final String finalMessageBody;

    // REQUIRES: commentBody must not be an empty String
    // EFFECTS: creates a message with messageBody
    public Message(String user,String messageBody) {
        userWhoPosted = user;
        finalMessageBody = messageBody;
    }

    // getters

    public String getCommentBody() {
        return finalMessageBody;
    }

    public String getUserWhoPosted() {
        return userWhoPosted;
    }

}

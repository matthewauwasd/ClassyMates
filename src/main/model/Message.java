package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a message with a message body
public class Message implements Writable {
    private final String userWhoPosted;
    private final String finalMessageBody;

    // REQUIRES: messageBody must not be an empty String
    // EFFECTS: creates a message with messageBody
    public Message(String user,String messageBody) {
        userWhoPosted = user;
        finalMessageBody = messageBody;
    }

    // getters

    public String getMessageBody() {
        return finalMessageBody;
    }

    public String getUserWhoPosted() {
        return userWhoPosted;
    }

    // EFFECTS: returns message in this subgroup as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userWhoPosted", userWhoPosted);
        json.put("messageBody", finalMessageBody);
        return json;
    }

}

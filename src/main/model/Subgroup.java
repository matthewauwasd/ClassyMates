package model;

import model.user.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a subgroup with a name, a list of subgroup interests, and a list of messages
public class Subgroup implements Writable {
    private final String finalSubgroupName;
    private List<Message> messages;
    private List<Student> students;

    // EFFECTS: creates a Subgroup with given name and empty list of group interests represented as strings
    public Subgroup(String subgroupName) {
        finalSubgroupName = subgroupName;
        messages = new ArrayList<Message>();
        students = new ArrayList<Student>();
    }

    // MODIFIES: this
    // EFFECTS: adds message to list of messages
    public void addMessage(Message m) {
        messages.add(m);
    }

    // getters

    public String getSubgroupName() {
        return finalSubgroupName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Student> getListOfStudents() {
        return students;
    }

    // EFFECTS: returns subgroup in this subgroup as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subgroupName", finalSubgroupName);
        json.put("messages", messagesToJson());
        return json;
    }

    // EFFECTS: returns messages in this subgroup as a JSON array
    private JSONArray messagesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Message m : messages) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

}

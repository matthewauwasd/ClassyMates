// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads structure from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Structure read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStructure(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses structure from JSON object and returns it
    private Structure parseStructure(JSONObject jsonObject) {
        Structure str = new Structure();
        addClassrooms(str, jsonObject);
        return str;
    }

    // MODIFIES: str
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addClassrooms(Structure str, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("classrooms");
        for (Object json : jsonArray) {
            JSONObject nextClassroom = (JSONObject) json;
            addClassroom(str, nextClassroom);
        }
    }

    // MODIFIES: str
    // EFFECTS: parses classroom from JSON object and adds it to structure
    private void addClassroom(Structure str, JSONObject jsonObject) {
        String courseName = jsonObject.getString("courseName");
        int courseID = jsonObject.getInt("courseID");
        Classroom cl = new Classroom(courseName,courseID);
        str.addClassroom(cl);
        addPosts(cl, jsonObject);
        addSubgroups(cl, jsonObject);
    }

    // MODIFIES: cl
    // EFFECTS: parses posts from JSON object and adds them to classroom
    private void addPosts(Classroom cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("posts");
        for (Object json : jsonArray) {
            JSONObject nextPost = (JSONObject) json;
            addPost(cl, nextPost);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses post from JSON object and adds it to classroom
    private void addPost(Classroom cl, JSONObject jsonObject) {
        String postTitle = jsonObject.getString("postTitle");
        String postBody = jsonObject.getString("postBody");
        String userWhoPosted = jsonObject.getString("userWhoPosted");
        Post p = new Post(userWhoPosted,postTitle,postBody);
        cl.addPost(p);
        addComments(p, jsonObject);
    }


    // MODIFIES: p
    // EFFECTS: parses comments from JSON object and adds them to post
    private void addComments(Post p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("comments");
        for (Object json : jsonArray) {
            JSONObject nextComment = (JSONObject) json;
            addComment(p, nextComment);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses comment from JSON object and adds it to post
    private void addComment(Post p, JSONObject jsonObject) {
        String userWhoPosted = jsonObject.getString("userWhoPosted");
        String commentBody = jsonObject.getString("commentBody");
        Comment comment = new Comment(userWhoPosted, commentBody);
        p.addComment(comment);
    }

    // MODIFIES: cl
    // EFFECTS: parses subgroups from JSON object and adds them to classroom
    private void addSubgroups(Classroom cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("subgroups");
        for (Object json : jsonArray) {
            JSONObject nextSubgroup = (JSONObject) json;
            addSubgroup(cl, nextSubgroup);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses subgroup from JSON object and adds it to classroom
    private void addSubgroup(Classroom cl, JSONObject jsonObject) {
        String subgroupName = jsonObject.getString("subgroupName");
        Subgroup sg = new Subgroup(subgroupName);
        cl.addSubgroup(sg);
        addMessages(sg, jsonObject);
    }


    // MODIFIES: sg
    // EFFECTS: parses messages from JSON object and adds them to subgroup
    private void addMessages(Subgroup sg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("messages");
        for (Object json : jsonArray) {
            JSONObject nextMessage = (JSONObject) json;
            addMessage(sg, nextMessage);
        }
    }

    // MODIFIES: sg
    // EFFECTS: parses message from JSON object and adds it to subgroup
    private void addMessage(Subgroup sg, JSONObject jsonObject) {
        String userWhoPosted = jsonObject.getString("userWhoPosted");
        String messageBody = jsonObject.getString("messageBody");
        Message message = new Message(userWhoPosted, messageBody);
        sg.addMessage(message);
    }

}

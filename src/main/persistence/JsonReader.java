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

    // MODIFIES: Structure
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addClassrooms(Structure str, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("classrooms");
        for (Object json : jsonArray) {
            JSONObject nextClassroom = (JSONObject) json;
            addClassroom(str, nextClassroom);
        }
    }

    // MODIFIES: Structure
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addClassroom(Structure str, JSONObject jsonObject) {
        String courseName = jsonObject.getString("courseName");
        int courseID = jsonObject.getInt("courseID");
        Classroom cl = new Classroom(courseName,courseID);
        addPosts(str, cl, jsonObject);
        addSubgroups(str, cl, jsonObject);
    }

    // MODIFIES: Structure
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addPosts(Structure str, Classroom cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("posts");
        for (Object json : jsonArray) {
            JSONObject nextPost = (JSONObject) json;
            addPost(str, cl, nextPost);
        }
    }

    // MODIFIES: Structure
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addPost(Structure str, Classroom cl, JSONObject jsonObject) {
        String postTitle = jsonObject.getString("postTitle");
        String postBody = jsonObject.getString("postBody");
        String userWhoPosted = jsonObject.getString("userWhoPosted");
        Post p = new Post(userWhoPosted,postTitle,postBody);
        addComments(str, cl, p, jsonObject);
    }


    // MODIFIES: Post
    // EFFECTS: parses comment from JSON object and adds it to post
    private void addComments(Structure str, Classroom cl, Post p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("comments");
        for (Object json : jsonArray) {
            JSONObject nextComment = (JSONObject) json;
            addComment(str, cl, p, nextComment);
        }
    }

    // MODIFIES: Post
    // EFFECTS: parses comment from JSON object and adds it to post
    private void addComment(Structure str, Classroom cl, Post p, JSONObject jsonObject) {
        String userWhoPosted = jsonObject.getString("userWhoPosted");
        String commentBody = jsonObject.getString("commentBody");
        Comment comment = new Comment(userWhoPosted, commentBody);
        str.addClassroom(cl);
        cl.addPost(p);
        p.addComment(comment);
    }

    // MODIFIES: Structure
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addSubgroups(Structure str, Classroom cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("subgroups");
        for (Object json : jsonArray) {
            JSONObject nextSubgroup = (JSONObject) json;
            addSubgroup(str, cl, nextSubgroup);
        }
    }

    // MODIFIES: Structure
    // EFFECTS: parses classrooms from JSON object and adds them to structure
    private void addSubgroup(Structure str, Classroom cl, JSONObject jsonObject) {
        String subgroupName = jsonObject.getString("subgroupName");
        Subgroup sg = new Subgroup(subgroupName);
        addMessages(str, cl, sg, jsonObject);
    }


    // MODIFIES: Post
    // EFFECTS: parses comment from JSON object and adds it to post
    private void addMessages(Structure str, Classroom cl, Subgroup sg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("messages");
        for (Object json : jsonArray) {
            JSONObject nextMessage = (JSONObject) json;
            addMessage(str, cl, sg, nextMessage);
        }
    }

    // MODIFIES: Post
    // EFFECTS: parses comment from JSON object and adds it to post
    private void addMessage(Structure str, Classroom cl, Subgroup sg, JSONObject jsonObject) {
        String userWhoPosted = jsonObject.getString("userWhoPosted");
        String messageBody = jsonObject.getString("messageBody");
        Message message = new Message(userWhoPosted, messageBody);
        str.addClassroom(cl);
        cl.addSubgroup(sg);
        sg.addMessage(message);
    }

}

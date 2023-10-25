package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Structure implements Writable {

    private List<Classroom> classroomList;

    public Structure() {
        classroomList = new ArrayList<Classroom>();
    }

    public void addClassroom(Classroom cr) {
        classroomList.add(cr);
    }

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("classrooms", classroomsToJson());
        return json;
    }

    // EFFECTS: returns classrooms in this structure as a JSON array
    private JSONArray classroomsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Classroom cr : classroomList) {
            jsonArray.put(cr.toJson());
        }

        return jsonArray;
    }

}

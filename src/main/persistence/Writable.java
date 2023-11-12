// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import org.json.JSONObject;

// Represents a writing feature
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

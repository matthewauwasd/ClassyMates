package model;

import model.user.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a subgroup with a name, a list of subgroup interests, and a list of messages
public class Subgroup {
    private final String finalSubgroupName;
    private List<String> groupInterests;
    private List<Message> messages;
    private List<Student> students;

    String[] allInterests = {"Anime","Art","Cars","Cooking","Environment","Exercising","Gardening","Hiking","Music",
            "Photography","Reading","Socializing","Sports","Tech","Travelling","Video Games","Volunteering","Yoga"};
    private List<String> listOfInterests = new ArrayList<>(Arrays.asList(allInterests));

    // EFFECTS: creates a Subgroup with given name and empty list of group interests represented as strings
    public Subgroup(String subgroupName) {
        finalSubgroupName = subgroupName;
        groupInterests = new ArrayList<>(5);
        messages = new ArrayList<Message>();
        students = new ArrayList<Student>();
    }

    // MODIFIES: this
    // EFFECTS: if given interest is in list of interests, adds given interest to subgroup interests
    public void addSubgroupInterest(String interest) {
        for (String s : listOfInterests) {
            if (s.equals(interest)) {
                groupInterests.add(s);
            }
        }
    }

    // getters

    public List<String> getListOfInterests() {
        return listOfInterests;
    }

    public String getFinalSubgroupName() {
        return finalSubgroupName;
    }

    public List<String> getGroupInterests() {
        return groupInterests;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Student> getListOfStudents() {
        return students;
    }

}

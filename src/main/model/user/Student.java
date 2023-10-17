package model.user;

import model.*;

import java.util.List;

// Represents a student with a username, password, and user type of student.
// Has basic user privileges as well as those specific to students.
public class Student extends User {

    public Student(String username, String password, String userType) {
        super(username,password,userType);
    }

    // REQUIRES: subgroupName cannot be an empty String
    // MODIFIES: Subgroup
    // EFFECTS: creates and returns new subgroup with subgroup name
    public Subgroup createSubgroup(String subgroupName) {
        Subgroup newSubgroup = new Subgroup(subgroupName);
        return newSubgroup;
    }

    // MODIFIES: Subgroup
    // EFFECTS: adds current instance of Student to selected Subgroup
    public void joinSubgroup(Subgroup selectedSubgroup) {
        selectedSubgroup.getListOfStudents().add(this);
    }

    // MODIFIES: Subgroup
    // EFFECTS: removes current instance of Student from selected Subgroup
    public void leaveSubgroup(Subgroup selectedSubgroup) {
        selectedSubgroup.getListOfStudents().remove(this);
    }

    // MODIFIES: Message
    // EFFECTS: creates and returns new message with message body
    public Message createMessage(String messageBody) {
        Message newMessage = new Message(finalUsername,messageBody);
        return newMessage;
    }

    // MODIFIES: Subgroup
    // EFFECTS: removes Message from Subgroup if entered message body matches message body in list of messages
    public void deleteMessage(Subgroup currentSubgroup, String messageBody) {
        for (int i = 0; i < currentSubgroup.getMessages().size(); i++) {
            Message currentMessage = currentSubgroup.getMessages().get(i);
            if (messageBody.equals(currentMessage.getMessageBody())) {
                currentSubgroup.getMessages().remove(currentMessage);
                i--;
            }
        }
    }

    // MODIFIES: Classroom, Subgroup
    // TEST IF THIS WORKS, IT MIGHT NOT...
    // EFFECTS: removes current instance of User from selected Classroom
    //          if user is in Subgroup(s), remove them from subgroup(s)
    public void leaveClassroom(Classroom selectedClassroom) {
        List<Subgroup> subgroups = selectedClassroom.getSubgroups();
        for (Subgroup s : subgroups) {
            if (s.getListOfStudents().contains(this)) {
                s.getListOfStudents().remove(this);
            }
        }
        selectedClassroom.getListOfUsers().remove(this);
    }

}

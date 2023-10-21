package model.user;

import model.*;

import java.util.ArrayList;
import java.util.List;

// Represents a student with a username, password, and user type of student.
// Has basic user privileges as well as those specific to students.
public class Student extends User {

    List<Subgroup> subgroupsJoined;

    // EFFECTS: creates student of username, password, and user type
    public Student(String username, String password, String userType) {
        super(username,password,userType);
        subgroupsJoined = new ArrayList<Subgroup>();
    }

    // REQUIRES: subgroupName cannot be an empty String
    // MODIFIES: Subgroup
    // EFFECTS: creates and returns new subgroup with subgroup name
    public Subgroup createSubgroup(String subgroupName) {
        Subgroup newSubgroup = new Subgroup(subgroupName);
        return newSubgroup;
    }

    // MODIFIES: Subgroup
    // EFFECTS: adds current instance of Student to selected Subgroup if they aren't already in the subgroup,
    //          then adds subgroup to student's list of joined subgroups
    public void joinSubgroup(Subgroup selectedSubgroup) {
        if (!selectedSubgroup.getListOfStudents().contains(this)) {
            selectedSubgroup.getListOfStudents().add(this);
            subgroupsJoined.add(selectedSubgroup);
        }
    }

    // MODIFIES: Subgroup
    // EFFECTS: removes current instance of Student from selected Subgroup
    //          Student has to be in subgroup to call this method
    public void leaveSubgroup(Subgroup selectedSubgroup) {
        selectedSubgroup.getListOfStudents().remove(this);
        this.subgroupsJoined.remove(selectedSubgroup);
    }

    // MODIFIES: Message
    // EFFECTS: creates and returns new message with message body
    public Message createMessage(String messageBody) {
        Message newMessage = new Message(finalUsername,messageBody);
        return newMessage;
    }

    // MODIFIES: Subgroup
    // EFFECTS: removes Message from Subgroup if entered message body matches message body in list of messages
    //          and if the user who sent the message is deleting the message.
    //          otherwise, do nothing
    public void deleteMessage(Subgroup currentSubgroup, String messageBody) {
        for (int i = 0; i < currentSubgroup.getMessages().size(); i++) {
            Message currentMessage = currentSubgroup.getMessages().get(i);
            String userWhoPosted = currentMessage.getUserWhoPosted();
            if ((messageBody.equals(currentMessage.getMessageBody())) && (this.finalUsername.equals(userWhoPosted))) {
                currentSubgroup.getMessages().remove(currentMessage);
                break;
            }
        }
    }

    // MODIFIES: Classroom, Subgroup
    // EFFECTS: removes current instance of User from selected Classroom
    //          if user is in Subgroup(s), remove them from subgroup(s)
    public void leaveClassroom(Classroom selectedClassroom) {
        List<Subgroup> subgroups = selectedClassroom.getSubgroups();
        for (Subgroup s : subgroups) {
            s.getListOfStudents().remove(this);
            this.subgroupsJoined.remove(s);
        }
        selectedClassroom.getListOfUsers().remove(this);
    }

    // getter

    public List<Subgroup> getSubgroupsJoined() {
        return subgroupsJoined;
    }

}

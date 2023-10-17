package model.user;

import model.*;

// Represents a student with a username, password, and user type of student.
// Has basic user privileges as well as those specific to students.
public class Student extends User {

    public Student(String username, String password, String userType) {
        super(username,password,userType);
    }

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

}

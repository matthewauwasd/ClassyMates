package model.user;

import model.Subgroup;

public class Student extends User {

    public Student(String username, String password, String userType) {
        super(username,password,userType);
    }

    public Subgroup createSubgroup() {
        return null; // stub
    }

    // createMessage()

}

package ui;

import model.*;
import model.user.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// ClassyMates application
public class ClassyMatesApp {
    private Scanner input;
    private static final String JSON_STRUCTURE = "./data/structure.json";
    private Structure structure;
    private Classroom currentClassroom;
    private Post currentPost;
    private Student currentStudent;
    private Subgroup currentSubgroup;
    private Classroom modelClassroom1 = new Classroom("CPSC 210", 210);
    private Classroom modelClassroom2 = new Classroom("MATH 200", 200);
    private Classroom modelClassroom3 = new Classroom("CPSC 221", 221);
    private Classroom modelClassroom4 = new Classroom("CPSC 213", 213);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs ClassyMates application
    public ClassyMatesApp() {
        structure = new Structure();
        jsonWriter = new JsonWriter(JSON_STRUCTURE);
        jsonReader = new JsonReader(JSON_STRUCTURE);
        structure.addClassroom(modelClassroom1);
        structure.addClassroom(modelClassroom2);
        structure.addClassroom(modelClassroom3);
        structure.addClassroom(modelClassroom4);
        runClassyMates();
    }

    // MODIFIES: this
    // EFFECTS: moves user to appropriate view based on user type
    private void runClassyMates() {
        init();

        displayUserCreation();
        classroomsView();
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: displays initial options to user
    private void displayUserCreation() {
        System.out.println("\nHi, please make an account!\n");
        System.out.println("Please enter a username:");
        String enteredUsername = input.next();

        System.out.println("Please enter a password:");
        String enteredPassword = input.next();

        currentStudent = new Student(enteredUsername, enteredPassword, "Student");
    }


    // EFFECTS: moves user to appropriate method based on user input
    private void classroomsView() {
        Boolean selectionValid = true;
        while (selectionValid) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("a - View classrooms");
            System.out.println("b - Save data");
            System.out.println("c - Load data");
            System.out.println("d - Exit program");
            String command = input.next();
            if (command.equals("a")) {
                viewClassrooms();
            } else if (command.equals("b")) {
                saveStructure();
            } else if (command.equals("c")) {
                loadStructure();
            } else if (command.equals("d")) {
                selectionValid = false;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }
    }

    // EFFECTS: displays classrooms made and prompts student to go into a classroom
    private void viewClassrooms() {
        System.out.println("\nType the classroom's ID would you like to view:");
        for (Classroom c : structure.getClassroomList()) {
            System.out.print("Classroom Name: " + c.getCourseName() + ", ");
            System.out.println("Classroom ID: " + c.getCourseID() + " ");
        }

        int viewWhichClassroom = input.nextInt();

        for (Classroom c : structure.getClassroomList()) {
            if (viewWhichClassroom == c.getCourseID()) {
                currentClassroom = c;
                classroomView(currentClassroom);
            }
        }
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void classroomView(Classroom c) {
        boolean running = true;

        while (running) {
            System.out.println("\nYou are in classroom: " + c.getCourseName());
            studentCVOptions();

            String classroomChoice = input.next();

            if (classroomChoice.equals("a")) {
                createPost();
            } else if (classroomChoice.equals("b")) {
                createSubgroup();
            } else if (classroomChoice.equals("c")) {
                viewPosts();
            } else if (classroomChoice.equals("d")) {
                viewSubgroups();
            } else if (classroomChoice.equals("e")) {
                running = false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // EFFECTS: prints out options for student classroom view
    private void studentCVOptions() {
        System.out.println("What would you like to do?");
        System.out.println("\na - Create a post");
        System.out.println("b - Create a subgroup");
        System.out.println("c - View posts");
        System.out.println("d - View subgroups");
        System.out.println("e - Go back");
    }

    // MODIFIES: this, Classroom
    // EFFECTS: creates post based on user input and adds it to list of posts in associated classroom
    private void createPost() {
        System.out.println("Please enter the title of your post:");
        String postTitle = input.next();
        System.out.println("What would you like to write for your post?");
        String postBody = input.next();
        currentClassroom.addPost(currentStudent.createPost(postTitle, postBody));
    }

    // EFFECTS: displays posts made and moves user based on input
    private void viewPosts() {
        Boolean running = true;
        while (running) {
            System.out.println("\nHere are the current posts:");
            for (Post p : currentClassroom.getPosts()) {
                System.out.println(p.getPostTitle());
            }
            System.out.println("What would you like to do?");
            System.out.println("a - Select post");
            System.out.println("b - Go back");
            String command = input.next();

            if (command.equals("a")) {
                selectPost();
            } else if (command.equals("b")) {
                running = false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // EFFECTS: creates a new subgroup and puts it into respective classroom's list of subgroups
    private void createSubgroup() {
        System.out.println("Please enter a name for your subgroup:");
        String subgroupName = input.next();
        currentClassroom.addSubgroup(currentStudent.createSubgroup(subgroupName));
    }

    // EFFECTS: displays all subgroups in classroom
    private void viewSubgroups() {
        Boolean running = true;

        while (running) {
            System.out.println("\nHere are the classroom's subgroups:");
            for (Subgroup sg : currentClassroom.getSubgroups()) {
                System.out.println(sg.getSubgroupName());
            }
            System.out.println("\nWhat would you like to do?");
            System.out.println("a - Join a subgroup");
            System.out.println("b - Enter joined subgroups");
            System.out.println("c - Go back");
            String command = input.next();

            if (command.equals("a")) {
                joinSubgroup();
            } else if (command.equals("b")) {
                viewJoinedSubgroups();
            } else if (command.equals("c")) {
                running = false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // MODIFIES: Student
    // EFFECTS: allows user to join entered subgroup
    private void joinSubgroup() {
        System.out.println("\nHere are the classroom's subgroups:");
        for (Subgroup sg : currentClassroom.getSubgroups()) {
            System.out.println(sg.getSubgroupName());
        }
        System.out.println("\nPlease enter the subgroup you would like to join.");
        String command = input.next();

        for (Subgroup sg : currentClassroom.getSubgroups()) {
            if (command.equals(sg.getSubgroupName())) {
                currentStudent.joinSubgroup(sg);
            }
        }
    }

    // EFFECTS: displays subgroups user has joined
    private void viewJoinedSubgroups() {
        System.out.println("\nHere are the subgroups you have joined:");
        for (Subgroup sg : currentStudent.getSubgroupsJoined()) {
            System.out.println(sg.getSubgroupName());
        }
        System.out.println("\nWhich subgroup would you like to go into?");
        String command = input.next();
        for (Subgroup sg : currentStudent.getSubgroupsJoined()) {
            if (command.equals(sg.getSubgroupName())) {
                currentSubgroup = sg;
                subgroupView(currentSubgroup);
            }
        }
    }

    // EFFECTS: displays options for actions in subgroup
    private void subgroupView(Subgroup sg) {
        Boolean running = true;
        while (running) {
            System.out.println("\nSubgroup " + sg.getSubgroupName());
            for (Message m : sg.getMessages()) {
                System.out.println("\n" + m.getUserWhoPosted() + ":");
                System.out.println(m.getMessageBody());
            }

            sgvOptions();
            String command = input.next();

            if (command.equals("a")) {
                createMessage();
            } else if (command.equals("b")) {
                currentStudent.leaveSubgroup(currentClassroom.getSubgroups(),currentSubgroup.getSubgroupName());
                viewSubgroups();
                running = false;
            } else if (command.equals("c")) {
                running = false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // EFFECTS: prints out options available in subgroup view
    private void sgvOptions() {
        System.out.println("What would you like to do?");
        System.out.println("a - Send a message");
        System.out.println("b - Leave subgroup");
        System.out.println("c - Go back");
    }

    // MODIFIES: this, Subgroup
    // EFFECTS: creates new message based on user input and adds it to associated Subgroup
    private void createMessage() {
        System.out.println("What would you like to say?");
        String message = input.next();
        currentSubgroup.addMessage(currentStudent.createMessage(message));
    }

    // EFFECTS: sets current post based on instructor input
    private void selectPost() {
        Boolean running = true;
        while (running) {

            if (currentClassroom.getPosts().isEmpty()) {
                System.out.println("There are no posts.");
                break;
            }

            System.out.println("\nHere are the current posts:");
            for (Post p : currentClassroom.getPosts()) {
                System.out.println("\n" + p.getUserWhoPosted() + " posted:");
                System.out.println(p.getPostTitle());
            }
            System.out.println("\nWhich post would you like to view?");
            String command = input.next();

            for (Post p : currentClassroom.getPosts()) {
                if (command.equals(p.getPostTitle())) {
                    currentPost = p;
                    postView(currentPost);
                    running = false;
                }
            }
        }
    }

    // EFFECTS: displays options for actions in post
    private void postView(Post p) {
        boolean running = true;

        while (running) {
            System.out.println("\nYou are viewing post: " + p.getPostTitle());

            System.out.println("\nPost Title: " + p.getPostTitle());
            System.out.println("\n" + p.getPostBody());
            System.out.println("\nPosted by " + p.getUserWhoPosted());

            System.out.println("\n\nComments:");
            if (p.getComments().isEmpty()) {
                System.out.println("There are currently no comments.");
            } else {
                for (Comment c : p.getComments()) {
                    System.out.println("\n" + c.getUserWhoPosted() + " posted:");
                    System.out.println(c.getCommentBody());
                }
            }
            postViewOptions();
            postViewChoices();
        }
    }

    // EFFECTS: displays choices available in postView method
    private void postViewOptions() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\na - Create a comment");
        System.out.println("b - Go back");
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void postViewChoices() {
        Boolean running = true;

        while (running) {
            String postViewChoice = input.next();

            if (postViewChoice.equals("a")) {
                createComment();
                running = false;
            } else if (postViewChoice.equals("b")) {
                viewPosts();
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // MODIFIES: this, Post
    // EFFECTS: creates new comment based on user input and adds it to associated Post
    private void createComment() {
        System.out.println("What would you like to comment?");
        String enteredComment = input.next();
        currentPost.addComment(currentStudent.createComment(enteredComment));
    }

    // EFFECTS: saves the structure to file
    private void saveStructure() {
        try {
            jsonWriter.open();
            jsonWriter.write(structure);
            jsonWriter.close();
            System.out.println("Saved data to " + JSON_STRUCTURE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STRUCTURE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads structure from file
    private void loadStructure() {
        try {
            structure = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STRUCTURE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STRUCTURE);
        }
    }

}
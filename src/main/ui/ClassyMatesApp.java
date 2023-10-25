package ui;

import model.*;
import model.user.Instructor;
import model.user.Student;

import java.util.Scanner;

// ClassyMates application
public class ClassyMatesApp {
    private Scanner input;
    private Structure structure;
    private Classroom currentClassroom;
    private Post currentPost;
    private Instructor currentInstructor;
    private Student currentStudent;
    private Subgroup currentSubgroup;
    private Classroom modelClassroom = new Classroom("CPSC 210", 210);

    // EFFECTS: runs ClassyMates application
    public ClassyMatesApp() {
        structure = new Structure();
        structure.addClassroom(modelClassroom);
        runClassyMates();
    }

    // MODIFIES: this
    // EFFECTS: moves user to appropriate view based on user type
    private void runClassyMates() {
        init();

        displayUserCreation();
        if (currentStudent != null) {
            studentView();
        } else {
            instructorView();
        }
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
        Boolean accountLoop = true;
        System.out.println("\nHi, please make an account!\n");
        System.out.println("Please enter a username:");
        String enteredUsername = input.next();

        System.out.println("Please enter a password:");
        String enteredPassword = input.next();

        while (accountLoop) {
            System.out.println("Are you an instructor or student?");
            System.out.println("Please type \"instructor\" or \"student\".");
            String typeOfUser = input.next();

            if (typeOfUser.equals("instructor")) {
                currentInstructor = new Instructor(enteredUsername, enteredPassword, "Instructor");
                accountLoop = false;
            } else if (typeOfUser.equals("student")) {
                currentStudent = new Student(enteredUsername, enteredPassword, "Student");
                accountLoop = false;
            } else {
                System.out.println("Please enter a proper user type.\n");
            }
        }
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void instructorView() {
        Boolean selectionValid = true;
        while (selectionValid) {
            instructorClassroomsView();
            selectionValid = false;
        }
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void studentView() {
        Boolean selectionValid = true;
        while (selectionValid) {
            studentClassroomsView();
            selectionValid = false;
        }
    }

    // ~~~~~~~~~~ INSTRUCTOR BASED METHODS BELOW ~~~~~~~~~~

    // EFFECTS: moves user to appropriate method based on user input
    private void instructorClassroomsView() {
        Boolean selectionValid = true;
        while (selectionValid) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("a - Create a classroom");
            System.out.println("b - View a classroom");
            System.out.println("c - Exit program");
            String command = input.next();
            if (command.equals("a")) {
                createClassroom();
            } else if (command.equals("b")) {
                viewClassroomInstructor();
            } else if (command.equals("c")) {
                selectionValid = false;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new classroom
    private void createClassroom() {
        boolean running = true;
        while (running) {
            String className = "";
            int classID = 0;

            className = checkIfString();
            classID = checkIfInt();

            structure.addClassroom(new Classroom(className, classID));

            running = false;
        }
    }

    // EFFECTS: checks if user input is String and not visually empty
    private String checkIfString() {
        String className = "";
        boolean isString = true;

        while (isString) {
            System.out.println("\nPlease give your classroom a name:");

            className = input.next().trim();

            if (className.isEmpty()) {
                System.out.println("Please enter a valid classroom name.");
            } else {
                isString = false;
            }
        }
        return className;
    }

    // EFFECTS: checks if user input is int
    private int checkIfInt() {
        int classID = 0;
        boolean isInt = true;

        while (isInt) {
            System.out.println("\nPlease give your classroom a class ID:");

            if (input.hasNextInt()) {
                classID = input.nextInt();
                isInt = false;
            } else {
                System.out.println("Please enter a valid ID.");
                input.next();
            }
        }
        return classID;
    }

    // EFFECTS: displays classrooms made and prompts instructors to go into a classroom
    private void viewClassroomInstructor() {
        System.out.println("\nType the classroom's ID would you like to view:");
        for (Classroom c : structure.getClassroomList()) {
            System.out.print("Classroom Name: " + c.getCourseName() + ", ");
            System.out.println("Classroom ID: " + c.getCourseID() + " ");
        }

        int viewWhichClassroom = input.nextInt();

        for (Classroom c : structure.getClassroomList()) {
            if (viewWhichClassroom == c.getCourseID()) {
                currentClassroom = c;
                classroomViewInstructor(currentClassroom);
            }
        }
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void classroomViewInstructor(Classroom c) {
        boolean running = true;

        while (running) {
            System.out.println("\nYou are in classroom: " + c.getCourseName());
            System.out.println("What would you like to do?");
            System.out.println("\na - Create a post");
            System.out.println("b - View posts");
            System.out.println("c - Go back");

            String classroomChoice = input.next();

            if (classroomChoice.equals("a")) {
                createPostInstructor();
            } else if (classroomChoice.equals("b")) {
                viewPostsInstructor();
            } else if (classroomChoice.equals("c")) {
                running = false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // MODIFIES: this, Classroom
    // EFFECTS: creates post based on user input and adds it to list of posts in associated classroom
    private void createPostInstructor() {
        System.out.println("Please enter the title of your post:");
        String postTitle = input.next();
        System.out.println("What would you like to write for your post?");
        String postBody = input.next();

        Post newPost = new Post(currentInstructor.getUsername(), postTitle, postBody);
        currentClassroom.addPost(newPost);
    }

    // EFFECTS: displays posts made and moves user based on input
    private void viewPostsInstructor() {
        Boolean running = true;
        while (running) {

            if (currentClassroom.getPosts().isEmpty()) {
                System.out.println("There are no posts.");
                break;
            }

            System.out.println("\nHere are the current posts:");
            printCurrentPosts();
            instructorVP();
            String command = input.next();

            if (command.equals("a")) {
                selectPostInstructor();
                running = false;
            } else if (command.equals("b")) {
                deletePost();
                running = false;
            } else if (command.equals("c")) {
                break;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // EFFECTS: displays posts in current classroom
    private void printCurrentPosts() {
        for (Post p : currentClassroom.getPosts()) {
            System.out.println("\n" + p.getUserWhoPosted() + " posted:");
            System.out.println(p.getPostTitle());
        }
    }

    // EFFECTS: displays available options for user in viewposts method
    private void instructorVP() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("a - Select post");
        System.out.println("b - Delete post");
        System.out.println("c - Go back");
    }

    // EFFECTS: sets current post based on instructor input
    private void selectPostInstructor() {
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
                    instructorPostView(currentPost);
                    running = false;
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes post of same title from classroom's post list
    private void deletePost() {
        Boolean running = true;
        while (running) {

            if (currentClassroom.getPosts().isEmpty()) {
                System.out.println("There are no posts.");
                running = false;
            }

            System.out.println("\nHere are the current posts:");
            for (Post p : currentClassroom.getPosts()) {
                System.out.println("\n" + p.getUserWhoPosted() + " posted:");
                System.out.println(p.getPostTitle());
            }
            System.out.println("Which post would you like to delete?");
            String command = input.next();

            for (Post p : currentClassroom.getPosts()) {
                if (command.equals(p.getPostTitle())) {
                    currentClassroom.getPosts().remove(p);
                    break;
                }
            }
            break;
        }
    }

    // EFFECTS: displays options for actions in post
    private void instructorPostView(Post p) {
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
            postViewOptionsInstructor();
            postViewChoicesInstructor();
        }
    }

    // EFFECTS: displays choices available in postView method
    private void postViewOptionsInstructor() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\na - Create a comment");
        System.out.println("b - Delete a comment");
        System.out.println("c - Go back");
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void postViewChoicesInstructor() {
        Boolean running = true;

        while (running) {
            String postViewChoice = input.next();

            if (postViewChoice.equals("a")) {
                createCommentInstructor();
                running = false;
            } else if (postViewChoice.equals("b")) {
                deleteCommentInstructor();
                running = false;
            } else if (postViewChoice.equals("c")) {
                viewPostsInstructor();
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // MODIFIES: this, Post
    // EFFECTS: creates new comment based on user input and adds it to associated Post
    private void createCommentInstructor() {
        System.out.println("What would you like to comment?");
        String enteredComment = input.next();
        Comment newComment = new Comment(currentInstructor.getUsername(),enteredComment);
        currentPost.addComment(newComment);
    }

    // MODIFIES: this
    // EFFECTS: deletes comment that matches String input
    private void deleteCommentInstructor() {
        System.out.println("Which comment would you like to delete?");
        String enteredComment = input.next();
        currentInstructor.deleteComment(currentPost,enteredComment);
    }

    // ~~~~~~~~~~ STUDENT BASED METHODS BELOW ~~~~~~~~~~

    // EFFECTS: moves user to appropriate method based on user input
    private void studentClassroomsView() {
        Boolean selectionValid = true;
        while (selectionValid) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("a - View classrooms");
            System.out.println("b - Exit program");
            String command = input.next();
            if (command.equals("a")) {
                viewClassroomStudent();
            } else if (command.equals("b")) {
                selectionValid = false;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }
    }

    // EFFECTS: displays classrooms made and prompts student to go into a classroom
    private void viewClassroomStudent() {
        System.out.println("\nType the classroom's ID would you like to view:");
        for (Classroom c : structure.getClassroomList()) {
            System.out.print("Classroom Name: " + c.getCourseName() + ", ");
            System.out.println("Classroom ID: " + c.getCourseID() + " ");
        }

        int viewWhichClassroom = input.nextInt();

        for (Classroom c : structure.getClassroomList()) {
            if (viewWhichClassroom == c.getCourseID()) {
                currentClassroom = c;
                classroomViewStudent(currentClassroom);
            }
        }
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void classroomViewStudent(Classroom c) {
        boolean running = true;

        while (running) {
            System.out.println("\nYou are in classroom: " + c.getCourseName());
            studentCVOptions();

            String classroomChoice = input.next();

            if (classroomChoice.equals("a")) {
                createPostStudent();
            } else if (classroomChoice.equals("b")) {
                createSubgroup();
            } else if (classroomChoice.equals("c")) {
                viewPostsStudent();
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
    private void createPostStudent() {
        System.out.println("Please enter the title of your post:");
        String postTitle = input.next();
        System.out.println("What would you like to write for your post?");
        String postBody = input.next();

        Post newPost = new Post(currentStudent.getUsername(), postTitle, postBody);
        currentClassroom.addPost(newPost);
    }

    // EFFECTS: displays posts made and moves user based on input
    private void viewPostsStudent() {
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
                selectPostStudent();
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
        String command = input.next();
        Subgroup newSubgroup = new Subgroup(command);
        currentClassroom.addSubgroup(newSubgroup);
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

            System.out.println("What would you like to do?");
            System.out.println("a - Send a message");
            System.out.println("b - Go back");
            String command = input.next();

            if (command.equals("a")) {
                createMessageStudent();
            }else if (command.equals("b")) {
                running = false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // MODIFIES: this, Subgroup
    // EFFECTS: creates new message based on user input and adds it to associated Subgroup
    private void createMessageStudent() {
        System.out.println("What would you like to say?");
        String message = input.next();
        Message newMessage = new Message(currentStudent.getUsername(),message);
        currentSubgroup.addMessage(newMessage);
    }

    // EFFECTS: sets current post based on instructor input
    private void selectPostStudent() {
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
                    studentPostView(currentPost);
                    running = false;
                }
            }
        }
    }

    // EFFECTS: displays options for actions in post
    private void studentPostView(Post p) {
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
            postViewOptionsStudent();
            postViewChoicesStudent();
        }
    }

    // EFFECTS: displays choices available in postView method
    private void postViewOptionsStudent() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\na - Create a comment");
        System.out.println("b - Go back");
    }

    // EFFECTS: moves user to appropriate method based on user input
    private void postViewChoicesStudent() {
        Boolean running = true;

        while (running) {
            String postViewChoice = input.next();

            if (postViewChoice.equals("a")) {
                createCommentStudent();
                running = false;
            } else if (postViewChoice.equals("b")) {
                viewPostsStudent();
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    // MODIFIES: this, Post
    // EFFECTS: creates new comment based on user input and adds it to associated Post
    private void createCommentStudent() {
        System.out.println("What would you like to comment?");
        String enteredComment = input.next();
        Comment newComment = new Comment(currentStudent.getUsername(),enteredComment);
        currentPost.addComment(newComment);
    }
}
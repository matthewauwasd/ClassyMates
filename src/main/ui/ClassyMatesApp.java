//package ui;
//
//import model.Classroom;
//import model.Comment;
//import model.Post;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Scanner;
//
//// ClassyMates application
//public class ClassyMatesApp {
//    private Scanner input;
//    private List<Classroom> classroomList = new ArrayList<>();
//    private Classroom currentClassroom;
//    private Post currentPost;
//
//    // EFFECTS: runs ClassyMates application
//    public ClassyMatesApp() {
//        runClassyMates();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user commands
//    private void runClassyMates() {
//        boolean running = true;
//        String command = null;
//
//        init();
//
//        while (running) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//            if (command.equals("d")) {
//                running = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes scanner
//    private void init() {
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    // EFFECTS: displays initial options to user
//    private void displayMenu() {
//        System.out.println("\nWhat would you like to do?\n");
//        System.out.println("a - Create a classroom.");
//        System.out.println("b - View a classroom.");
//        System.out.println("c - Exit the program.");
//    }
//
//    // EFFECTS: moves user to appropriate method based on user input
//    private void processCommand(String command) {
//
//        if (command.equals("a")) {
//            createClassroom();
//        } else if (command.equals("b")) {
//            viewClassroom();
//        } else {
//            System.out.println("Selection not valid...\n");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates a new classroom
//    private void createClassroom() {
//        boolean running = true;
//        while (running) {
//            String className = "";
//            int classID = 0;
//
//            className = checkIfString();
//            classID = checkIfInt();
//
//            Classroom classroom = new Classroom(className, classID);
//            classroomList.add(classroom);
//
//            running = false;
//        }
//    }
//
//    // EFFECTS: checks if user input is String and not visually empty
//    private String checkIfString() {
//        String className = "";
//        boolean isString = true;
//
//        while (isString) {
//            System.out.println("\nPlease give your classroom a name:");
//
//            className = input.next().trim();
//
//            if (className.isEmpty()) {
//                System.out.println("Please enter a valid classroom name.");
//            } else {
//                isString = false;
//            }
//        }
//        return className;
//    }
//
//    // EFFECTS: checks if user input is int
//    private int checkIfInt() {
//        int classID = 0;
//        boolean isInt = true;
//
//        while (isInt) {
//            System.out.println("\nPlease give your classroom a class ID:");
//
//            if (input.hasNextInt()) {
//                classID = input.nextInt();
//                isInt = false;
//            } else {
//                System.out.println("Please enter a valid ID.");
//                input.next();
//            }
//        }
//        return classID;
//    }
//
//    // EFFECTS: displays classrooms made and prompts user to go into a classroom
//    private void viewClassroom() {
//        System.out.println("\nType the classroom's ID would you like to view:");
//        for (Classroom c : classroomList) {
//            System.out.print("Classroom Name: " + c.getCourseName() + ", ");
//            System.out.println("Classroom ID: " + c.getCourseID() + " ");
//        }
//
//        int viewWhichClassroom = input.nextInt();
//
//        for (Classroom c : classroomList) {
//            if (viewWhichClassroom == c.getCourseID()) {
//                currentClassroom = c;
//                classroomView(currentClassroom);
//            }
//        }
//    }
//
//    // EFFECTS: moves user to appropriate method based on user input
//    private void classroomView(Classroom c) {
//        boolean running = true;
//
//        while (running) {
//            System.out.println("\nYou are in classroom: " + c.getCourseName());
//            System.out.println("What would you like to do?");
//            System.out.println("\na - Create a post");
//            System.out.println("b - View posts");
//            System.out.println("c - Go back");
//
//            String classroomChoice = input.next();
//
//            if (classroomChoice.equals("a")) {
//                createPost();
//            } else if (classroomChoice.equals("b")) {
//                viewPosts();
//            } else if (classroomChoice.equals("c")) {
//                runClassyMates();
//            } else {
//                System.out.println("Please enter a valid input.");
//            }
//        }
//    }
//
//    // MODIFIES: this, Classroom
//    // EFFECTS: creates post based on user input and adds it to list of posts in associated classroom
//    private void createPost() {
//        System.out.println("Please enter the title of your post:");
//        String postTitle = input.next();
//        System.out.println("What would you like to write for your post?");
//        String postBody = input.next();
//
//        Post newPost = new Post(postTitle,postBody);
//        currentClassroom.addPost(newPost);
//    }
//
//    // EFFECTS: displays posts made and prompts user to view a specific post
//    private void viewPosts() {
//        System.out.println("\nHere are the current posts:");
//        for (Post p : currentClassroom.getPosts()) {
//            System.out.println(p.getPostTitle());
//        }
//
//        System.out.println("\nWhich post would you like to view?");
//        String viewWhichPost = input.next();
//
//        for (Post p : currentClassroom.getPosts()) {
//            if (viewWhichPost.equals(p.getPostTitle())) {
//                currentPost = p;
//                postView(currentPost);
//            }
//        }
//    }
//
//    // EFFECTS: displays post title, body, and comments associated with post,
//    // moves user to appropriate method based on user input
//    private void postView(Post p) {
//        boolean running = true;
//
//        while (running) {
//            System.out.println("\nYou are viewing post: " + p.getPostTitle());
//
//            System.out.println("\nPost Title: " + p.getPostTitle());
//            System.out.println("\n" + p.getPostBody());
//
//            System.out.println("\n\nComments:");
//            if (p.getComments().isEmpty()) {
//                System.out.println("There are currently no comments.");
//            } else {
//                for (Comment c : p.getComments()) {
//                    System.out.println(c.getCommentBody());
//                }
//            }
//            postViewOptions();
//
//            postViewChoices();
//        }
//    }
//
//    // MODIFIES: this, Post
//    // EFFECTS: creates new comment based on user input and adds it to associated Post
//    private void createComment() {
//        System.out.println("What would you like to comment?");
//        String enteredComment = input.next();
//        Comment newComment = new Comment(enteredComment);
//        currentPost.addComment(newComment);
//    }
//
//    // EFFECTS: displays choices available in postView method
//    private void postViewOptions() {
//        System.out.println("\nWhat would you like to do?");
//        System.out.println("\na - Create a comment");
//        System.out.println("b - Delete a comment");
//        System.out.println("c - Go back");
//    }
//
//    // EFFECTS: moves user to appropriate method based on user input
//    private void postViewChoices() {
//        String classroomChoice = input.next();
//
//        if (classroomChoice.equals("a")) {
//            createComment();
//        } else if (classroomChoice.equals("b")) {
//            deleteComment();
//        } else if (classroomChoice.equals("c")) {
//            classroomView(currentClassroom);
//        } else {
//            System.out.println("Please enter a valid input.");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: deletes comment that matches String input
//    private void deleteComment() {
//        System.out.println("Which comment would you like to delete?");
//        String enteredComment = input.next();
//        Comment newComment = new Comment(enteredComment);
//        currentPost.deleteComment(enteredComment);
//    }
//
//}

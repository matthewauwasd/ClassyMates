package ui;

import model.Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ClassyMatesApp {
    private Scanner input;
    private List<Classroom> classroomList = new ArrayList<>();
    private Classroom currentClassroom;

    public ClassyMatesApp() {
        runClassyMates();
    }

    private void runClassyMates() {
        boolean running = true;
        String command = null;

        init();

        while (running) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("c")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("a - Create a classroom.");
        System.out.println("b - View a classroom.");
        System.out.println("c - Exit the program.");
    }

    private void processCommand(String command) {

        if (command.equals("a")) {
            createClassroom();
        } else if (command.equals("b")) {
            viewClassroom();
        } else {
            System.out.println("Selection not valid...\n");
        }
    }

    private void createClassroom() {
        boolean running = true;
        while (running) {
            String className = "";
            int classID = 0;

            className = checkIfString();
            classID = checkIfInt();

            Classroom classroom = new Classroom(className, classID);
            classroomList.add(classroom);

            running = false;
        }
    }

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

    private int checkIfInt() {
        int classID = 0;
        boolean isInt = true;

        while (isInt) {
            System.out.println("\nPlease give your classroom a class ID:");

            if (input.hasNextInt()) {
                classID = input.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid ID.");
                input.next();
            }
        }
        return classID;
    }

    private void viewClassroom() {
        System.out.println("\nType the classroom's ID would you like to view:");
        for (Classroom c : classroomList) {
            System.out.print("Classroom Name: " + c.getCourseName() + ", ");
            System.out.println("Classroom ID: " + c.getCourseID() + " ");
        }

        int viewWhichClassroom = input.nextInt();

        for (Classroom c : classroomList) {
            if (viewWhichClassroom == c.getCourseID()) {
                currentClassroom = c;
                classroomView(currentClassroom);
            }
        }
        System.out.println("Please enter a valid classroom ID.");
    }

    private void classroomView(Classroom c) {
        String classroomChoice = "";
        System.out.println("You are in classroom: " + c.getCourseName());
        System.out.println("What would you like to do?");
        System.out.println("\na - Create a post");
        System.out.println("b - View posts");
        System.out.println("c - Go back");

        classroomChoice = input.next();
    }

}

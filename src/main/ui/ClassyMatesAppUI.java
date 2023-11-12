package ui;

import model.*;
import model.user.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// ClassyMates application
public class ClassyMatesAppUI extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private JFrame frame;

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
    private Classroom modelClassroom5 = new Classroom("CPSC 310", 310);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs ClassyMates application
    public ClassyMatesAppUI() {
        frame = new JFrame("ClassyMates");
        structure = new Structure();
        jsonWriter = new JsonWriter(JSON_STRUCTURE);
        jsonReader = new JsonReader(JSON_STRUCTURE);
        structure.addClassroom(modelClassroom1);
        structure.addClassroom(modelClassroom2);
        structure.addClassroom(modelClassroom3);
        structure.addClassroom(modelClassroom4);
        structure.addClassroom(modelClassroom5);

        runClassyMates();
    }

    // MODIFIES: this
    // EFFECTS: moves user to appropriate view based on user type
    private void runClassyMates() {
        initGUI();
        displayUserCreation();
    }

    // MODIFIES: this
    // EFFECTS:  displays GUI window
    private void initGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        frame.setJMenuBar(createMenu());
        frame.add(emptyPanel, BorderLayout.LINE_START);
        frame.add(classroomButtons(), BorderLayout.CENTER);

        //Display the window.
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays initial options to user
    private void displayUserCreation() {

        String enteredUsername = JOptionPane.showInputDialog(frame, "\nHi, please make an account!\n"
                        + "Please enter a username:");

        String enteredPassword = JOptionPane.showInputDialog(frame, "Please enter a password:");

        currentStudent = new Student(enteredUsername, enteredPassword, "Student");
    }

    // EFFECTS: creates button for each classroom
    public JScrollPane classroomButtons() {
        JPanel panel = new JPanel();
        for (Classroom c : structure.getClassroomList()) {
            JButton button = new JButton(c.getCourseName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClassroomUI currentClassroom = new ClassroomUI(c, currentStudent);
                }
            });
            panel.add(button);
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);

        return scrollPane;
    }

    // MODIFIES: this
    // EFFECTS: creates menu bar with save and load buttons
    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JButton saveButton = createSaveButton();
        menuBar.add(saveButton);

        JButton loadButton = createLoadButton();
        menuBar.add(loadButton);

        setJMenuBar(menuBar);

        return menuBar;
    }

    // MODIFIES: this
    // EFFECTS: creates a button that saves the structure to file
    public JButton createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: saving action
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(structure);
                    jsonWriter.close();
                    System.out.println("Saved data to " + JSON_STRUCTURE);
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STRUCTURE);
                }
            }
        });
        return saveButton;
    }

    // MODIFIES: this
    // EFFECTS: creates a button that loads the saved structure from file
    public JButton createLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: loading action that replaces the existing JFrame with a new one with loaded data
            public void actionPerformed(ActionEvent e) {
                try {
                    structure = jsonReader.read();
                    frame.dispose();
                    frame = new JFrame("ClassyMates");
                    initGUI();
                    System.out.println("Loaded data from " + JSON_STRUCTURE);
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_STRUCTURE);
                }
            }
        });
        return loadButton;
    }
}
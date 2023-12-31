package ui;

import model.Classroom;
import model.Post;
import model.Subgroup;
import model.user.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a classroom GUI
public class ClassroomUI extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private Classroom currentClassroom;
    private JFrame classroomFrame;
    private JFrame classroomSubgroupFrame;
    private Student currentStudent;

    // EFFECTS: creates classroom UI with two JFrames
    public ClassroomUI(Classroom classroom, Student student) {
        currentClassroom = classroom;
        currentStudent = student;
        classroomFrame = new JFrame(classroom.getCourseName());
        classroomSubgroupFrame = new JFrame(classroom.getCourseName());

        initializeGUI();
    }

    // MODIFIES: this
    // EFFECTS:  displays classroom GUI window
    private void initializeGUI() {
        //Create and set up the window.

        classroomFrame.add(sideButtons(), BorderLayout.LINE_START);
        classroomFrame.add(postButtons(), BorderLayout.CENTER);
        classroomSubgroupFrame.add(sideButtonsSG(), BorderLayout.LINE_START);
        classroomSubgroupFrame.add(subgroupButtons(), BorderLayout.CENTER);


        //Display the window.
        classroomFrame.setSize(WIDTH, HEIGHT);
        classroomSubgroupFrame.setSize(WIDTH, HEIGHT);
        classroomFrame.setVisible(true);
        classroomSubgroupFrame.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS:  displays classroom GUI with subgroups visible and not posts
    private void subgroupViewVisibility() {
        classroomFrame.setVisible(false);
        classroomSubgroupFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a JScrollPane containing a button for each classroom
    public JScrollPane postButtons() {
        JPanel panel = new JPanel();
        for (Post p : currentClassroom.getPosts()) {
            JButton button = new JButton(p.getPostTitle());
            button.addActionListener(new ActionListener() {
                @Override
                // EFFECTS: creates post UI for current post
                public void actionPerformed(ActionEvent e) {
                    PostUI currentPost = new PostUI(p, currentStudent);
                }
            });
            panel.add(button);
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);

        return scrollPane;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a JScrollPane containing a button for each subgroup
    public JScrollPane subgroupButtons() {
        JPanel panel = new JPanel();
        for (Subgroup sg : currentClassroom.getSubgroups()) {
            JButton button = new JButton(sg.getSubgroupName());
            button.addActionListener(new ActionListener() {
                @Override
                // EFFECTS: creates subgroup UI for current subgroup
                public void actionPerformed(ActionEvent e) {
                    SubgroupUI currentSubgroup = new SubgroupUI(sg, currentStudent);
                }
            });
            panel.add(button);
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPane = new JScrollPane(panel);

        return scrollPane;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a JPanel containing buttons that go on the left of posts
    public JPanel sideButtons() {
        JPanel sideButtonPanel = new JPanel();
        sideButtonPanel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.PAGE_AXIS));
        JButton createPost = createPostButton();
        JButton viewSubgroups = createViewSubgroupsButton();
        JButton goBack = createGoBackButton();


        sideButtonPanel.add(createPost);
        sideButtonPanel.add(viewSubgroups);
        sideButtonPanel.add(goBack);
        return sideButtonPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns JButton that prompts user to create a post
    public JButton createPostButton() {
        JButton createPost = new JButton("Create post");
        createPost.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: calls create post method and removes current JFrame
            public void actionPerformed(ActionEvent e) {
                createPost();
                classroomFrame.dispose();
            }
        });

        return createPost;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns JButton that changes JFrame to display subgroups
    public JButton createViewSubgroupsButton() {
        JButton viewSubgroups = new JButton("View subgroups");
        viewSubgroups.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: changes visibility of JFrames
            public void actionPerformed(ActionEvent e) {
                subgroupViewVisibility();
            }
        });

        return viewSubgroups;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a JPanel containing appropriate buttons for viewing subgroups
    public JPanel sideButtonsSG() {
        JPanel sideButtonPanel = new JPanel();
        sideButtonPanel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.PAGE_AXIS));
        JButton createSubgroup = createSubgroupButton();
        JButton goBackSG = createGoBackButtonSG();

        sideButtonPanel.add(createSubgroup);
        sideButtonPanel.add(goBackSG);
        return sideButtonPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns go back JButton that disposes current JFrame
    public JButton createGoBackButton() {
        JButton goBack = new JButton("Go back");
        goBack.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: disposes current JFrame
            public void actionPerformed(ActionEvent e) {
                classroomFrame.dispose();
            }
        });

        return goBack;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns go back JButton that changes visibility of relevant JFrames
    public JButton createGoBackButtonSG() {
        JButton goBackSG = new JButton("Go back");
        goBackSG.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: sets current JFrame as invisible and previous JFrame as visible
            public void actionPerformed(ActionEvent e) {
                classroomSubgroupFrame.setVisible(false);
                classroomFrame.setVisible(true);
            }
        });

        return goBackSG;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns JButton that allows for creation of subgroup
    public JButton createSubgroupButton() {
        JButton createSubgroup = new JButton("Create subgroup");
        createSubgroup.addActionListener(new ActionListener() {
            @Override
            // EFFECTS: calls method to create subgroup, and disposes of current JFrame
            public void actionPerformed(ActionEvent e) {
                createSubgroup();
                classroomSubgroupFrame.dispose();
            }
        });

        return createSubgroup;
    }

    // MODIFIES: this
    // EFFECTS: prompts user to create post and creates new instance of classroom UI
    public void createPost() {
        String postTitle = JOptionPane.showInputDialog(classroomFrame, "Please enter the title of your post:");
        String postBody = JOptionPane.showInputDialog(classroomFrame,
                "What would you like to write for your post?");
        currentClassroom.addPost(currentStudent.createPost(postTitle, postBody));
        new ClassroomUI(currentClassroom, currentStudent);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to create post and creates new instance of classroom UI with appropriate visibility
    public void createSubgroup() {
        String subgroupName = JOptionPane.showInputDialog(classroomSubgroupFrame,
                "What would you like to call your subgroup?");
        currentClassroom.addSubgroup(currentStudent.createSubgroup(subgroupName));
        ClassroomUI newInstance = new ClassroomUI(currentClassroom, currentStudent);
        newInstance.subgroupViewVisibility();
    }

}

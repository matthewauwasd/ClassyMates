package ui;

import model.Message;
import model.Subgroup;
import model.user.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubgroupUI {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private Subgroup currentSubgroup;
    private Student currentStudent;
    private JFrame subgroupFrame;

    public SubgroupUI(Subgroup sg, Student s) {

    currentSubgroup = sg;
    currentStudent = s;
    subgroupFrame = new JFrame(sg.getSubgroupName());

    initializeGUI();
}

    // MODIFIES: this
    // EFFECTS:  displays GUI window
    private void initializeGUI() {
        //Create and set up the window.

        subgroupFrame.add(sideButtons(), BorderLayout.LINE_START);
        subgroupFrame.add(subgroupDetails(), BorderLayout.CENTER);

        //Display the window.
        subgroupFrame.setSize(WIDTH, HEIGHT);
        subgroupFrame.setVisible(true);
    }

    public JPanel subgroupDetails() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(2 * (WIDTH / 3), HEIGHT));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(createSubgroupPanel());
        mainPanel.add(createMessagePanel());

        return mainPanel;
    }

    public JPanel createSubgroupPanel() {
        JPanel subgroupInfoPanel = new JPanel();
        subgroupInfoPanel.setPreferredSize(new Dimension(2 * (WIDTH / 3), HEIGHT / 5));
        subgroupInfoPanel.setMinimumSize(new Dimension(2 * (WIDTH / 3), HEIGHT / 5));
        subgroupInfoPanel.setMaximumSize(new Dimension(2 * (WIDTH / 3), HEIGHT / 5));
        subgroupInfoPanel.setLayout(new BoxLayout(subgroupInfoPanel, BoxLayout.PAGE_AXIS));
        JTextArea subgroupName = new JTextArea(currentSubgroup.getSubgroupName());
        subgroupName.setEditable(false);
        subgroupInfoPanel.add(subgroupName);

        return subgroupInfoPanel;
    }

    public JScrollPane createMessagePanel() {
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.PAGE_AXIS));
        for (Message m : currentSubgroup.getMessages()) {
            JPanel messageAndAuthor = new JPanel();
            messageAndAuthor.setLayout(new BoxLayout(messageAndAuthor, BoxLayout.PAGE_AXIS));
            messageAndAuthor.add(new JTextArea(m.getUserWhoPosted() + " posted:"));
            messageAndAuthor.add(new JTextArea(m.getMessageBody() + "\n"));
            messagesPanel.add(messageAndAuthor);
        }
        JScrollPane scrollPane = new JScrollPane(messagesPanel);

        return scrollPane;
    }

    public JPanel sideButtons() {
        JPanel sideButtonPanel = new JPanel();
        sideButtonPanel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.PAGE_AXIS));
        JButton createPost = new JButton("Create comment");
        JButton goBack = new JButton("Go back");
        createPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMessage();
                subgroupFrame.dispose();
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subgroupFrame.dispose();
            }
        });
        sideButtonPanel.add(createPost);
        sideButtonPanel.add(goBack);
        return sideButtonPanel;
    }

    public void createMessage() {

        String enteredMessage = JOptionPane.showInputDialog(subgroupFrame, "What would you like to say?");

        currentSubgroup.addMessage(currentStudent.createMessage(enteredMessage));
        new SubgroupUI(currentSubgroup, currentStudent);
    }
}

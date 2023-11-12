package ui;

import model.Comment;
import model.Post;
import model.user.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostUI {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private Student currentStudent;
    private Post currentPost;
    private JFrame postFrame;

    public PostUI(Post p, Student s) {
        currentPost = p;
        currentStudent = s;
        postFrame = new JFrame(p.getPostTitle());

        initializeGUI();
    }

    // MODIFIES: this
    // EFFECTS:  displays GUI window
    private void initializeGUI() {
        //Create and set up the window.

        postFrame.add(sideButtons(), BorderLayout.LINE_START);
        postFrame.add(postDetails(), BorderLayout.CENTER);

        //Display the window.
        postFrame.setSize(WIDTH, HEIGHT);
        postFrame.setVisible(true);
    }

    public JPanel postDetails() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(2 * (WIDTH / 3), HEIGHT));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(createPostPanel());
        mainPanel.add(createCommentsPanel());

        return mainPanel;
    }

    public JPanel createPostPanel() {
        JPanel postPanel = new JPanel();
        JTextArea postTitle = new JTextArea(currentPost.getPostTitle());
        JTextArea postBody = new JTextArea(currentPost.getPostBody());
        int postTitleFontSize = 30;
        postTitle.setFont(postTitle.getFont().deriveFont((float)postTitleFontSize));
        postPanel.setPreferredSize(new Dimension(2 * (WIDTH / 3), 2 * postTitleFontSize));
        postPanel.setMinimumSize(new Dimension(2 * (WIDTH / 3), 2 * postTitleFontSize));
        postPanel.setMaximumSize(new Dimension(2 * (WIDTH / 3), 2 * postTitleFontSize));
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.PAGE_AXIS));

        postTitle.setEditable(false);
        postBody.setEditable(false);
        postPanel.add(postTitle);
        postPanel.add(postBody);

        return postPanel;
    }

    public JScrollPane createCommentsPanel() {
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.PAGE_AXIS));
        for (Comment c : currentPost.getComments()) {
            JPanel commentAndAuthor = new JPanel();
            commentAndAuthor.setLayout(new BoxLayout(commentAndAuthor, BoxLayout.PAGE_AXIS));
            commentAndAuthor.add(new JTextArea(c.getUserWhoPosted() + " posted:"));
            commentAndAuthor.add(new JTextArea(c.getCommentBody() + "\n"));
            commentsPanel.add(commentAndAuthor);
        }
        JScrollPane scrollPane = new JScrollPane(commentsPanel);

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
                createComment();
                postFrame.dispose();
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postFrame.dispose();
            }
        });
        sideButtonPanel.add(createPost);
        sideButtonPanel.add(goBack);
        return sideButtonPanel;
    }

    public void createComment() {

        String enteredComment = JOptionPane.showInputDialog(postFrame, "What would you like to comment?");

        currentPost.addComment(currentStudent.createComment(enteredComment));
        new PostUI(currentPost, currentStudent);
    }

}

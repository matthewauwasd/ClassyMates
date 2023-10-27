package persistence;

import model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkStructure(List<Classroom> classrooms, Structure str) {
        assertEquals(classrooms, str.getClassroomList());
    }

    protected void checkClassroom(String courseName, int courseID, List<Post> posts,
                                  List<Subgroup> subgroups, Classroom cl) {
        assertEquals(courseName, cl.getCourseName());
        assertEquals(courseID, cl.getCourseID());
        assertEquals(posts, cl.getPosts());
        assertEquals(subgroups, cl.getSubgroups());
    }

    protected void checkPost(String postTitle, String postBody, String userWhoPosted, List<Comment> comments, Post p) {
        assertEquals(postTitle, p.getPostTitle());
        assertEquals(postBody, p.getPostBody());
        assertEquals(userWhoPosted, p.getUserWhoPosted());
        assertEquals(comments, p.getComments());
    }

    protected void checkComment(String userWhoPosted, String commentBody, Comment comment) {
        assertEquals(userWhoPosted, comment.getUserWhoPosted());
        assertEquals(commentBody, comment.getCommentBody());
    }

    protected void checkSubgroup(String subgroupName, List<Message> messages, Subgroup sg) {
        assertEquals(subgroupName, sg.getSubgroupName());
        assertEquals(messages, sg.getMessages());
    }

    protected void checkMessage(String userWhoPosted, String messageBody, Message message) {
        assertEquals(userWhoPosted, message.getUserWhoPosted());
        assertEquals(messageBody, message.getMessageBody());
    }
}

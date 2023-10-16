package model.user;

import model.Classroom;
import model.Comment;
import model.Post;

import java.util.List;

// Represents an instructor with a username, password, and user type of instructor.
// Has basic user privileges as well as those specific to instructors.
public class Instructor extends User {

    public Instructor(String username, String password, String userType) {
        super(username,password,userType);
    }

    // MODIFIES: Classroom
    // EFFECTS: creates and returns new classroom with classroom name and classroom ID
    public void createClassroom(List<Classroom> classroomList, String classroomName, int classroomID) {
        Classroom newClassroom = new Classroom(classroomName,classroomID);
        classroomList.add(newClassroom);
    }

    // MODIFIES: Classroom
    // EFFECTS: removes Post from Classroom if entered post title matches post title in list of posts
    public void deletePost(Classroom currentClass, String postTitle) {
        for (int i = 0; i < currentClass.getPosts().size(); i++) {
            Post currentPost = currentClass.getPosts().get(i);
            if (postTitle.equals(currentPost.getPostTitle())) {
                currentClass.getPosts().remove(currentPost);
                i--;
            }
        }
    }

    // MODIFIES: Post
    // EFFECTS: removes Comment from Post if entered comment body matches comment body in list of comments
    public void deleteComment(Post currentPost, String commentBody) {
        for (int i = 0; i < currentPost.getComments().size(); i++) {
            Comment currentComment = currentPost.getComments().get(i);
            if (commentBody.equals(currentComment.getCommentBody())) {
                currentPost.getComments().remove(currentComment);
                i--;
            }
        }
    }

}

package model.user;

import model.Classroom;
import model.Comment;
import model.Post;

import java.util.List;

public class Instructor extends User {

    public Instructor(String username, String password, String userType) {
        super(username,password,userType);
    }

    public void createClassroom(List<Classroom> classroomList, String classroomName, int classroomID) {
        Classroom newClassroom = new Classroom(classroomName,classroomID);
        classroomList.add(newClassroom);
    }

    public void deletePost(Classroom currentClass, String postTitle) {
        for (int i = 0; i < currentClass.getPosts().size(); i++) {
            Post currentPost = currentClass.getPosts().get(i);
            if (postTitle.equals(currentPost.getPostTitle())) {
                currentClass.getPosts().remove(currentPost);
                i--;
            }
        }
    }

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

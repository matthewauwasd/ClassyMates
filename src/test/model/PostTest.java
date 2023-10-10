package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostTest {
    private Post post1;
    private String post1Title = "Question Title?";
    private String post1Body = "I have a question...";
    private Comment comment1;
    private String comment1Body = "I do not know the answer.";
    private Comment comment2;
    private String comment2Body = "The answer is...";

    @BeforeEach
    void runBefore() {
        post1 = new Post(post1Title,post1Body);
        comment1 = new Comment(comment1Body);
        comment2 = new Comment(comment2Body);
    }

    @Test
    void testConstructor() {
        assertEquals("Question Title?",post1.getPostTitle());
        assertEquals("I have a question...",post1.getPostBody());
        List<Comment> commentList = post1.getComments();
        assertTrue(commentList.isEmpty());
    }

    @Test
    void testAddComment() {
        post1.addComment(comment1);
        List<Comment> commentList = post1.getComments();
        assertEquals(comment1,commentList.get(0));
        assertEquals(1,commentList.size());
    }

    @Test
    void testAddTwoComments() {
        post1.addComment(comment1);
        post1.addComment(comment2);
        List<Comment> commentList = post1.getComments();
        assertEquals(2,commentList.size());
        assertEquals(comment1,commentList.get(0));
        assertEquals(comment2,commentList.get(1));
    }

    @Test
    void testAddTwoCommentsInDifferentOrder() {
        post1.addComment(comment2);
        post1.addComment(comment1);
        List<Comment> commentList = post1.getComments();
        assertEquals(2,commentList.size());
        assertEquals(comment2,commentList.get(0));
        assertEquals(comment1,commentList.get(1));
    }

    @Test
    void testDeleteComment() {
        post1.addComment(comment2);
        post1.addComment(comment1);
        List<Comment> commentList = post1.getComments();
        post1.deleteComment("I do not know the answer.");
        assertEquals(comment2,commentList.get(0));
        assertEquals(1,commentList.size());
    }

    @Test
    void testDeleteTwoComment() {
        post1.addComment(comment1);
        post1.addComment(comment2);
        List<Comment> commentList = post1.getComments();
        post1.deleteComment("I do not know the answer.");
        post1.deleteComment("The answer is...");
        assertTrue(commentList.isEmpty());
    }

    @Test
    void testDeleteCommentInEmptyList() {
        List<Comment> commentList = post1.getComments();
        post1.deleteComment("I do not know the answer.");
        assertTrue(commentList.isEmpty());
    }

}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {
    private Comment comment1;
    private String comment1Body = "I'm a comment";

    @BeforeEach
    void runBefore() {
        comment1 = new Comment(comment1Body);
    }

    @Test
    void testConstructor() {
        assertEquals("I'm a comment",comment1.getCommentBody());
    }

}

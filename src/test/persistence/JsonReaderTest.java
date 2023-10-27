package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Structure str = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStructure() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyStructure.json");
        try {
            Structure str = reader.read();
            assertEquals(0, str.getClassroomList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderStructureClassroom() {
        JsonReader reader = new JsonReader("./data/testWriterStructureClassroom.json");
        try {
            Structure str = reader.read();
            List<Classroom> classrooms = str.getClassroomList();
            Classroom classroom1 = classrooms.get(0);
            List<Post> posts1 = classroom1.getPosts();
            List<Subgroup> subgroups1 = classroom1.getSubgroups();
            assertEquals(1, classrooms.size());
            checkClassroom("CPSC 210", 210,posts1,subgroups1,classroom1);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderStructureMultipleClassroom() {
        JsonReader reader = new JsonReader("./data/testWriterStructureMultipleClassroom.json");
        try {
            Structure str = reader.read();
            List<Classroom> classrooms = str.getClassroomList();
            assertEquals(2, classrooms.size());
            Classroom classroom = classrooms.get(0);
            Classroom classroom2 = classrooms.get(1);
            checkClassroom("CPSC 210",210,classroom.getPosts(),classroom.getSubgroups(),classroom);
            checkClassroom("CPSC 110",110,classroom2.getPosts(),classroom2.getSubgroups(),classroom2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClassroomPostNoSubgroup() {
        JsonReader reader = new JsonReader("./data/testWriterClassroomPostNoSubgroup.json");
        try {
            Classroom cl = new Classroom("CPSC 210",210);
            Structure str = reader.read();
            str.addClassroom(cl);
            cl.addPost(new Post("User", "Post Title", "Post Body"));
            List<Post> posts = cl.getPosts();
            assertEquals(1, posts.size());
            Post post = posts.get(0);
            List<Comment> comments = post.getComments();
            checkPost("Post Title","Post Body","User",comments,post);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClassroomTwoPostsNoSubgroup() {
        JsonReader reader = new JsonReader("./data/testWriterClassroomTwoPostsNoSubgroup.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title","Post Body"));
            cl.addPost(new Post("User","Post Title2","Post Body2"));
            List<Post> posts = cl.getPosts();
            assertEquals(2, posts.size());
            Post post1 = posts.get(0);
            Post post2 = posts.get(1);
            List<Comment> comments1 = post1.getComments();
            List<Comment> comments2 = post2.getComments();
            checkPost("Post Title","Post Body","User",comments1,post1);
            checkPost("Post Title2","Post Body2","User",comments2,post2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClassroomSubgroupNoPost() {
        JsonReader reader = new JsonReader("./data/testWriterClassroomSubgroupNoPost.json");

        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addSubgroup(new Subgroup("Subgroup1"));
            List<Subgroup> subgroups = cl.getSubgroups();
            assertEquals(1, subgroups.size());
            Subgroup subgroup1 = subgroups.get(0);
            List<Message> messages = subgroup1.getMessages();
            checkSubgroup("Subgroup1",messages,subgroup1);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClassroomSubgroupTwoNoPost() {
        JsonReader reader = new JsonReader("./data/testWriterClassroomSubgroupNoPost.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addSubgroup(new Subgroup("Subgroup1"));
            cl.addSubgroup(new Subgroup("Subgroup2"));
            List<Subgroup> subgroups = cl.getSubgroups();
            assertEquals(2, subgroups.size());
            Subgroup subgroup1 = subgroups.get(0);
            Subgroup subgroup2 = subgroups.get(1);
            List<Message> messages1 = subgroup1.getMessages();
            List<Message> messages2 = subgroup2.getMessages();
            checkSubgroup("Subgroup1",messages1,subgroup1);
            checkSubgroup("Subgroup2",messages2,subgroup2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClassroomPostSubgroup() {
        JsonReader reader = new JsonReader("./data/testWriterClassroomPostSubgroup.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title","Post Body"));
            cl.addSubgroup(new Subgroup("Subgroup1"));
            List<Post> posts = cl.getPosts();
            assertEquals(1, posts.size());
            List<Subgroup> subgroups = cl.getSubgroups();
            assertEquals(1, subgroups.size());
            Post post1 = posts.get(0);
            Subgroup subgroup1 = subgroups.get(0);
            List<Comment> comments1 = post1.getComments();
            List<Message> messages1 = subgroup1.getMessages();
            checkPost("Post Title","Post Body","User",comments1,post1);
            checkSubgroup("Subgroup1",messages1,subgroup1);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderClassroomPostTwiceSubgroupTwice() {
        JsonReader reader = new JsonReader("./data/testWriterClassroomPostTwiceSubgroupTwice.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title1","Post Body1"));
            cl.addPost(new Post("User","Post Title2","Post Body2"));
            cl.addSubgroup(new Subgroup("Subgroup1"));
            cl.addSubgroup(new Subgroup("Subgroup2"));
            List<Post> posts = cl.getPosts();
            assertEquals(2, posts.size());
            List<Subgroup> subgroups = cl.getSubgroups();
            assertEquals(2, subgroups.size());
            Post post1 = posts.get(0);
            Post post2 = posts.get(1);
            Subgroup subgroup1 = subgroups.get(0);
            Subgroup subgroup2 = subgroups.get(1);
            List<Comment> comments1 = post1.getComments();
            List<Comment> comments2 = post2.getComments();
            List<Message> messages1 = subgroup1.getMessages();
            List<Message> messages2 = subgroup2.getMessages();
            checkPost("Post Title1","Post Body1","User",comments1,post1);
            checkPost("Post Title2","Post Body2","User",comments2,post2);
            checkSubgroup("Subgroup1",messages1,subgroup1);
            checkSubgroup("Subgroup2",messages2,subgroup2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderPostComment() {
        JsonReader reader = new JsonReader("./data/testWriterPostComment.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            Post p = new Post("User","Post Title","Post Body");
            str.addClassroom(cl);
            cl.addPost(p);
            p.addComment(new Comment("User1","Comment1"));
            List<Comment> comments = p.getComments();
            assertEquals(1, comments.size());
            Comment comment1 = comments.get(0);
            checkComment("User1","Comment1",comment1);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderPostCommentTwice() {
        JsonReader reader = new JsonReader("./data/testWriterPostCommentTwice.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            Post p = new Post("User","Post Title","Post Body");
            str.addClassroom(cl);
            cl.addPost(p);
            p.addComment(new Comment("User1","Comment1"));
            p.addComment(new Comment("User2","Comment2"));
            List<Comment> comments = p.getComments();
            assertEquals(2, comments.size());
            Comment comment1 = comments.get(0);
            Comment comment2 = comments.get(1);
            checkComment("User1","Comment1",comment1);
            checkComment("User2","Comment2",comment2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSubgroupMessage() {
        JsonReader reader = new JsonReader("./data/testWriterSubgroupMessage.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            Subgroup sg = new Subgroup("Subgroup1");
            str.addClassroom(cl);
            cl.addSubgroup(sg);
            sg.addMessage(new Message("User1","Message1"));
            List<Message> messages = sg.getMessages();
            assertEquals(1, messages.size());
            Message message1 = messages.get(0);
            checkMessage("User1","Message1",message1);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testWriterSubgroupMessageTwice() {
        JsonReader reader = new JsonReader("./data/testWriterSubgroupMessageTwice.json");
        try {
            Structure str = reader.read();
            Classroom cl = new Classroom("CPSC 210",210);
            Subgroup sg = new Subgroup("Subgroup1");
            str.addClassroom(cl);
            cl.addSubgroup(sg);
            sg.addMessage(new Message("User1","Message1"));
            sg.addMessage(new Message("User2","Message2"));
            List<Message> messages = sg.getMessages();
            assertEquals(2, messages.size());
            Message message1 = messages.get(0);
            Message message2 = messages.get(1);
            checkMessage("User1","Message1",message1);
            checkMessage("User2","Message2",message2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

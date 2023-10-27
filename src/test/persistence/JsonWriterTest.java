// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Structure str = new Structure();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Structure str = new Structure();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStructure.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStructure.json");
            str = reader.read();
            assertEquals(0, str.getClassroomList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterStructureClassroom() {
        try {
            Structure str = new Structure();
            str.addClassroom(new Classroom("CPSC 210",210));
            JsonWriter writer = new JsonWriter("./data/testWriterStructureClassroom.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStructureClassroom.json");
            str = reader.read();
            List<Classroom> classrooms = str.getClassroomList();
            assertEquals(1, classrooms.size());
            Classroom classroom = classrooms.get(0);
            checkClassroom("CPSC 210",210,classroom.getPosts(),classroom.getSubgroups(),classroom);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterStructureMultipleClassroom() {
        try {
            Structure str = new Structure();
            str.addClassroom(new Classroom("CPSC 210",210));
            str.addClassroom(new Classroom("CPSC 110",110));
            JsonWriter writer = new JsonWriter("./data/testWriterStructureMultipleClassroom.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStructureMultipleClassroom.json");
            str = reader.read();
            List<Classroom> classrooms = str.getClassroomList();
            assertEquals(2, classrooms.size());
            Classroom classroom = classrooms.get(0);
            Classroom classroom2 = classrooms.get(1);
            checkClassroom("CPSC 210",210,classroom.getPosts(),classroom.getSubgroups(),classroom);
            checkClassroom("CPSC 110",110,classroom2.getPosts(),classroom2.getSubgroups(),classroom2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClassroomPostNoSubgroup() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title","Post Body"));
            JsonWriter writer = new JsonWriter("./data/testWriterClassroomPostNoSubgroup.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClassroomPostNoSubgroup.json");
            str = reader.read();
            List<Post> posts = cl.getPosts();
            assertEquals(1, posts.size());
            Post post = posts.get(0);
            List<Comment> comments = post.getComments();
            checkPost("Post Title","Post Body","User",comments,post);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClassroomTwoPostsNoSubgroup() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title","Post Body"));
            cl.addPost(new Post("User","Post Title2","Post Body2"));
            JsonWriter writer = new JsonWriter("./data/testWriterClassroomTwoPostsNoSubgroup.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClassroomTwoPostsNoSubgroup.json");
            str = reader.read();
            List<Post> posts = cl.getPosts();
            assertEquals(2, posts.size());
            Post post1 = posts.get(0);
            Post post2 = posts.get(1);
            List<Comment> comments1 = post1.getComments();
            List<Comment> comments2 = post2.getComments();
            checkPost("Post Title","Post Body","User",comments1,post1);
            checkPost("Post Title2","Post Body2","User",comments2,post2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClassroomSubgroupNoPost() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addSubgroup(new Subgroup("Subgroup1"));
            JsonWriter writer = new JsonWriter("./data/testWriterClassroomSubgroupNoPost.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClassroomSubgroupNoPost.json");
            str = reader.read();
            List<Subgroup> subgroups = cl.getSubgroups();
            assertEquals(1, subgroups.size());
            Subgroup subgroup1 = subgroups.get(0);
            List<Message> messages = subgroup1.getMessages();
            checkSubgroup("Subgroup1",messages,subgroup1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClassroomSubgroupTwoNoPost() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addSubgroup(new Subgroup("Subgroup1"));
            cl.addSubgroup(new Subgroup("Subgroup2"));
            JsonWriter writer = new JsonWriter("./data/testWriterClassroomSubgroupNoPost.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClassroomSubgroupNoPost.json");
            str = reader.read();
            List<Subgroup> subgroups = cl.getSubgroups();
            assertEquals(2, subgroups.size());
            Subgroup subgroup1 = subgroups.get(0);
            Subgroup subgroup2 = subgroups.get(1);
            List<Message> messages1 = subgroup1.getMessages();
            List<Message> messages2 = subgroup2.getMessages();
            checkSubgroup("Subgroup1",messages1,subgroup1);
            checkSubgroup("Subgroup2",messages2,subgroup2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClassroomPostSubgroup() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title","Post Body"));
            cl.addSubgroup(new Subgroup("Subgroup1"));
            JsonWriter writer = new JsonWriter("./data/testWriterClassroomPostSubgroup.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClassroomPostSubgroup.json");
            str = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterClassroomPostTwiceSubgroupTwice() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            str.addClassroom(cl);
            cl.addPost(new Post("User","Post Title1","Post Body1"));
            cl.addPost(new Post("User","Post Title2","Post Body2"));
            cl.addSubgroup(new Subgroup("Subgroup1"));
            cl.addSubgroup(new Subgroup("Subgroup2"));
            JsonWriter writer = new JsonWriter("./data/testWriterClassroomPostTwiceSubgroupTwice.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClassroomPostTwiceSubgroupTwice.json");
            str = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterPostComment() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            Post p = new Post("User","Post Title","Post Body");
            str.addClassroom(cl);
            cl.addPost(p);
            p.addComment(new Comment("User1","Comment1"));
            JsonWriter writer = new JsonWriter("./data/testWriterPostComment.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPostComment.json");
            str = reader.read();
            List<Comment> comments = p.getComments();
            assertEquals(1, comments.size());
            Comment comment1 = comments.get(0);
            checkComment("User1","Comment1",comment1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterPostCommentTwice() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            Post p = new Post("User","Post Title","Post Body");
            str.addClassroom(cl);
            cl.addPost(p);
            p.addComment(new Comment("User1","Comment1"));
            p.addComment(new Comment("User2","Comment2"));
            JsonWriter writer = new JsonWriter("./data/testWriterPostCommentTwice.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPostCommentTwice.json");
            str = reader.read();
            List<Comment> comments = p.getComments();
            assertEquals(2, comments.size());
            Comment comment1 = comments.get(0);
            Comment comment2 = comments.get(1);
            checkComment("User1","Comment1",comment1);
            checkComment("User2","Comment2",comment2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSubgroupMessage() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            Subgroup sg = new Subgroup("Subgroup1");
            str.addClassroom(cl);
            cl.addSubgroup(sg);
            sg.addMessage(new Message("User1","Message1"));
            JsonWriter writer = new JsonWriter("./data/testWriterSubgroupMessage.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSubgroupMessage.json");
            str = reader.read();
            List<Message> messages = sg.getMessages();
            assertEquals(1, messages.size());
            Message message1 = messages.get(0);
            checkMessage("User1","Message1",message1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSubgroupMessageTwice() {
        try {
            Structure str = new Structure();
            Classroom cl = new Classroom("CPSC 210",210);
            Subgroup sg = new Subgroup("Subgroup1");
            str.addClassroom(cl);
            cl.addSubgroup(sg);
            sg.addMessage(new Message("User1","Message1"));
            sg.addMessage(new Message("User2","Message2"));
            JsonWriter writer = new JsonWriter("./data/testWriterSubgroupMessageTwice.json");
            writer.open();
            writer.write(str);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSubgroupMessageTwice.json");
            str = reader.read();
            List<Message> messages = sg.getMessages();
            assertEquals(2, messages.size());
            Message message1 = messages.get(0);
            Message message2 = messages.get(1);
            checkMessage("User1","Message1",message1);
            checkMessage("User2","Message2",message2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}

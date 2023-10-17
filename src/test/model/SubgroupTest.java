package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubgroupTest {

    private Subgroup subgroup1;
    private Subgroup subgroup2;
    private String subgroup1Name = "SG1";
    private String subgroup2Name = "SG2";

    @BeforeEach
    void runBefore() {
        subgroup1 = new Subgroup(subgroup1Name);
        subgroup2 = new Subgroup(subgroup2Name);
    }

    @Test
    void testConstructor() {
        assertEquals("SG1",subgroup1.getSubgroupName());
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
        assertTrue(subgroup1.getMessages().isEmpty());
        assertTrue(subgroup1.getListOfStudents().isEmpty());
    }

    @Test
    void testAddSubgroupInterestFirstInterest() {
        subgroup1.addSubgroupInterest("Anime");
        assertEquals("Anime",subgroup1.getSubgroupInterests().get(0));
        assertEquals(1,subgroup1.getSubgroupInterests().size());
    }

    @Test
    void testAddSubgroupInterestTwoInterests() {
        subgroup1.addSubgroupInterest("Tech");
        subgroup1.addSubgroupInterest("Anime");
        assertEquals("Tech",subgroup1.getSubgroupInterests().get(0));
        assertEquals("Anime",subgroup1.getSubgroupInterests().get(1));
        assertEquals(2,subgroup1.getSubgroupInterests().size());
    }

    @Test
    void testAddSubgroupInterestWrongInterest() {
        subgroup1.addSubgroupInterest("Sleeping");
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
    }

    @Test
    void testAddSubgroupInterestDuplicateInterest() {
        subgroup1.addSubgroupInterest("Cars");
        subgroup1.addSubgroupInterest("Cars");
        assertEquals("Cars",subgroup1.getSubgroupInterests().get(0));
        assertEquals(1,subgroup1.getSubgroupInterests().size());
    }

    @Test
    void testRemoveSubgroupInterest() {
        subgroup1.addSubgroupInterest("Cars");
        subgroup1.removeSubgroupInterest("Cars");
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
    }

    @Test
    void testRemoveSubgroupInterestNoValues() {
        subgroup1.removeSubgroupInterest("Cars");
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
    }

    @Test
    void testRemoveSubgroupInterestsWrongInterest() {
        subgroup1.addSubgroupInterest("Cars");
        subgroup1.removeSubgroupInterest("Sports");
        assertEquals("Cars",subgroup1.getSubgroupInterests().get(0));
        assertEquals(1,subgroup1.getSubgroupInterests().size());
    }

    @Test
    void testRemoveSubgroupInterestTwice() {
        subgroup1.addSubgroupInterest("Cars");
        subgroup1.addSubgroupInterest("Sports");
        subgroup1.removeSubgroupInterest("Cars");
        subgroup1.removeSubgroupInterest("Sports");
        assertTrue(subgroup1.getSubgroupInterests().isEmpty());
    }

}

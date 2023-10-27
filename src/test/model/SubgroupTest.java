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
        assertTrue(subgroup1.getMessages().isEmpty());
        assertTrue(subgroup1.getListOfStudents().isEmpty());
    }

}

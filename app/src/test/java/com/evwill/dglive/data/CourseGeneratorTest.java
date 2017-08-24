package com.evwill.dglive.data;

import com.evwill.dglive.models.Course;
import com.evwill.dglive.models.Hole;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class CourseGeneratorTest {

    CourseGenerator courseGenerator;

    @Before
    public void setUp() throws Exception {
        courseGenerator = new CourseGenerator();
    }

    @Test
    public void testCampgawBlacksHasCorrectCourseName() throws Exception {
        Course campgaw = courseGenerator.campgawBlacks();
        String courseName = "Campgaw Black";
        assertEquals(courseName, campgaw.getName());
    }

    @Test
    public void testCampgawBlacksReturnsCorrectSizeList() throws Exception {
        Course campgaw = courseGenerator.campgawBlacks();
        List<Hole> holes = campgaw.getHoles();
        assertThat(holes, hasSize(18));
    }

    @Test
    public void testCampgawBlacksHasCorrectParOnHoleTwo() throws Exception {
        Course campgaw = courseGenerator.campgawBlacks();
        List<Hole> holes = campgaw.getHoles();
        Hole hole = holes.get(1);
        assertEquals(hole.getPar(), 4);
    }

    @Test
    public void testCampgawBlacksHasCorrectParOnHoleTen() throws Exception {
        Course campgaw = courseGenerator.campgawBlacks();
        List<Hole> holes = campgaw.getHoles();
        Hole hole = holes.get(9);
        assertEquals(hole.getPar(), 3);
    }

    @Test
    public void testCampgawBlacksHasCorrectParOnHoleEighteen() throws Exception {
        Course campgaw = courseGenerator.campgawBlacks();
        List<Hole> holes = campgaw.getHoles();
        Hole hole = holes.get(17);
        assertEquals(hole.getPar(), 5);
    }



}
package com.evwill.dglive.data;

import com.evwill.dglive.models.Course;
import com.evwill.dglive.models.Hole;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CourseGenerator {

    public Course campgawBlacks() {
        Course course = new Course();
        course.setName("Campgaw Black");
        Integer[] parList = campgawBlackPars();
        course.setHoles(holeList(parList));
        return course;
    }

    public List<Hole> holeList(Integer[] parList) {
        List<Hole> holes = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            Hole hole = new Hole(String.valueOf(i), parList[i - 1]);
            holes.add(hole);
        }

        return holes;
    }

    private Integer[] campgawBlackPars() {
        return new Integer[]{4, 4, 3, 5, 5, 4, 4, 3, 4, 3, 4, 4, 3, 4, 3, 5, 3, 5};
    }
}

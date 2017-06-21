package com.evwill.dglive;

import java.util.List;

public class Course {
    private String mName;
    private List<Hole> mHoles;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Hole> getHoles() {
        return mHoles;
    }

    public void setHoles(List<Hole> holes) {
        mHoles = holes;
    }
}

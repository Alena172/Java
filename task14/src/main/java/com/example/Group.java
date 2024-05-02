package com.example;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private List<Student> students;

    public Group(String groupName)
    {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public void setGroup(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}

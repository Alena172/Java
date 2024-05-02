package com.example;
import jakarta.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Student> students = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Group{id=").append(id)
                .append(", groupName='").append(groupName)
                .append("', students=");
        for (Student student : students) {
            stringBuilder.append(student.toString()).append(", ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public List<Student> getStudents() {
        return students;
    }

    public Long getId() {
        return id;
    }
}
package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataExportServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private DataExportService dataExportService;

    private Group testGroup;

    @BeforeEach
    void setUp() {
        testGroup = new Group();
        testGroup.setGroupName("TestGroup");

        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setFirstName("John");
        student1.setLastName("Doe");
        students.add(student1);

        Student student2 = new Student();
        student2.setFirstName("Jane");
        student2.setLastName("Doe");
        students.add(student2);

        testGroup.setStudents(students);
    }


    @Test
    void exportData_ExportsDataForAllGroups() {
        List<Group> groups = new ArrayList<>();
        groups.add(testGroup);
        when(groupRepository.findAll()).thenReturn(groups);
        dataExportService.exportData();
    }

}

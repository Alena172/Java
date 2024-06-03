package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddStudent() {
        Group group = new Group();
        group.setId(1L);

        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        ResponseEntity<?> responseEntity = studentService.addStudent("John", "Doe", 1L);

        assertEquals(ResponseEntity.ok("Student John successfully created!"), responseEntity);

        verify(studentRepository, times(1)).save(any(Student.class));
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        students.add(student);

        when(studentRepository.findAll()).thenReturn(students);

        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> studentData = new HashMap<>();
        studentData.put("id", 1L);
        studentData.put("firstName", "John");
        studentData.put("lastName", "Doe");
        expected.add(studentData);

        List<Map<String, Object>> actual = studentService.getAllStudents();

        assertEquals(expected, actual);
    }
}

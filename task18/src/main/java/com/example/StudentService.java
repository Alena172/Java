package com.example;

import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface StudentService {
    ResponseEntity<?> addStudent(String firstName, String lastName, Long groupId);
    List<Map<String, Object>> getAllStudents();
    List<Map<String, Object>> filterStudentsByCriteria(Map<String, String> params);
    void deleteStudent(Long id);
}

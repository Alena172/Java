package com.example;

// StudentServiceImpl.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public ResponseEntity<?> addStudent(String firstName, String lastName, Long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (!optionalGroup.isPresent()) {
            return ResponseEntity.badRequest().body("Group with id " + groupId + " not found.");
        }

        Group group = optionalGroup.get();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroup(group);

        studentRepository.save(student);
        return ResponseEntity.ok("Student " + firstName + " successfully created!");
    }

    @Override
    public List<Map<String, Object>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<Map<String, Object>> studentsData = new ArrayList<>();
        for (Student student : students) {
            studentsData.add(mapStudentToMap(student));
        }
        return studentsData;
    }

    @Override
    public List<Map<String, Object>> filterStudentsByCriteria(Map<String, String> params) {
        List<Student> filteredStudents;
        if (params.containsKey("firstName")) {
            filteredStudents = studentRepository.findByFirstName(params.get("firstName"));
        } else {
            filteredStudents = studentRepository.findAll();
        }

        List<Map<String, Object>> studentsData = new ArrayList<>();
        for (Student student : filteredStudents) {
            studentsData.add(mapStudentToMap(student));
        }
        return studentsData;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private Map<String, Object> mapStudentToMap(Student student) {
        Map<String, Object> studentData = new HashMap<>();
        studentData.put("id", student.getId());
        studentData.put("firstName", student.getFirstName());
        studentData.put("lastName", student.getLastName());
        if (student.getGroup() != null) {
            studentData.put("groupId", student.getGroup().getId());
        }
        return studentData;
    }


}

package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseEntity<?> addStudent(String firstName, String lastName, Long groupId) {
        logger.info("Adding student with firstName: {}, lastName: {}, groupId: {}", firstName, lastName, groupId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (!optionalGroup.isPresent()) {
            String errorMessage = "Group with id " + groupId + " not found.";
            logger.error(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Group group = optionalGroup.get();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroup(group);

        studentRepository.save(student);
        logger.info("Student {} successfully created!", firstName);
        emailService.sendEmail("pusto446@gmail.com", "New Student!", "Student saved successfully: " + student.getFirstName() + " " + student.getLastName());
        return ResponseEntity.ok("Student " + firstName + " successfully created!");
    }

    @Override
    public List<Map<String, Object>> getAllStudents() {
        logger.info("Retrieving all students");
        List<Student> students = studentRepository.findAll();
        List<Map<String, Object>> studentsData = new ArrayList<>();
        for (Student student : students) {
            studentsData.add(mapStudentToMap(student));
        }
        return studentsData;
    }

    @Override
    public List<Map<String, Object>> filterStudentsByCriteria(Map<String, String> params) {
        logger.info("Filtering students by criteria: {}", params);
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
        logger.info("Deleting student with id: {}", id);
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

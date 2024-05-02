package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("/add")
    public ResponseEntity<?> addStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("groupId") Long groupId) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Group group = session.get(Group.class, groupId);
            if (group == null) {
                return ResponseEntity.badRequest().body("Group with id " + groupId + " not found.");
            }

            student.setGroup(group);
            session.save(student);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating student.");
        }

        return ResponseEntity.ok("Student " + firstName + " successfully created!");
    }

    @GetMapping
    public List<Map<String, Object>> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            List<Map<String, Object>> studentsData = new ArrayList<>();
            for (Student student : students) {
                Map<String, Object> studentInfo = new HashMap<>();
                studentInfo.put("id", student.getId());
                studentInfo.put("firstName", student.getFirstName());
                studentInfo.put("lastName", student.getLastName());
                // Добавьте другие поля студента, если нужно

                studentsData.add(studentInfo);
            }
            return studentsData;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
            }
            tx.commit();
        }
    }
}

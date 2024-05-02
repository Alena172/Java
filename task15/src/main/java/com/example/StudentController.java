package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

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
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
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

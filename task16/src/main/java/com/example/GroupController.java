package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private SessionFactory sessionFactory;

    @PostMapping
    public void createGroup(@RequestBody Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(group);
            tx.commit();
        }
    }

    @GetMapping
    public List<Group> getAllGroups() {
        try (Session session = sessionFactory.openSession()) {
            List<Group> groups = session.createQuery("FROM Group", Group.class).list();
            for (Group group : groups) {
                // Извлечение студентов для текущей группы
                List<Student> students = group.getStudents();
                // Если FetchType.LAZY, вы можете добавить эту строку, чтобы принудительно загрузить студентов
                 students.size();
                // Далее вы можете выполнить нужные операции с полученным списком студентов, например, вывести их в консоль
                for (Student student : students) {
                    System.out.println(student.getFirstName() + " " + student.getLastName());
                }
            }
            return groups;
        }
    }

    @GetMapping("/add")
    public ResponseEntity<String> addGroup(@RequestParam("groupName") String groupName) {
        Group group = new Group();
        group.setGroupName(groupName);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(group);
            tx.commit();
        }

        return ResponseEntity.ok("Group " + groupName + " created successfully!");
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Group group = session.get(Group.class, id);
            if (group != null) {
                session.delete(group);
            }
            tx.commit();
        }
    }
}

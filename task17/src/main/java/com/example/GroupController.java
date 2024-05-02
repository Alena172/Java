package com.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;
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
                List<Student> students = group.getStudents();
                 students.size();
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

    @GetMapping("/filter")
    public List<Group> filterGroupsByName(@RequestParam("groupName") String groupName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = builder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);

            criteriaQuery.select(root).where(builder.equal(root.get("groupName"), groupName));

            List<Group> groups = session.createQuery(criteriaQuery).getResultList();
            for (Group group : groups) {
                Hibernate.initialize(group.getStudents());
            }
            return groups;
        }
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

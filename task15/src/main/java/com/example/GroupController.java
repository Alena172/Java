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
            return session.createQuery("FROM Group", Group.class).list();
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

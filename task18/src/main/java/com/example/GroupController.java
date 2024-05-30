// GroupController.java
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public void createGroup(@RequestBody Group group) {
        groupService.createGroup(group);
    }

    @GetMapping("/add")
    public ResponseEntity<String> addGroup(@RequestParam("groupName") String groupName) {
        Group group = new Group();
        group.setGroupName(groupName);

        groupService.createGroup(group); // Используем сервис для создания группы

        return ResponseEntity.ok("Group " + groupName + " created successfully!");
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/filter")
    public List<Group> filterGroupsByName(@RequestParam("groupName") String groupName) {
        return groupService.filterGroupsByName(groupName);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}

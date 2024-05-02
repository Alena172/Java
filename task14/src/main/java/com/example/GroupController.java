package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {
    private List<Group> groups = new ArrayList<>();

    @PostMapping("/groups")
    public void createGroup(@RequestBody Group group) {
        groups.add(group);
    }

    @GetMapping(value = "/set/group", params = {"groupName"})
    public @ResponseBody String setStudent(@RequestParam("groupName") String groupName) {
        System.err.println("Going to set " + groupName);

        Group group = new Group(groupName);
        groups.add(group);
        return "Created " + group.toString();
    }

    @DeleteMapping("/groups/{index}")
    public void deleteGroup(@PathVariable int index) {
        groups.remove(index);
    }


    @GetMapping("/groups")
    public String getAllGroups() {
        String str = "";
        for (int i = 0; i < groups.size(); i++) {
            str += groups.get(i).getGroupName();
        }
        return str;
    }
    //localhost:8080/set/group?groupName=IKBO-20-22
}

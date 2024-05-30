// GroupService.java
package com.example;

import java.util.List;

public interface GroupService {
    void createGroup(Group group);
    List<Group> getAllGroups();
    List<Group> filterGroupsByName(String groupName);
    void deleteGroup(Long id);
}

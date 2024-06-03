package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGroupsTest() {
        List<Group> groups = new ArrayList<>();
        when(groupRepository.findAll()).thenReturn(groups);
        List<Group> result = groupService.getAllGroups();
        assertEquals(groups, result);
    }

    @Test
    void filterGroupsByNameTest() {
        String groupName = "TestGroup";
        List<Group> filteredGroups = new ArrayList<>();
        when(groupRepository.findByGroupName(groupName)).thenReturn(filteredGroups);
        List<Group> result = groupService.filterGroupsByName(groupName);
        assertEquals(filteredGroups, result);
    }

    @Test
    void deleteGroupTest() {
        Long groupId = 1L;
        groupService.deleteGroup(groupId);
        verify(groupRepository, times(1)).deleteById(groupId);
    }
}

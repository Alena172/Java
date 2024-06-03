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

class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private GroupServiceImpl groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateGroup() {
        Group group = new Group();
        group.setGroupName("Test Group");

        groupService.createGroup(group);

        verify(groupRepository, times(1)).save(group);
        verify(emailService, times(1)).sendEmail(eq("pusto446@gmail.com"), eq("New Group"), contains("Group saved successfully: Test Group"));
    }

    @Test
    void testGetAllGroups() {
        List<Group> expectedGroups = new ArrayList<>();
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Group 1");
        expectedGroups.add(group1);

        Group group2 = new Group();
        group2.setId(2L);
        group2.setGroupName("Group 2");
        expectedGroups.add(group2);

        when(groupRepository.findAll()).thenReturn(expectedGroups);

        List<Group> actualGroups = groupService.getAllGroups();

        assertEquals(expectedGroups.size(), actualGroups.size());
        for (int i = 0; i < expectedGroups.size(); i++) {
            assertEquals(expectedGroups.get(i).getId(), actualGroups.get(i).getId());
            assertEquals(expectedGroups.get(i).getGroupName(), actualGroups.get(i).getGroupName());
        }
    }

    @Test
    void testFilterGroupsByName() {
        List<Group> expectedGroups = new ArrayList<>();
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Test Group");
        expectedGroups.add(group1);

        when(groupRepository.findByGroupName("Test Group")).thenReturn(expectedGroups);

        List<Group> actualGroups = groupService.filterGroupsByName("Test Group");

        assertEquals(expectedGroups.size(), actualGroups.size());
        for (int i = 0; i < expectedGroups.size(); i++) {
            assertEquals(expectedGroups.get(i).getId(), actualGroups.get(i).getId());
            assertEquals(expectedGroups.get(i).getGroupName(), actualGroups.get(i).getGroupName());
        }
    }

    @Test
    void testDeleteGroup() {
        Long groupId = 1L;
        groupService.deleteGroup(groupId);
        verify(groupRepository, times(1)).deleteById(groupId);
    }
}

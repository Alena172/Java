package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void createGroup(Group group) {
        logger.info("Creating group: {}", group.getGroupName());
        groupRepository.save(group);
        logger.info("Group created successfully: {}", group.getGroupName());
        emailService.sendEmail("pusto446@gmail.com", "New Group", "Group saved successfully: " + group.getGroupName());
    }

    @Override
    public List<Group> getAllGroups() {
        logger.info("Retrieving all groups");
        return groupRepository.findAll();
    }

    @Override
    public List<Group> filterGroupsByName(String groupName) {
        logger.info("Filtering groups by name: {}", groupName);
        return groupRepository.findByGroupName(groupName);
    }

    @Override
    public void deleteGroup(Long id) {
        logger.info("Deleting group with id: {}", id);
        groupRepository.deleteById(id);
    }
}

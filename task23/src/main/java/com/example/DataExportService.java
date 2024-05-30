package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class DataExportService {

    private static final Logger logger = LoggerFactory.getLogger(DataExportService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Scheduled(fixedRate = 1800000) // 30 minutes
    public void exportData() {
        logger.info("Starting data export process.");
        String directoryPath = "C:\\Users\\Alena\\IdeaProjects\\task22\\Directory";
        File directory = new File(directoryPath);
        clearDirectory(directory);
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            exportGroupData(group, directoryPath);
        }

        logger.info("Data export process completed.");
    }

    private void clearDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        } else {
            directory.mkdir();
        }
    }

    private void exportGroupData(Group group, String directoryPath) {
        String fileName = directoryPath + File.separator + group.getGroupName() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Group Name: " + group.getGroupName() + "\n");
            writer.write("Students:\n");
            for (Student student : group.getStudents()) {
                writer.write(" - " + student.getFirstName() + " " + student.getLastName() + "\n");
            }
            logger.info("Data exported for group: {}", group.getGroupName());
        } catch (IOException e) {
            logger.error("Failed to export data for group: {}", group.getGroupName(), e);
        }
    }
}

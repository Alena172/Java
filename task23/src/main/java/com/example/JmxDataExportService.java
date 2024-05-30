package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.example:type=DataExportService")
public class JmxDataExportService {

    private final DataExportService dataExportService;

    @Autowired
    public JmxDataExportService(DataExportService dataExportService) {
        this.dataExportService = dataExportService;
    }

    @ManagedOperation(description = "Manually trigger data export process")
    public void triggerDataExport() {
        dataExportService.exportData();
    }
}

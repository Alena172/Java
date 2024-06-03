package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class JmxDataExportServiceTest {

    @Test
    void testTriggerDataExport() {
        DataExportService dataExportServiceMock = Mockito.mock(DataExportService.class);

        JmxDataExportService jmxDataExportService = new JmxDataExportService(dataExportServiceMock);

        jmxDataExportService.triggerDataExport();

        verify(dataExportServiceMock).exportData();
    }
}

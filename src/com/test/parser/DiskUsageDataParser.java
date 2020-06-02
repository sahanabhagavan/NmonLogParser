package com.test.parser;

import java.io.PrintWriter;
import java.util.List;

import static com.test.util.FileUtility.close;
import static com.test.util.FileUtility.fileWriter;

public class DiskUsageDataParser implements NmonLogParser {

    private float allDiskBusy = 0f;
    private float averageDiskBusy = 0f;
    private float allDiskRead = 0f;
    private float averageDiskRead = 0f;
    private float allDiskWrite = 0f;
    private float averageDiskWrite = 0f;

    @Override
    public void analyze(List<String> diskBusyUsageDataList, List<String> diskReadUsageDataList, List<String> diskWriteUsageDataList) {
        for (String diskBusyDataList : diskBusyUsageDataList) {
            String values1[] = diskBusyDataList.split(",");
            float diskBusy = Float.parseFloat(values1[2]);
            allDiskBusy = allDiskBusy + diskBusy;
        }
        averageDiskBusy = (float) (Math.round((allDiskBusy / diskBusyUsageDataList.size()) * 100.0) / 100.0);

        for (String diskReadDataList : diskReadUsageDataList) {
            String values2[] = diskReadDataList.split(",");
            float diskRead = Float.parseFloat(values2[2]);
            allDiskRead = allDiskRead + diskRead;
        }
        averageDiskRead = (float) (Math.round((allDiskRead / diskReadUsageDataList.size()) * 100.0) / 100.0);

        for (String diskWriteDataList : diskWriteUsageDataList) {
            String values3[] = diskWriteDataList.split(",");
            float diskWrite = Float.parseFloat(values3[2]);
            allDiskWrite = allDiskWrite + diskWrite;
        }
        averageDiskWrite = (float) (Math.round((allDiskWrite / diskWriteUsageDataList.size()) * 100.0) / 100.0);

        PrintWriter printWriter = new PrintWriter(fileWriter());
        printWriter.println("The average Disk Busy is " + averageDiskBusy + "%");
        printWriter.println("The average Disk Read is " + averageDiskRead + " KB/s");
        printWriter.println("The average Disk Write is " + averageDiskWrite + " KB/s");
        close(printWriter);

    }

    @Override
    public void analyze(List<String> parsedDataLines) {

    }
}

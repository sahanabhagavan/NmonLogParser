package com.test;

import com.test.parser.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.test.util.FileUtility.*;

public class Main {

    public static void main(String[] args) {

        boolean isFirstMatchCPU = true;
        boolean isFirstMatchMemory = true;
        boolean isFirstMatchNetwork = true;
        boolean isFirstMatchDiskBusy = true;
        boolean isFirstMatchDiskRead = true;
        boolean isFirstMatchDiskWrite = true;

        List<String> cpuUsageLinesDataList = new ArrayList<>();
        List<String> memoryUsageLinesDataList = new ArrayList<>();
        List<String> networkUsageLinesDataList = new ArrayList<>();
        List<String> diskBusyLinesDataList = new ArrayList<>();
        List<String> diskReadLinesDataList = new ArrayList<>();
        List<String> diskWriteLinesDataList = new ArrayList<>();

        Scanner inputFileScanner = fileReader();

        if (inputFileScanner != null) {
            while (inputFileScanner.hasNextLine()) {
                String lineData = inputFileScanner.nextLine();

                if (lineData.startsWith("CPU_ALL")) {
                    if (!isFirstMatchCPU) {
                        cpuUsageLinesDataList.add(lineData);
                    }
                    isFirstMatchCPU = false;

                } else if (lineData.startsWith("MEM,")) {
                    if (!isFirstMatchMemory) {
                        memoryUsageLinesDataList.add(lineData);
                    }
                    isFirstMatchMemory = false;

                } else if (lineData.startsWith("NET,")) {
                    if (!isFirstMatchNetwork) {
                        networkUsageLinesDataList.add(lineData);
                    }
                    isFirstMatchNetwork = false;

                } else if (lineData.startsWith("DISKBUSY")) {
                    if (!isFirstMatchDiskBusy) {
                        diskBusyLinesDataList.add(lineData);
                    }
                    isFirstMatchDiskBusy = false;

                } else if (lineData.startsWith("DISKREAD")) {
                    if (!isFirstMatchDiskRead) {
                        diskReadLinesDataList.add(lineData);
                    }
                    isFirstMatchDiskRead = false;

                } else if (lineData.startsWith("DISKWRITE")) {
                    if (!isFirstMatchDiskWrite) {
                        diskWriteLinesDataList.add(lineData);
                    }
                    isFirstMatchDiskWrite = false;
                }
            }
            close(inputFileScanner);

            createOutputFile();

            NmonLogParser logParser = new CPUUsageDataParser();
            logParser.analyze(cpuUsageLinesDataList);

            logParser = new MemoryUsageDataParser();
            logParser.analyze(memoryUsageLinesDataList);

            logParser = new NetworkUsageDataParser();
            logParser.analyze(networkUsageLinesDataList);

            logParser = new DiskUsageDataParser();
            logParser.analyze(diskBusyLinesDataList, diskReadLinesDataList, diskWriteLinesDataList);
        }
    }
}


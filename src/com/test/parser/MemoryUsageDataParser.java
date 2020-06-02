package com.test.parser;

import com.test.domain.MemoryUsageData;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.test.util.FileUtility.close;
import static com.test.util.FileUtility.fileWriter;

public class MemoryUsageDataParser implements NmonLogParser {

    private float allMemoryUsage = 0;
    private float averageMemoryUsage = 0;
    private float averageMemoryUsagePercentage = 0;

    List<MemoryUsageData> memoryUsageDataList = new ArrayList<>();

    @Override
    public void analyze(List<String> memoryDataLinesList) {
        for (String memoryDataList : memoryDataLinesList) {
            String values[] = memoryDataList.split(",");
            float freeMemory = Float.parseFloat(values[4]);
            float usedMemory = (float) (32768.0 - freeMemory);

            MemoryUsageData dataset = new MemoryUsageData();
            dataset.setUsedMemory(usedMemory);
            memoryUsageDataList.add(dataset);
        }

        addAllMemoryUsageTime(memoryUsageDataList);
        PrintWriter printWriter = new PrintWriter(fileWriter());
        printWriter.println("The average memory used is " + averageMemoryUsagePercentage + "%");
        close(printWriter);
    }

    @Override
    public void analyze(List<String> diskBusyLinesDataList, List<String> diskReadLinesDataList, List<String> diskWriteLinesDataList) {

    }

    private void addAllMemoryUsageTime(List<MemoryUsageData> memoryUsageDataList) {
        for (MemoryUsageData memoryUsageData : memoryUsageDataList) {
            allMemoryUsage = allMemoryUsage + memoryUsageData.getUsedMemory();
        }
        averageMemoryUsage = allMemoryUsage / memoryUsageDataList.size();
        averageMemoryUsagePercentage = (float) (Math.round(((averageMemoryUsage / (float) 32768.0) * 100) * 100.0) / 100.0);
    }
}





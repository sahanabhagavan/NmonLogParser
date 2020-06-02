package com.test.parser;

import com.test.domain.CPUUsageData;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.test.util.FileUtility.close;
import static com.test.util.FileUtility.fileWriter;

public class CPUUsageDataParser implements NmonLogParser {

    private float allUserCPUTime = 0f;
    private float averageUserCPUTime = 0f;
    private float allSystemCPUTime = 0f;
    private float averageSystemCPUTime = 0f;
    private float allIdleCPUTime = 0f;
    private float averageIdleCPUTime = 0f;
    private float allWaitCPUTime = 0f;
    private float averageWaitCPUTime = 0f;

    List<CPUUsageData> cpuUsageDataList = new ArrayList<>();

    @Override
    public void analyze(List<String> cpuDataLinesList) {
        for (String cpuDataList : cpuDataLinesList) {
            String values[] = cpuDataList.split(",");
            float userCPUTime = Float.parseFloat(values[2]);
            float systemCPUTime = Float.parseFloat(values[3]);
            float waitCPUTime = Float.parseFloat(values[4]);
            float idleCPUTime = Float.parseFloat(values[5]);

            CPUUsageData dataSet = new CPUUsageData();
            dataSet.setUserCpuTime(userCPUTime);
            dataSet.setSystemCpuTime(systemCPUTime);
            dataSet.setWaitCpuTime(waitCPUTime);
            dataSet.setIdleCPUTime(idleCPUTime);

            cpuUsageDataList.add(dataSet);
        }
        addAllCpuUsageTime(cpuUsageDataList);

        PrintWriter printWriter = new PrintWriter(fileWriter());

        printWriter.println("The average CPU user time is " + averageUserCPUTime + "%");
        printWriter.println("The average CPU system time is " + averageSystemCPUTime + "%");
        printWriter.println("The average CPU wait time is " + averageWaitCPUTime + "%");
        printWriter.println("The average CPU idle time is " + averageIdleCPUTime + "%");
        close(printWriter);

    }

    @Override
    public void analyze(List<String> diskBusyLinesDataList, List<String> diskReadLinesDataList, List<String> diskWriteLinesDataList) {

    }

    private void addAllCpuUsageTime(List<CPUUsageData> cpuUsageDataList) {
        for (CPUUsageData cpuUsageData : cpuUsageDataList) {
            allUserCPUTime = allUserCPUTime + cpuUsageData.getUserCpuTime();
            allSystemCPUTime = allSystemCPUTime + cpuUsageData.getSystemCpuTime();
            allIdleCPUTime = allIdleCPUTime + cpuUsageData.getIdleCPUTime();
            allWaitCPUTime = allWaitCPUTime + cpuUsageData.getWaitCpuTime();
        }
        averageUserCPUTime = (float) (Math.round((allUserCPUTime / cpuUsageDataList.size()) * 100.0) / 100.0);
        averageSystemCPUTime = (float) (Math.round((allSystemCPUTime / cpuUsageDataList.size()) * 100.0) / 100.0);
        averageIdleCPUTime = (float) (Math.round((allIdleCPUTime / cpuUsageDataList.size()) * 100.0) / 100.0);
        averageWaitCPUTime = (float) (Math.round((allWaitCPUTime / cpuUsageDataList.size()) * 100.0) / 100.0);
    }
}





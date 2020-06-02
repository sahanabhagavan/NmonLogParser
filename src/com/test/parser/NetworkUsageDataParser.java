package com.test.parser;

import com.test.domain.NetworkUsageData;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.test.util.FileUtility.close;
import static com.test.util.FileUtility.fileWriter;

public class NetworkUsageDataParser implements NmonLogParser {

    private float allNetworkRead = 0;
    private float averageNetworkRead = 0;
    private float allNetworkWrite = 0;
    private float averageNetworkWrite = 0;

    List<NetworkUsageData> networkUsageDataList = new ArrayList<>();

    @Override
    public void analyze(List<String> networkDataLinesList) {
        for (String networkDataList : networkDataLinesList) {
            String values[] = networkDataList.split(",");
            float networkRead = Float.parseFloat(values[2]);
            float networkWrite = Float.parseFloat(values[4]);

            NetworkUsageData dataset = new NetworkUsageData();
            dataset.setNetworkRead(networkRead);
            dataset.setNetworkWrite(networkWrite);

            networkUsageDataList.add(dataset);
        }

        addAllNetworkIOData(networkUsageDataList);

        PrintWriter printWriter = new PrintWriter(fileWriter());
        printWriter.println("The average network read is " + averageNetworkRead + " KB/s");
        printWriter.println("The average network write is " + averageNetworkWrite + " KB/s");
        close(printWriter);

    }

    @Override
    public void analyze(List<String> diskBusyLinesDataList, List<String> diskReadLinesDataList, List<String> diskWriteLinesDataList) {

    }

    private void addAllNetworkIOData(List<NetworkUsageData> networkUsageDataList) {
        for (NetworkUsageData networkUsageData : networkUsageDataList) {
            allNetworkRead = allNetworkRead + networkUsageData.getNetworkRead();
            allNetworkWrite = allNetworkWrite + networkUsageData.getNetworkWrite();

        }
        averageNetworkRead = (float) (Math.round((allNetworkRead / networkUsageDataList.size()) * 100.0) / 100.0);
        averageNetworkWrite = (float) (Math.round((allNetworkWrite / networkUsageDataList.size()) * 100.0) / 100.0);
    }
}

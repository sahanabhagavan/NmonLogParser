package com.test.parser;

import java.util.List;

public interface NmonLogParser {
    void analyze(List<String> parsedDataLines);

    void analyze(List<String> diskBusyLinesDataList, List<String> diskReadLinesDataList, List<String> diskWriteLinesDataList);
}

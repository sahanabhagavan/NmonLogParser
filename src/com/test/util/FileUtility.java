package com.test.util;

import java.io.*;
import java.util.Scanner;

public class FileUtility {

    public static Scanner fileReader() {
        try {
            File inputFile = new File("nmon_log_file.nmon");
            Scanner inputFileReader = new Scanner(inputFile);
            return (inputFileReader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createOutputFile() {
        try {
            File outputFile = new File("nmon_output.txt");
            if (outputFile.exists()) {
                new FileWriter("nmon_output.txt", false).close();
            } else {
                outputFile.createNewFile();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static PrintWriter fileWriter() {
        try {
            File outputFile = new File("nmon_output.txt");
            FileWriter fw = new FileWriter(outputFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            return pw;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public static void close(Scanner scanner) {
        scanner.close();
    }

    public static void close(PrintWriter printWriter) {
        printWriter.close();
    }
}

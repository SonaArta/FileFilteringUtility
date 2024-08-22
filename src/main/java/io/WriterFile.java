package io;

import options.OpenMode;
import processing.FileHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterFile {
    private final String longOutputFile;
    private final String doubleOutputFile;
    private final String stringOutputFile;
    private OpenMode openMode = OpenMode.REWRITE;

    public WriterFile(String outputFilePath, String outputFileNamePrefix, OpenMode openMode) {
        String changedOutputDir = outputFilePath;

        if (!outputFilePath.endsWith("\\")) {
            changedOutputDir += '\\';
        }

        createPath(changedOutputDir);

        String basePartName = changedOutputDir + outputFileNamePrefix;
        longOutputFile = basePartName + "integers.txt";
        doubleOutputFile = basePartName + "floats.txt";
        stringOutputFile = basePartName + "strings.txt";

        this.openMode = openMode;
    }

    private void createPath(String changedOutputDir) {
        File path = new File(changedOutputDir);
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    private <T> void writeOneData(List<T> data, String outputFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, openMode.equals(OpenMode.ADD)))) {
            for (T line : data) {
                bufferedWriter.write(line.toString() + '\n');
            }
            bufferedWriter.write('\n');
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public void writeData(FileHandler fileHandler) {
        if (!fileHandler.getLongList().isEmpty()) {
            writeOneData(fileHandler.getLongList(), longOutputFile);
        } else {
            System.out.println("Integer data is missing");
        }

        if (!fileHandler.getDoubleList().isEmpty()) {
            writeOneData(fileHandler.getDoubleList(), doubleOutputFile);
        } else {
            System.out.println("Float data is missing");
        }

        if (!fileHandler.getStringList().isEmpty()) {
            writeOneData(fileHandler.getStringList(), stringOutputFile);
        } else {
            System.out.println("String data is missing");
        }

    }
}

package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {
    private final List<String> inputFiles;

    public ReaderFile(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    private List<String> readFile(String currentFile) {
        List<String> dataFile = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataFile.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return dataFile;
    }

    public List<String> readFiles() {
        List<String> dataFiles = new ArrayList<>();
        for (String currentFile : inputFiles) {
            dataFiles.addAll(readFile(currentFile));
        }
        return dataFiles;
    }
}

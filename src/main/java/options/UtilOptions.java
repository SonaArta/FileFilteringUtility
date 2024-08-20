package options;

import java.util.List;

public class UtilOptions {
    private String outputFilePath;
    private String outputFileNamePrefix;
    private OpenMode openMode = OpenMode.REWRITE;
    private StatisticsCollectionMode statisticsCollectionMode;
    private List<String> inputFiles;

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public String getOutputFileNamePrefix() {
        return outputFileNamePrefix;
    }

    public void setOutputFileNamePrefix(String outputFileNamePrefix) {
        this.outputFileNamePrefix = outputFileNamePrefix;
    }

    public OpenMode getOpenMode() {
        return openMode;
    }

    public void setOpenMode(OpenMode openMode) {
        this.openMode = openMode;
    }

    public StatisticsCollectionMode getStatisticsCollectionMode() {
        return statisticsCollectionMode;
    }

    public void setStatisticsCollectionMode(StatisticsCollectionMode statisticsCollectionMode) {
        this.statisticsCollectionMode = statisticsCollectionMode;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }
}

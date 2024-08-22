import io.ReaderFile;
import io.WriterFile;
import options.OptionsParser;
import options.UtilOptions;
import org.apache.commons.cli.ParseException;
import processing.FileHandler;
import statistic.StatisticsCollector;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OptionsParser optionsParser = OptionsParser.getOptionsParser();
        ReaderFile readerFile;
        WriterFile writerFile;
        FileHandler fileHandler = new FileHandler();

        try {
            UtilOptions utilOptions = optionsParser.parseOption(args);

            readerFile = new ReaderFile(utilOptions.getInputFiles());
            List<String> dataFromAllFiles = readerFile.readFiles();

            fileHandler.filter(dataFromAllFiles);

            writerFile = new WriterFile(utilOptions.getOutputFilePath(), utilOptions.getOutputFileNamePrefix(), utilOptions.getOpenMode());
            writerFile.writeData(fileHandler);

            StatisticsCollector statisticsCollector = new StatisticsCollector(fileHandler, utilOptions.getStatisticsCollectionMode());
            statisticsCollector.getStatistics();
        } catch (IllegalArgumentException | ParseException e) {
            System.err.println(e.getMessage());
            optionsParser.printUsage();
        }
    }
}
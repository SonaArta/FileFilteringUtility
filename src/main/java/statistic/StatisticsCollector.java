package statistic;

import options.StatisticsCollectionMode;
import processing.FileHandler;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;


public class StatisticsCollector {
    private final FileHandler fileHandler;
    private final StatisticsCollectionMode statisticsCollectionMode;

    public StatisticsCollector(FileHandler fileHandler, StatisticsCollectionMode statisticsCollectionMode) {
        this.fileHandler = fileHandler;
        this.statisticsCollectionMode = statisticsCollectionMode;
    }

    private long longCount() {
        List<Long> longList = fileHandler.getLongList();
        return longList.size();
    }

    private long doubCount() {
        List<Double> doubList = fileHandler.getDoubleList();
        return doubList.size();
    }

    private long stringCount() {
        List<String> sringList = fileHandler.getStringList();
        return sringList.size();
    }

    public void getStatistics() {
        if (statisticsCollectionMode.equals(StatisticsCollectionMode.SHORT)) {
            if (!fileHandler.getLongList().isEmpty() || !fileHandler.getDoubleList().isEmpty() || !fileHandler.getStringList().isEmpty()) {
                System.out.println(getShortStatistics().toString());
            }
        } else {
            if (!fileHandler.getLongList().isEmpty() || !fileHandler.getDoubleList().isEmpty() || !fileHandler.getStringList().isEmpty()) {
                System.out.println(getFullStatistics().toString());
            }
        }
    }

    public ShortStatistics getShortStatistics() {
        return new ShortStatistics(longCount() + doubCount() + stringCount());
    }

    public FullStatistics getFullStatistics() {
        List<Long> longList = fileHandler.getLongList();
        List<Double> doubList = fileHandler.getDoubleList();
        List<String> stringList = fileHandler.getStringList();

        LongSummaryStatistics longSummaryStatistics = longList.stream()
                .mapToLong(Long::longValue)
                .summaryStatistics();


        DoubleSummaryStatistics doubleSummaryStatistics = doubList.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();


        long stringMinLength = stringList.stream()
                .map(String::length)
                .min(Integer::compareTo)
                .orElse(0);
        long stringMaxLength = stringList.stream()
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(0);


        return new FullStatistics(
                new StatInfoNumbers<Long>(longSummaryStatistics.getCount(),
                        longSummaryStatistics.getMin(),
                        longSummaryStatistics.getMax(),
                        longSummaryStatistics.getSum(),
                        longSummaryStatistics.getMax()),
                new StatInfoNumbers<Double>(doubleSummaryStatistics.getCount(),
                        doubleSummaryStatistics.getMin(),
                        doubleSummaryStatistics.getMax(),
                        doubleSummaryStatistics.getSum(),
                        doubleSummaryStatistics.getAverage()),
                new StatInfo<Long>(stringCount(), stringMinLength, stringMaxLength)
        );
    }
}

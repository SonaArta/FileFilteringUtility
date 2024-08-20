package processing;

public class FullStatistics extends Statistics {
    private final long elementCount;
    StatInfoNumbers<Long> longStat;
    StatInfoNumbers<Double> doubleStat;
    StatInfo<Long> stringStat;

    public FullStatistics(StatInfoNumbers<Long> longStat, StatInfoNumbers<Double> doubleStat, StatInfo<Long> stringStat) {
        this.elementCount = longStat.getCount() + doubleStat.getCount() + stringStat.getCount();
        this.longStat = longStat;
        this.doubleStat = doubleStat;
        this.stringStat = stringStat;
    }

    public String longToString() {
        String string = "";
        if (longStat.getCount() != 0) {
            string = "Статистика по целочисленным данным" + '\n' +
                    '\t' + "Количество целых чисел: " + longStat.getCount() + '\n' +
                    '\t' + "Минимальное целое число: " + longStat.getMin() + '\n' +
                    '\t' + "Максимальное целое число: " + longStat.getMax() + '\n' +
                    '\t' + "Сумма целых чисел: " + longStat.getSum() + '\n' +
                    '\t' + "Среднее целых чисел: " + longStat.getAverage() + '\n';
        }
        return string;
    }

    public String doubleToString() {
        String string = "";
        if (doubleStat.getCount() != 0) {
            string = "Статистика по вещественным данным" + '\n' +
                    '\t' + "Количество вещественных чисел: " + doubleStat.getCount() + '\n' +
                    '\t' + "Минимальное вещественное число: " + doubleStat.getMin() + '\n' +
                    '\t' + "Максимальное вещественное число: " + doubleStat.getMax() + '\n' +
                    '\t' + "Сумма вещественных чисел: " + doubleStat.getSum() + '\n' +
                    '\t' + "Среднее вещественных чисел: " + doubleStat.getAverage() + '\n';
        }
        return string;
    }

    public String stringToString() {
        String string = "";
        if (stringStat.getCount() != 0) {
            string = "Статистика по строковым данным" + '\n' +
                    '\t' + "Количество строк: " + stringStat.getCount() + '\n' +
                    '\t' + "Размер самой короткой строки: " + stringStat.getMin() + '\n' +
                    '\t' + "Размер самой длинной строки: " + stringStat.getMax() + '\n';
        }
        return string;
    }

    @Override
    public String toString() {
        return "Полная статистика:" + '\n' +
                "Количество элементов, записанных в исходящие файлы: " + elementCount + '\n' +
                longToString() + '\n' +
                doubleToString() + '\n' +
                stringToString() + '\n';
    }
}

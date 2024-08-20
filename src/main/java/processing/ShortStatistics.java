package processing;

public class ShortStatistics extends Statistics {
    private final long elementCount;

    public ShortStatistics(long elementCount) {
        this.elementCount = elementCount;
    }

    public long getElementCount() {
        return elementCount;
    }

    @Override
    public String toString() {
        return "Краткая статистика:" + '\n' +
                "Количество элементов, записанных в исходящие файлы: " + elementCount;
    }
}

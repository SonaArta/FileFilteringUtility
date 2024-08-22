package statistic;

public class StatInfoNumbers<T> extends StatInfo<T> {
    private T sum;
    private double average;

    public StatInfoNumbers() {
        super();
    }

    public StatInfoNumbers(long count, T min, T max, T sum, double average) {
        super(count, min, max);
        this.sum = sum;
        this.average = average;
    }

    public T getSum() {
        return sum;
    }

    public void setSum(T sum) {
        this.sum = sum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}

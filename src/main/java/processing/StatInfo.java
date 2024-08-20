package processing;

public class StatInfo<T> {
    private long count;
    private T min;
    private T max;

    public StatInfo() {
    }

    public StatInfo(long count, T min, T max) {
        this.count = count;
        this.min = min;
        this.max = max;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }
}

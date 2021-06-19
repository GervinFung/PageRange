package range;

public final class Range {

    private final int min, max;

    public Range(final int min, final int max) {
        if (min <= 0) {
            throw new IllegalArgumentException("Min cannot be less than 1");
        }
        if (max <= 0) {
            throw new IllegalArgumentException("Max cannot be less than 1");
        }
        this.min = min;
        this.max = max;
    }

    public Range(final int min, final boolean nOrAbove) {
        this(min, nOrAbove ? Integer.MAX_VALUE : min);
    }

    public int getMax() { return max; }

    public int getMin() { return this.min; }

    @Override
    public String toString() {
        return "(min: " + min + " max: " + max + ")\t";
    }
}
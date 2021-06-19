package range;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class RangeTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 15, Integer.MAX_VALUE})
    public void testRangePositiveInteger(final int positiveInt) {
        new Range(positiveInt, false);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void testRangeNotPositiveInteger(final int nonPositiveInt) {
        assertThrows(IllegalArgumentException.class, () -> new Range(nonPositiveInt, false));
    }
}
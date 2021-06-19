package range;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static range.RangeList.RangeValidateInput;

public final class RangeValidateInputExceptionTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,-2,5-7,9-", "1-3,-5,6-8,9-100", "1-4-5,6-8,-10"})
    public void testDashAsFirstChar(final String range) {
        assertThrows(IllegalArgumentException.class, () -> new RangeValidateInput().getValidatedRange(range));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2-,5-7, 9-", "1,2-, 5 - 7,9-", "1,  2,6-7, 10-11"})
    public void testSpaceNotAtTrailingLeading(final String range) {
        assertThrows(IllegalArgumentException.class, () -> new RangeValidateInput().getValidatedRange(range));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,-2,9-3,9-", "1-3,-5,7-6,9-100", "1-7-4,6-8,-10"})
    public void testDescending(final String range) {
        assertThrows(IllegalArgumentException.class, () -> new RangeValidateInput().getValidatedRange(range));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,1-2,9-", "1-,1-", "1-4-7,6-8,10-10", "1-3-5-7-9-9"})
    public void testDuplicateNum(final String range) {
        assertThrows(IllegalArgumentException.class, () -> new RangeValidateInput().getValidatedRange(range));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "?", "a-10", "!", ".", "", "/", "  "})
    public void testInvalidRange(final String range) {
        assertThrows(IllegalArgumentException.class, () -> new RangeValidateInput().getValidatedRange(range));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,,,,,,4,,,,,", "2,,1", ",2", "1,", "1-,", ","})
    public void testExtraComma(final String range) {
        assertThrows(IllegalArgumentException.class, () -> new RangeValidateInput().getValidatedRange(range));
    }
}
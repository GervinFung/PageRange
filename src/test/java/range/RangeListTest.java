package range;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static range.RangeList.RangeValidateInput;

public final class RangeListTest {

    private final static String PATH = "src/test/resources/RangeListTest.txt";
    private final static RangeValidateInput RANGE_VALIDATE_INPUT = new RangeValidateInput();

    @ParameterizedTest
    @MethodSource("readFromResources")
    public void testValidRange(final String range, final int input, final boolean result) {
        assertSame(result, new RangeList(RANGE_VALIDATE_INPUT.getValidatedRange(range)).inRange(input));
    }

    private static Stream<Arguments> readFromResources() {

        final List<Arguments> objectList = new ArrayList<>();

        try (final BufferedReader reader = new BufferedReader(new FileReader(PATH))) {

            String line;
            while ((line = reader.readLine()) != null) {

                final String[] rawData = line.split("/");

                final String range = rawData[0];
                final int input = Integer.parseInt(rawData[1]);
                final boolean result = Boolean.parseBoolean(rawData[2]);

                objectList.add(arguments(range, input ,result));
            }

            return objectList.stream();

        } catch (final IOException e) { throw new RuntimeException("Error reading " + PATH); }
    }
}
package range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class RangeList {

    private final List<Range> rangeList;

    public RangeList(final String rangeGiven) {
        this.rangeList = createPageRange(rangeGiven);
    }

    public boolean inRange(final int input) {
        return this.rangeList.stream().anyMatch(range -> input <= range.getMax() && input >= range.getMin());
    }

    private List<Range> createPageRange(final String processedRangeGiven) {
        final List<Range> rangeList = new ArrayList<>();
        final String[] rangeSplit = processedRangeGiven.split(",");

        for (final String range : rangeSplit) {

            if (range.contains("-")) {
                final String[] minMax = range.split("-");

                if (minMax.length == 1) {
                    rangeList.add(new Range(Integer.parseInt(minMax[0]), true));
                    return Collections.unmodifiableList(rangeList);
                } else {
                    rangeList.add(new Range(Integer.parseInt(minMax[0]), Integer.parseInt(minMax[minMax.length - 1])));
                }
            } else {
                rangeList.add(new Range(Integer.parseInt(range), false));
            }
        }

        return Collections.unmodifiableList(rangeList);
    }

    public static boolean isPositiveInteger(final String integer) {
        return NumberUtils.isParsable(integer) && Integer.parseInt(integer) > 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.rangeList.size());
        this.rangeList.forEach(range -> sb.append(range.toString()));
        return sb.toString();
    }

    public final static class RangeValidateInput {

        private final static char COMMA = ',';

        private int compareGetLargest(final String range, final int smallest) {
            if (isPositiveInteger(range)) {
                final int parsedRange = Integer.parseInt(range);
                if (parsedRange == smallest) {
                    throw new IllegalArgumentException("Range cannot have duplicated numbers");
                }
                if (parsedRange < smallest) {
                    throw new IllegalArgumentException("Range must be in ascending order");
                }
                return parsedRange;
            }
            if ("-".equals(range)) {
                return smallest;
            } else {
                throw new IllegalArgumentException("Other than a single '-' in the middle of a pair of positive integer or after a positive integer, Range should only contain positive integers");
            }
        }

        private boolean noExtraComma(final String rangeGivenStrip) {
            char sampleCompare = ' ';

            final List<Character> rangeInChar = Lists.charactersOf(rangeGivenStrip);

            for (final char c : rangeInChar) {
                if (c == COMMA) {
                    if (sampleCompare == c) {
                        return false;
                    } else {
                        sampleCompare = c;
                    }
                } else {
                    sampleCompare = c;
                }
            }

            return rangeInChar.get(rangeInChar.size() - 1) != COMMA && rangeInChar.get(0) != COMMA;
        }

        private boolean isNumberAndAscending(final String[] rangeSplit) {
            int smallest = 0;

            for (final String range : rangeSplit) {

                if (StringUtils.isAllBlank(range)) {
                    throw new IllegalArgumentException("Range cannot have blank or empty space");
                }
                if (range.charAt(0) == '-') {
                    throw new IllegalArgumentException("Range cannot have '-' without a positive integer in front");
                }
                if (range.contains("-")) {
                    for (final String splitDash : range.split("-")) {
                        smallest = this.compareGetLargest(splitDash, smallest);
                    }
                } else {
                    smallest = this.compareGetLargest(range, smallest);
                }
            }

            return true;
        }

        public String getValidatedRange(@NonNull final String rangeGiven) {

            final String rangeGivenStrip = rangeGiven.strip();

            if (rangeGivenStrip == null) {
                throw new IllegalArgumentException("Range cannot be null");
            }

            if (rangeGivenStrip.length() == 0) {
                throw new IllegalArgumentException("Range cannot consists of empty space");
            }

            if (this.noExtraComma(rangeGivenStrip)) {

                final String[] rangeSplit = rangeGivenStrip.split(",");

                if (rangeSplit.length == 0) {
                    throw new IllegalArgumentException("Range cannot consists only of ',' ");
                }

                if (this.isNumberAndAscending(rangeSplit)) {
                    return rangeGivenStrip;
                }

            }
            throw new IllegalArgumentException("Range cannot have more than 1 ',' consecutively or ',' at the beginning/end of string");
        }
    }
}
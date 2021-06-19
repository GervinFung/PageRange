import range.RangeList;

import java.util.Scanner;

import static range.RangeList.RangeValidateInput;

public final class Main {

    private final Scanner scanner;
    private final RangeValidateInput rangeValidateInput;

    private Main() {
        this.rangeValidateInput = new RangeValidateInput();
        this.scanner = new Scanner(System.in);
    }

    public static void main(final String[] args) {
        final Main main = new Main();
        while (true) {
            final String pageRange = main.askPageRange();
            final int pageNum = main.askPageNum();
            System.out.println("Page Number within page range: " + main.isPageRange(pageNum, pageRange));
            if (main.askContinue()) {
                return;
            }
        }
    }

    private boolean isPageRange(final int pageNum, final String pageRange) {
        return new RangeList(pageRange).inRange(pageNum);
    }

    private boolean askContinue() {
        while (true) {
            System.out.print("Terminate program? (Y/N): ");
            final String decision = this.scanner.nextLine();
            if (decision.equalsIgnoreCase("Y")) {
                return true;
            } else if (decision.equalsIgnoreCase("N"))  {
                return false;
            }
        }
    }

    private int askPageNum() {
        while (true) {
            System.out.print("Please enter page number(positive integer only : 1 to " + Integer.MAX_VALUE + "): ");
            final String pageNum = this.scanner.nextLine();
            try {
                if (RangeList.isPositiveInteger(pageNum)) {
                    return Integer.parseInt(pageNum);
                }
            } catch (final NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String askPageRange() {
        while (true) {
            try {
                System.out.print("Please enter page range eg(1,2,5-7,9-): ");
                return this.rangeValidateInput.getValidatedRange(this.scanner.nextLine());
            } catch (final Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
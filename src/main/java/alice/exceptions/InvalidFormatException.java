package alice.exceptions;

import alice.parser.DateTimeParser;

public class InvalidFormatException extends AliceException {
    private static final String ERROR_MESSAGE = "    The Knave of Hearts noticed that your FORMATTING IS WRONG!!!\n" +
            "    Correct it before he attacks us!" + System.lineSeparator();

    public void getErrorMessage() {
        System.out.println(ERROR_MESSAGE);
    }
}
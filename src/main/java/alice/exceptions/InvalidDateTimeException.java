package alice.exceptions;

import alice.ui.Ui;
import alice.parser.DateTimeParser;

public class InvalidDateTimeException extends AliceException{
    private static final String ERROR_MESSAGE = "Your date and time format is wrong! Correct it before you get teleported to BOARDERLAND :O" +
            "    Correct format: " + DateTimeParser.getInputFormat() + System.lineSeparator();

    public void getErrorMessage() {
        System.out.println(ERROR_MESSAGE);
    }
}

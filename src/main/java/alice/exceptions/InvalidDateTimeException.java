package alice.exceptions;

import alice.ui.Ui;
import alice.parser.DateTimeParser;

public class InvalidDateTimeException extends AliceException{
    public InvalidDateTimeException() {
        System.out.println("Your date and time format is wrong! Correct it before you get teleported to BOARDERLAND :O");
        Ui.printOneTabMessage("Correct format: " + DateTimeParser.getInputFormat());
    }
}

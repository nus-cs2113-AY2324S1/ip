package RC.command;

import RC.RCException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
    public static final DateTimeFormatter strFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmma");
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "\tOOPS!!! Please enter the correct date time " +
            "format yyyy-M-d HHmm (eg. 2023-12-12 1200)";

    public static LocalDateTime getDateTime(String input) throws RCException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(input, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new RCException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
        return dateTime;
    }
}

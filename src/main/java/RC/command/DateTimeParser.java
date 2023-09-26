package RC.command;

import RC.RCException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parse date and time string.
 */
public class DateTimeParser {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
    public static final DateTimeFormatter strFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmma");
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "\tOOPS!!! Please enter the correct date time " +
            "format yyyy-M-d HHmm (eg. 2023-12-12 1200)";

    /**
     * Parses the input string into LocalDateTime object using specified date
     * and time format.
     *
     * @param input The input date and time string.
     * @return A LocalDateTime object of the parsed input.
     * @throws RCException If there is an issue parsing date and time string.
     */
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

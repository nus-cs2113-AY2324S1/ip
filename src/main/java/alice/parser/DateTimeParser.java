package alice.parser;

import alice.exceptions.InvalidDateTimeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private static final String FORMAT = "dd-MM-yyyy HHmm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public static String getInputFormat() {
        return FORMAT;
    }

    public static DateTimeFormatter getFormatter() {
        return FORMATTER;
    }
    public static LocalDateTime formatDateTime(String input) throws InvalidDateTimeException {
        LocalDateTime formattedInput;
        try {
            formattedInput = LocalDateTime.parse(input.trim(), FORMATTER);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
        }
        return formattedInput;
    }
}

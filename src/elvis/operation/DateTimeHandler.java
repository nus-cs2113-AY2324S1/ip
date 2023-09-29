package elvis.operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles date and time parsing and validation for the ELVIS chatbot.
 */
public class DateTimeHandler {
    /**
     * Standard date-time format used for parsing.
     */
    private static final DateTimeFormatter STD_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Regular expression pattern to match various date-time formats.
     */
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile(
            "(0?[1-9]|[1-2][0-9]|3[0-1])/(0?[1-9]|1[0-2])/\\d{4} \\d{4}|" +     // dd/mm/yyyy 1800
            "\\d{4}/(0?[1-9]|1[0-2])/(0?[1-9]|[1-2][0-9]|3[0-1]) \\d{4}|" +     // yyyy/mm/dd 1800
            "(0?[1-9]|[1-2][0-9]|3[0-1])-(0?[1-9]|1[0-2])-\\d{4} \\d{4}|" +     // dd-mm-yyyy 1800
            "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1]) \\d{4}|" +     // yyyy-mm-dd 1800
            "(0?[1-9]|[1-2][0-9]|3[0-1])/(0?[1-9]|1[0-2])/\\d{4} \\d{2}:\\d{2}|" +     // dd/mm/yyyy 18:00
            "\\d{4}/(0?[1-9]|1[0-2])/(0?[1-9]|[1-2][0-9]|3[0-1]) \\d{2}:\\d{2}|" +     // yyyy/mm/dd 18:00
            "(0?[1-9]|[1-2][0-9]|3[0-1])-(0?[1-9]|1[0-2])-\\d{4} \\d{2}:\\d{2}|" +     // dd-mm-yyyy 18:00
            "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1]) \\d{2}:\\d{2}|"       // yyyy-mm-dd 18:00
    );

    /**
     * Validates the date-time format based on the task type.
     *
     * @param taskType    The type of task ('D' for Deadline, 'E' for Event).
     * @param inputBuffer The input string containing the date-time information.
     * @return True if the date-time format is valid, false otherwise.
     */
    public static boolean isDateTimeValid (char taskType, String inputBuffer) {
        if (taskType == 'D') {
            CharSequence byWhen = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim(); //Get rid of "/by..."
            if (DATE_TIME_PATTERN.matcher(byWhen).matches()) {
                return true;
            }
        } else if (taskType == 'E') {
            CharSequence fromWhen = inputBuffer.substring(inputBuffer.indexOf("/from") + 5,
                    inputBuffer.indexOf("/to")).trim();
            CharSequence toWhen = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
            if (DATE_TIME_PATTERN.matcher(fromWhen).matches() && DATE_TIME_PATTERN.matcher(toWhen).matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param input The date-time string to be parsed.
     * @return A LocalDateTime object representing the parsed date-time.
     * @throws DateTimeParseException If the date-time string cannot be parsed.
     */
    public static LocalDateTime dateTimeParser(String input) throws DateTimeParseException {
        Matcher matcher = DATE_TIME_PATTERN.matcher(input);

        if (matcher.find()) {
            try {
                String matched = matcher.group();
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(getPattern(matched));
                LocalDateTime dateTime = LocalDateTime.parse(matched, inputFormatter);
                return dateTime;
            } catch (DateTimeParseException exception) {
                Ui.invalidDateTimeMessagePrinter(); 
            }

        }
        return null; // Return null if no pattern matches
    }

    /**
     * Determines the appropriate date-time pattern for parsing.
     *
     * @param input The date-time string to be parsed.
     * @return The date-time pattern as a string.
     */
    private static String getPattern(String input) {
        // Map the matched input to the corresponding pattern
        if (input.matches("\\d{1}-\\d{1}-\\d{4}\\s\\d{4}")) {
            return "d-M-yyyy HHmm";
        } else if (input.matches("\\d{2}-\\d{1}-\\d{4}\\s\\d{4}")) {
            return "dd-M-yyyy HHmm";
        } else if (input.matches("\\d{1}-\\d{2}-\\d{4}\\s\\d{4}")) {
            return "d-MM-yyyy HHmm";
        } else if (input.matches("\\d{2}-\\d{2}-\\d{4}\\s\\d{4}")) {
            return "dd-MM-yyyy HHmm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{1}\\s\\d{4}")) {
            return "yyyy-M-d HHmm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{2}\\s\\d{4}")) {
            return "yyyy-M-dd HHmm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{1}\\s\\d{4}")) {
            return "yyyy-MM-d HHmm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{4}")) {
            return "yyyy-MM-dd HHmm";

        } else if (input.matches("\\d{1}-\\d{1}-\\d{4}\\s\\d{2}:\\d{2}")) {
            return "d-M-yyyy HH:mm";
        } else if (input.matches("\\d{2}-\\d{1}-\\d{4}\\s\\d{2}:\\d{2}")) {
            return "dd-M-yyyy HH:mm";
        } else if (input.matches("\\d{1}-\\d{2}-\\d{4}\\s\\d{2}:\\d{2}")) {
            return "d-MM-yyyy HH:mm";
        } else if (input.matches("\\d{2}-\\d{2}-\\d{4}\\s\\d{2}:\\d{2}")) {
            return "dd-MM-yyyy HH:mm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{1}\\s\\d{2}:\\d{2}")) {
            return "yyyy-M-d HH:mm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{2}\\s\\d{2}:\\d{2}")) {
            return "yyyy-M-dd HH:mm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{1}\\s\\d{2}:\\d{2}")) {
            return "yyyy-MM-d HH:mm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}")) {
            return "yyyy-MM-dd HH:mm";

        } else if (input.matches("\\d{1}/\\d{1}/\\d{4}\\s\\d{4}")) {
            return "d/M/yyyy HHmm";
        } else if (input.matches("\\d{2}/\\d{1}/\\d{4}\\s\\d{4}")) {
            return "dd/M/yyyy HHmm";
        } else if (input.matches("\\d{1}/\\d{2}/\\d{4}\\s\\d{4}")) {
            return "d/MM/yyyy HHmm";
        } else if (input.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{4}")) {
            return "dd/MM/yyyy HHmm";
        } else if (input.matches("\\d{4}/\\d{1}/\\d{1}\\s\\d{4}")) {
            return "yyyy/M/d HHmm";
        } else if (input.matches("\\d{4}/\\d{1}/\\d{2}\\s\\d{4}")) {
            return "yyyy/M/dd HHmm";
        } else if (input.matches("\\d{4}/\\d{2}/\\d{1}\\s\\d{4}")) {
            return "yyyy/MM/d HHmm";
        } else if (input.matches("\\d{4}/\\d{2}/\\d{2}\\s\\d{4}")) {
            return "yyyy/MM/dd HHmm";

        } else if (input.matches("\\d{1}/\\d{1}/\\d{4}\\s\\d{2}:\\d{2}")) {
            return "d/M/yyyy HH:mm";
        } else if (input.matches("\\d{2}/\\d{1}/\\d{4}\\s\\d{2}:\\d{2}")) {
            return "dd/M/yyyy HH:mm";
        } else if (input.matches("\\d{1}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}")) {
            return "d/MM/yyyy HH:mm";
        } else if (input.matches("\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}")) {
            return "dd/MM/yyyy HH:mm";
        } else if (input.matches("\\d{4}/\\d{1}/\\d{1}\\s\\d{2}:\\d{2}")) {
            return "yyyy/M/d HH:mm";
        }else if (input.matches("\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}")) {
            return "yyyy/M/dd HH:mm";
        }else if (input.matches("\\d{4}/\\d{2}/\\d{1}\\s\\d{2}:\\d{2}")) {
            return "yyyy/MM/d HH:mm";
        } else if (input.matches("\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}")) {
            return "yyyy/MM/dd HH:mm";
        }

        return "";
    }
}

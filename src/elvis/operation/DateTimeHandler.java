package elvis.operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DateTimeHandler {
    private static final DateTimeFormatter STD_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile(
            "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4}|" +     // dd/mm/yyyy 1800
            "\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{4}|" +     // yyyy/mm/dd 1800
            "\\d{1,2}-\\d{1,2}-\\d{4}\\s+\\d{4}|" +     // dd-mm-yyyy 1800
            "\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{4}|" +     // yyyy-mm-dd 1800
            "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{2}:\\d{2}|" +     // dd/mm/yyyy 18:00
            "\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{2}:\\d{2}|" +     // yyyy/mm/dd 18:00
            "\\d{1,2}-\\d{1,2}-\\d{4}\\s+\\d{2}:\\d{2}|" +     // dd-mm-yyyy 18:00
            "\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{2}:\\d{2}|"       // yyyy-mm-dd 18:00
    );

    public static boolean isDateTimeValid (char taskType, String inputBuffer) {
        if (taskType == 'D') {
            CharSequence byWhen = inputBuffer.substring(inputBuffer.indexOf("/by") + 3).trim(); //Get rid of "/by..."
            if (!DATE_TIME_PATTERN.matcher(byWhen).matches()) {
                return false;
            }
            return true;
        } else if (taskType == 'E') {
            CharSequence fromWhen = inputBuffer.substring(inputBuffer.indexOf("/from") + 5,
                    inputBuffer.indexOf("/to")).trim();
            CharSequence toWhen = inputBuffer.substring(inputBuffer.indexOf("/to") + 3).trim();
            if (!DATE_TIME_PATTERN.matcher(fromWhen).matches() || !DATE_TIME_PATTERN.matcher(toWhen).matches()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static LocalDateTime dateTimeParser(String input) throws DateTimeParseException {
        Matcher matcher = DATE_TIME_PATTERN.matcher(input);

        if (matcher.find()) {
            String matched = matcher.group();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(getPattern(matched));
            LocalDateTime dateTime = LocalDateTime.parse(matched, inputFormatter);
            return dateTime;

        }
        return null; // Return null if no pattern matches
    }

    private static String getPattern(String input) {
        // Map the matched input to the corresponding pattern
        if (input.matches("\\d{1}-\\d{1}-\\d{4}\\s+\\d{4}")) {
            return "d-M-yyyy HHmm";
        } else if (input.matches("\\d{2}-\\d{1}-\\d{4}\\s+\\d{4}")) {
            return "dd-M-yyyy HHmm";
        } else if (input.matches("\\d{1}-\\d{2}-\\d{4}\\s+\\d{4}")) {
            return "d-MM-yyyy HHmm";
        } else if (input.matches("\\d{2}-\\d{2}-\\d{4}\\s+\\d{4}")) {
            return "dd-MM-yyyy HHmm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{1}\\s+\\d{4}")) {
            return "yyyy-M-d HHmm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{2}\\s+\\d{4}")) {
            return "yyyy-M-dd HHmm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{1}\\s+\\d{4}")) {
            return "yyyy-MM-d HHmm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{4}")) {
            return "yyyy-MM-dd HHmm";

        } else if (input.matches("\\d{1}-\\d{1}-\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "d-M-yyyy HH:mm";
        } else if (input.matches("\\d{2}-\\d{1}-\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "dd-M-yyyy HH:mm";
        } else if (input.matches("\\d{1}-\\d{2}-\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "d-MM-yyyy HH:mm";
        } else if (input.matches("\\d{2}-\\d{2}-\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "dd-MM-yyyy HH:mm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{1}\\s+\\d{2}:\\d{2}")) {
            return "yyyy-M-d HH:mm";
        } else if (input.matches("\\d{4}-\\d{1}-\\d{2}\\s+\\d{2}:\\d{2}")) {
            return "yyyy-M-dd HH:mm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{1}\\s+\\d{2}:\\d{2}")) {
            return "yyyy-MM-d HH:mm";
        } else if (input.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}")) {
            return "yyyy-MM-dd HH:mm";

        } else if (input.matches("\\d{1}/\\d{1}/\\d{4}\\s+\\d{4}")) {
            return "d/M/yyyy HHmm";
        } else if (input.matches("\\d{2}/\\d{1}/\\d{4}\\s+\\d{4}")) {
            return "dd/M/yyyy HHmm";
        } else if (input.matches("\\d{1}/\\d{2}/\\d{4}\\s+\\d{4}")) {
            return "d/MM/yyyy HHmm";
        } else if (input.matches("\\d{2}/\\d{2}/\\d{4}\\s+\\d{4}")) {
            return "dd/MM/yyyy HHmm";
        } else if (input.matches("\\d{4}/\\d{1}/\\d{1}\\s+\\d{4}")) {
            return "yyyy/M/d HHmm";
        } else if (input.matches("\\d{4}/\\d{1}/\\d{2}\\s+\\d{4}")) {
            return "yyyy/M/dd HHmm";
        } else if (input.matches("\\d{4}/\\d{2}/\\d{1}\\s+\\d{4}")) {
            return "yyyy/MM/d HHmm";
        } else if (input.matches("\\d{4}/\\d{2}/\\d{2}\\s+\\d{4}")) {
            return "yyyy/MM/dd HHmm";

        } else if (input.matches("\\d{1}/\\d{1}/\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "d/M/yyyy HH:mm";
        } else if (input.matches("\\d{2}/\\d{1}/\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "dd/M/yyyy HH:mm";
        } else if (input.matches("\\d{1}/\\d{2}/\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "d/MM/yyyy HH:mm";
        } else if (input.matches("\\d{2}/\\d{2}/\\d{4}\\s+\\d{2}:\\d{2}")) {
            return "dd/MM/yyyy HH:mm";
        } else if (input.matches("\\d{4}/\\d{1}/\\d{1}\\s+\\d{2}:\\d{2}")) {
            return "yyyy/M/d HH:mm";
        }else if (input.matches("\\d{4}/\\d{1}/\\d{2}\\s+\\d{2}:\\d{2}")) {
            return "yyyy/M/dd HH:mm";
        }else if (input.matches("\\d{4}/\\d{2}/\\d{1}\\s+\\d{2}:\\d{2}")) {
            return "yyyy/MM/d HH:mm";
        } else if (input.matches("\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}")) {
            return "yyyy/MM/dd HH:mm";
        }

        System.out.println("No Matching Pattern");
        return "dd-MM-yyyy HHmm";
    }
}

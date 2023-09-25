package elvis.operation;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateTimeHandler {
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
            String deadline = inputBuffer.replace("deadline ", "").trim();  //Get rid of "deadline "
            CharSequence byWhen = deadline.substring(deadline.indexOf("/by") + 3).trim(); //Get rid of "/by..."
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

    public static LocalDateTime dateTimeParser(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            formatter.parse(dateTime);
        } catch (DateTimeParseException exception) {
            Sys
        }
    }
}

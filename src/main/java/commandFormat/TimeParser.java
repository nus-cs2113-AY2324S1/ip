package commandFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeParser {
    /**
     *
     * @param input time string
     * @return LocalDataTIme object
     */
    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     *
     * @param dateTime object of LocalDateTime
     * @return converted string
     */
    public static String convertDateTimetoString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }


}



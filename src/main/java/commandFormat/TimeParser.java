package commandFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeParser {

    /**
     * This changes a string of time into a dataTIme object
     * It only supports string with format of DDMMYYYY HHMM format
     * @param input time string
     * @return LocalDataTIme object
     */
    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * This method can transform a dataTime object into a string information of dateTime
     * @param dateTime object of LocalDateTime
     * @return converted string
     */
    public static String convertDateTimetoString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }


}



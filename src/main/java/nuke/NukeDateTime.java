package nuke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class NukeDateTime {
    private boolean isUsingLocalDateTime;
    private LocalDateTime localDateTime;
    private String string;

    public NukeDateTime(String dateTime) {
        try {
            localDateTime = parseDateTime(dateTime);
            isUsingLocalDateTime = true;
            string = toString();
        } catch (NukeException e) {
            localDateTime = null;
            isUsingLocalDateTime = false;
            string = dateTime;
        }
    }

    @Override
    public String toString() {
        if (isUsingLocalDateTime) {
            return localDateTime.format(
                    DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm", Locale.US)
            );
        } else {
            return string;
        }
    }

    private LocalDateTime parseDateTime(String dateTime) throws NukeException {
        // Tries to parse using 10 different formats.
        // ISO_DATE; yyyy-MM-dd with optional offset
        // MM-dd
        // ISO_TIME; HH:mm:ss or HH:mm with optional offset
        // ISO_DATE_TIME; yyyy-MM-dd'T'HH:mm:ss or yyyy-MM-dd'T'HH:mm with optional offset
        // yyyy-MM-dd HH:mm:ss
        // yyyy-MM-dd HH:mm
        // MM-dd HH:mm:ss
        // MM-dd HH:mm
        // d/M/y HHmm
        // d/M/y
        try {
            return LocalDate.parse(dateTime, DateTimeFormatter.ISO_DATE).atStartOfDay();
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDate.parse(dateTime, createFormatterWithoutYear("MM-dd")).atStartOfDay();
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalTime.parse(dateTime, DateTimeFormatter.ISO_TIME).atDate(LocalDate.now());
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(dateTime, createFormatterWithoutYear("MM-dd HH:mm:ss"));
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(dateTime, createFormatterWithoutYear("MM-dd HH:mm"));
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y HHmm"));
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y")).atStartOfDay();
        } catch (DateTimeParseException ignored) { }
        throw new NukeException();
    }

    private static DateTimeFormatter createFormatterWithoutYear(String pattern) {
        return new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                .toFormatter();
    }
}

package nuke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Represents a chronological period.
 * It supports both {@link String} and {@link LocalDateTime}.
 */
public class NukeDateTime {
    private boolean isUsingLocalDateTime;
    private LocalDateTime localDateTime;
    private String string;

    /**
     * Constructs a period with a string.
     *
     * @param dateTime string indicating the period
     */
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

    /**
     * Returns a string representation of the period.
     * The format is as following.
     * <p>
     * 'MMM dd yyyy, HH:mm'
     *
     * @return a string representation of the period
     */
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

    /**
     * Parses the period from {@link String} to {@link LocalDateTime}.
     * Tries to parse using 10 different formats.
     * <p>
     * ISO_DATE; yyyy-MM-dd with optional offset<br>
     * MM-dd<br>
     * ISO_TIME; HH:mm:ss or HH:mm with optional offset<br>
     * ISO_DATE_TIME; yyyy-MM-dd'T'HH:mm:ss or yyyy-MM-dd'T'HH:mm with optional offset<br>
     * yyyy-MM-dd HH:mm:ss<br>
     * yyyy-MM-dd HH:mm<br>
     * MM-dd HH:mm:ss<br>
     * MM-dd HH:mm<br>
     * d/M/y HHmm<br>
     * d/M/y
     *
     * @param dateTime period
     * @return parsed period
     * @throws NukeDateTimeParseException if parsing fails
     */
    private LocalDateTime parseDateTime(String dateTime)
            throws NukeDateTimeParseException {
        try {
            return LocalDate.parse(
                    dateTime,
                    DateTimeFormatter.ISO_DATE
            ).atStartOfDay();
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDate.parse(
                    dateTime,
                    createFormatterWithoutYear("MM-dd")
            ).atStartOfDay();
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalTime.parse(
                    dateTime,
                    DateTimeFormatter.ISO_TIME
            ).atDate(LocalDate.now());
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(
                    dateTime,
                    DateTimeFormatter.ISO_DATE_TIME
            );
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(
                    dateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(
                    dateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            );
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(
                    dateTime,
                    createFormatterWithoutYear("MM-dd HH:mm:ss")
            );
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(
                    dateTime,
                    createFormatterWithoutYear("MM-dd HH:mm")
            );
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDateTime.parse(
                    dateTime,
                    DateTimeFormatter.ofPattern("d/M/y HHmm")
            );
        } catch (DateTimeParseException ignored) { }
        try {
            return LocalDate.parse(
                    dateTime,
                    DateTimeFormatter.ofPattern("d/M/y")
            ).atStartOfDay();
        } catch (DateTimeParseException ignored) { }

        throw new NukeDateTimeParseException();
    }

    private static DateTimeFormatter createFormatterWithoutYear(String pattern) {
        return new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                .toFormatter();
    }
}

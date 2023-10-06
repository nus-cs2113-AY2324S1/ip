package linguobot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The <code>Deadline</code> class represents a type of task with a deadline. It extends the <code>Task</code> class
 * and includes a date field to store the deadline information.
 */
public class Deadline extends Task {
    protected String date;

    /**
     * Constructs a new <code>Deadline</code> task with a description and a deadline date.
     *
     * @param description A description of the task.
     * @param date        The deadline date in the format "d/M/yyyy" and optionally "h:mm a."
     */
    public Deadline (String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Converts and returns the deadline date to a formatted date and time string.
     * @param date date in the format provided by the user ("d/M/yyyy" and optionally "h:mm a").
     * @return formatted date string in format "MMMM d yyyy" and optionally "HHmm".
     */
    public String getLocalDate(String date) {
        if (date.matches("[A-Za-z]{3} \\d{2} \\d{4} \\d{4}") || date.matches("[A-Za-z]{3} \\d{1,2} \\d{4}")) {
            // Date is already in "MMM d yyyy HHMM or MMM d yyyy" format, return as it is
            return date;
        } else if (date.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [APap][Mm]")) {
            // Date is in "d/M/yyyy h:mm a" format, convert to "MMM d yyyy HHmm"
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy h:mm a"));
            return localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        } else {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "]" + description + " (by: " + getLocalDate(date)+ ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description +
                " | " + getLocalDate(date);
    }
}

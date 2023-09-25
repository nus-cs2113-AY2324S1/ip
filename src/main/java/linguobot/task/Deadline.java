package linguobot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String date;
    public Deadline (String description, String date) {
        super(description);
        this.date = date;
    }

    public String getLocalDate(String date) {
        if (date.matches("[A-Za-z]{3} \\d{1,2} \\d{4} HHMM") || date.matches("[A-Za-z]{3} \\d{1,2} \\d{4}")) {
            // Date is already in "MMM d yyyy HHMM or MMM d yyyy" format, return as is
            return date;
        } else if (date.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [APap][Mm]")) {
            // Date is in "d/M/yyyy h:mm a" format, convert to "MMM d yyyy h:mm a"
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy h:mm a"));
            return localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        }
        else {
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

package neo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Task {
    protected String description;
    protected boolean isDone;

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private boolean hasTime(String line) {
        String[] dateAndTime = line.split(" ");
        return dateAndTime.length == 2;
    }
    public String dateTimeFormatter(String line) {
        if (hasTime(line)) {
            return formatDateAndTime(line);
        } else {
            return formatDate(line);
        }
    }
    private String formatDate(String line) {
        LocalDate date = LocalDate.parse(line, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    private String formatDateAndTime(String line) {
        LocalDateTime dateTime = LocalDateTime.parse(line, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT));
    }
    public String formatTask() {
        int status;
        if (this.getStatusIcon().equals("X")) {
            status = 1;
        } else {
            status = 0;
        }
        return status + " / " + this.description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

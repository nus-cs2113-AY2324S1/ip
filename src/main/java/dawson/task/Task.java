package dawson.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public abstract String encode();

    protected final DateTimeFormatter showDateTimeFormat = DateTimeFormatter
        .ofPattern("dd MMM yyyy, HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    protected LocalDateTime parseDateTime(String dateTimeString, boolean isStart) {
        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, isStart ? 0 : 23)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, isStart ? 0 : 59)
            .toFormatter();
        
        try {
            return LocalDateTime.parse(dateTimeString, parseFormat);
        } catch (Exception e) {
            return null;
        }
    }

}

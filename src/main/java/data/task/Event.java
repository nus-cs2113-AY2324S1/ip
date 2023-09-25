package data.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event in the TaskList.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalTime end;

    public Event (String description, LocalDateTime start, LocalTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event (String description, LocalDateTime start, LocalTime end, int setMark) {
        super(description, setMark);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getDetails() {
        return "[E]" + super.getDetails() + " (from: " + getFormattedStart() + " to: " + getFormattedEnd() + ")";
    }

    public LocalDateTime getStart() {
        return start;
    }

    public String getFormattedStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm MMMM d, yyyy");
        return start.format(formatter);
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getFormattedEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return end.format(formatter);
    }
}

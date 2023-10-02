package spaceman.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event in the TaskList.
 *
 * @see Task
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event (String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event (String description, LocalDateTime start, LocalDateTime end, int setMark) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mma dd MMMM, yyyy");
        return start.format(formatter);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getFormattedEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mma dd MMMM, yyyy");
        return end.format(formatter);
    }
}

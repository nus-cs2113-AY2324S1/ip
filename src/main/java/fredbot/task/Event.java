package fredbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a class for event tasks
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + formatDate(start) + " to: "+ formatDate(end);
    }

    @Override
    public String toFile() {
        return  "[E]" + super.toString() + " from: " + start + " to: " + end;
    }
}

package alice.tasks;

import alice.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        String typeOfTask = "[E]";
        String statusOfTask = "[" + super.getStatusIcon() + "] ";
        String startString = start.format(DateTimeParser.getFormatter());
        String endString = end.format(DateTimeParser.getFormatter());
        String task = super.getDescription() + " (from: " + startString + " to: " + endString + ")";
        return typeOfTask + statusOfTask + task;
    }

    @Override
    public String encode() {
        return String.format("Event | %s | %s | %s", super.encode(), getStart(), getEnd());
    }
}
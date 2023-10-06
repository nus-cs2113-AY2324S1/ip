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

    /**
     * Encodes the event task into a string to be stored in the text file
     *
     * @return string in the format to be stored in text file
     */
    @Override
    public String encode() {
        String encodeStart = getStart().format(DateTimeParser.getFormatter());
        String encodeEnd = getEnd().format(DateTimeParser.getFormatter());
        return String.format("Event | %s | %s | %s", super.encode(), encodeStart, encodeEnd);
    }
}
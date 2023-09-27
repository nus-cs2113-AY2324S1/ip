package elgin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static elgin.parser.Parser.DATETIME_FORMATTER;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh.mma");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        type = "E";
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        type = "E";
        this.from = from;
        this.to = to;
        setIsDone(isDone);
    }

    public String getFrom() {
        return from.format(DATETIME_FORMATTER);
    }

    public String getFromAs12HourClock() {
        return from.format(FORMATTER);
    }

    public String getTo() {
        return to.format(DATETIME_FORMATTER);
    }

    public String getToAs12HourClock() {
        return to.format(FORMATTER);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + getFromAs12HourClock() + ", to: " + getToAs12HourClock() + ")";
    }
}

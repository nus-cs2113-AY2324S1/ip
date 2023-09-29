package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    private Parser parser;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        parser = new Parser();
    }

    public Event(String description, String isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        parser = new Parser();
    }

    @Override
    public String toString() {
        String startString  = parser.convertDateTimetoString(start);
        String endString = parser.convertDateTimetoString(end);
        return "[E]" + super.toString() + " (from: " + startString + " to: " + endString + ")";
    }
}

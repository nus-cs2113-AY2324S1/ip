package mudmud.task;

import mudmud.parser.Parser;

import java.time.LocalDateTime;

/**
 * Represents an event in the list.
 */
public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    private Parser parser;

    /**
     * Creates a new event.
     *
     * @param description The description of the task.
     * @param start The start date of the task.
     * @param end The end date of the task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        parser = new Parser();
    }

    /**
     * Creates an event from the previous session.
     *
     * @param description The description of the task.
     * @param isDone Status of whether it is marked or unmarked.
     * @param start The start date of the task.
     * @param end The end date of the task.
     */
    public Event(String description, String isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        parser = new Parser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String startString  = parser.convertDateTimetoString(start);
        String endString = parser.convertDateTimetoString(end);
        return "[E]" + super.toString() + " (from: " + startString + " to: " + endString + ")";
    }
}

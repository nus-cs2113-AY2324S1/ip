package commands;

/**
 * Represents an event with a specific start and end time. It is a subclass of the Deadline class.
 */
public class Event extends Deadline {
    protected String end;

    /**
     * Constructs an Event object with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param by          The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, String by, String end) {
        super(description, by);
        this.end = end;
        this.type = 'E';
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", this.description, this.by, this.end);
    }
}

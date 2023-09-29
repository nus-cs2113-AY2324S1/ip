package magpie.task;
/**
 * Represents an <b>Event</b> Task with <code>description</code> and specified timings using <code>/from</code>
 * and <code>/to</code><br>
 * <i>Example: Math exam /from 2pm /to 4pm</i>
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for <code>description</code>, <code>isDone</code>, <code>from</code>, and <code>to</code>.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextToWrite() {
        return "E | " + parseBooleanToInt(isDone) + " | " + description + " | " + from + " | " + to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}

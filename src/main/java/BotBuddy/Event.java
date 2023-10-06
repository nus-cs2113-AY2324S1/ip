package BotBuddy;

/**
 * Represents an event.
 *
 * @see Task
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates an event object.
     *
     * @param description Description of the event.
     * @param from Start date of the event.
     * @param to End date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

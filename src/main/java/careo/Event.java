package careo;

public class Event extends Task {
    /** When the event starts */
    protected String from;
    /** When the event ends */
    protected String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Instantiates an Event instance given its parameters.
     *
     * @param description Textual description of the event.
     * @param from Time at which the event starts.
     * @param to Time at which the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns type, description, timespan and whether it is done of an event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
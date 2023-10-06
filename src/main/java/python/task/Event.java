package python.task;

/**
 * Represents a event object using start and end datetime
 */
public class Event extends Task {
    final static private String TYPE_ICON = "[E]";
    private String from, to;

    /**
     * Constructs <code>Event</code> object
     *
     * @param description The description of the task
     * @param from        The start datetime of the task
     * @param to          The end datetime of the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type icon of event
     *
     * @return Returns the type icon of event
     */
    public String getTypeIcon() {
        return TYPE_ICON;
    }

    /**
     * Returns the start datetime of the event
     *
     * @return Returns the start datetime of the event
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end datetime of the event
     *
     * @return Returns the end datetime of the event
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a human-readable string format
     *
     * @return Returns a human-readable string format
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}

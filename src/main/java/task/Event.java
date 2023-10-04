package task;

/**
 * The Event class represents a task with a description and a specific event duration.
 * It is a subclass of the Task class and includes additional functionality for managing events.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task for display purposes.
     *
     * @return A formatted string containing "[E]" for event tasks, the status icon, description,
     *         and the event duration.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a text file.
     *
     * @return A formatted string containing "E" to identify event tasks, the status icon,
     *         description, and the event duration.
     */
    @Override
    public String toText() {
        return "E" + " | " + getStatusIcon() + " | " + getDescription() + " | " + from + " - " + to;
    }
}
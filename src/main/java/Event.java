/**
 * The Event class represents a task that is an event with a specific start and end time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates a new event task with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param isDone      The completion status of the event task.
     */
    public Event(String description, String from,String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String additionalInfo, boolean isDone) {
        super(description);
    }

    /**
     * Returns a string representation of the event task, including its type marker ('[E]'),
     * description, start time, and end time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
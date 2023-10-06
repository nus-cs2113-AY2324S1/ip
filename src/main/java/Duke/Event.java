package Duke;

/**
 * Represents an Event task that extends from the Task class. An Event task has a start and end time.
 */
public class Event extends Task {

    protected String start;
    protected String end;
    
    /**
     * Constructor to create a new Event task with the given description together with a start and end time.
     * @param description The description of the Event task.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.type = "E";
    }
    
    /**
     * Get the complete description of the Event task with its type, status, and time information.
     * @return The complete description of the Event task.
     */
    @Override
    public String getDescription() {
        return "[" + type + "]" + super.getDescription() + "(from: " + start + "to: " + end + ")";
    }

    /**
     * Get the start time of the Event.
     * @return The start time of the Event.
     */
    @Override
    public String getStart() {
        return start;
    }

    /**
     * Get the end time of the Event.
     * @return The end time of the Event.
     */
    @Override
    public String getEnd() {
        return end;
    }
}

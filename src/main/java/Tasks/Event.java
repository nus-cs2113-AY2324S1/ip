package Tasks;

import Tasks.Task;

/**
 * The Event class represents a task with a specific start and end time.
 * It extends the Task class and inherits its properties and behavior.
 */
public class Event extends Task {
    private String from;
    private String by;

    /**
     * Constructs an Event object with the provided start and end times, and a description.
     *
     * @param from        The starting time of the event.
     * @param by          The ending time of the event.
     * @param description The description of the event.
     */
    public Event(String from, String by, String description) {
        // call by the superclass (task) constructor
        super(description);
        this.from = from;
        this.by = by;
        this.type = 'E';
    }

    /**
     * Returns a formatted string representation of the Event object.
     *
     * @return A string representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.by + ")";
    }
}
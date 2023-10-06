package duke;

/**
 * Represents an Event task in Duke.
 * It is a subclass of the Task class and contains information about the task's description, start, and end times.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event task with the given description, start, and end times.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the task type, which is "Event."
     *
     * @return The task type.
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time.
     */
    public String getTo() {
        return to;
    }

    /**
     * Converts the Event task to a string format for saving.
     *
     * @return The string representation of the Event task for saving.
     */
    @Override
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return "E | " + doneStatus + " | " + description + " | " + from + "-" + to;
    }

    /**
     * Converts the Event task to a string format for displaying.
     *
     * @return The string representation of the Event task for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
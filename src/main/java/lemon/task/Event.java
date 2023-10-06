package lemon.task;

/**
 * Represents a task with a start and end date/time. Inherits from the Task class.
 * An Event object corresponds to a task represented by a task description, start and end date/time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event task with the specified description, start and end date/time, and completion status.
     *
     * @param description Description of the event task.
     * @param start Start date/time of the event task.
     * @param end End date/time of the event task.
     * @param isDone Indication of whether the event task is completed.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.from = start;
        this.to = end;
    }

    /**
     * Returns a string representation of the Event task for display.
     *
     * @return Formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the event task for storage in a file.
     *
     * @return Formmated string representing the event task for storage.
     */
    public String toFile() {
        return "E" + super.toFile() + " | " + from + " | " + to;
    }
}

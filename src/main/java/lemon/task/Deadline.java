package lemon.task;

/**
 * Represents a task with a deadline. Inherits from the Task class.
 * A Deadline object corresponds to a task represented by a task description and a due date/time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a Deadline task with the specified description, due date/time, and completion status.
     *
     * @param description Description of the deadline task.
     * @param by Due date/time of the deadline task.
     * @param isDone Indication of whether the deadline task is completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return Formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task for storage in a file.
     *
     * @return Formatted string representing the deadline task for storage.
     */
    public String toFile() {
        return "D" + super.toFile() + " | " + by;
    }
}

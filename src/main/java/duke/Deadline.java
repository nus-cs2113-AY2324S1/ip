package duke;

/**
 * Represents a task with a deadline in Duke.
 * It is a subclass of the Task class and contains information about the task's description and deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the task type, which is "Deadline."
     *
     * @return The task type.
     */
    @Override
    public String getTaskType() {
        return "Deadline";
    }

    /**
     * Retrieves the deadline date of the task.
     *
     * @return The deadline date.
     */
    public String getBy() {
        return by;
    }

    /**
     * Converts the Deadline task to a string format for saving.
     *
     * @return The string representation of the Deadline task for saving.
     */
    @Override
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return "D | " + doneStatus + " | " + description + " | " + by;
    }

    /**
     * Converts the Deadline task to a string format for displaying.
     *
     * @return The string representation of the Deadline task for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

package task;

/**
 * The Deadline class represents a task with a description and a specific deadline.
 * It is a subclass of the Task class and includes additional functionality for managing deadlines.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task for display purposes.
     *
     * @return A formatted string containing "[D]" for deadline tasks, the status icon, description,
     *         and the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task suitable for saving to a text file.
     *
     * @return A formatted string containing "D" to identify deadline tasks, the status icon,
     *         description, and the deadline.
     */
    @Override
    public String toText() {
        return "D" + " | " + getStatusIcon() + " | " + getDescription() + " | " + by;
    }
}
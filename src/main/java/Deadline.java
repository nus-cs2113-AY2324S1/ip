/**
 * The Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new deadline task with the specified description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline for the task.
     * @param isDone      The completion status of the deadline task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its type marker ('[D]'),
     * description, and deadline.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
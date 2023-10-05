package neo.task;

/**
 * Represents a form of task that has a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a task with a string to indicate the due date.
     *
     * @param description This is the description of the task.
     * @param by This is the description of the due date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns The deadline in a format for saving into a .txt file.
     *
     * @return The formatted deadline.
     */
    @Override
    public String formatTask() {
        return "D | " + super.formatTask() + " | " + by;
    }

    /**
     * Returns the deadline in an easy-to-read format to the user.
     *
     * @return The formatted deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeFormatter(by) + ")";
    }
}

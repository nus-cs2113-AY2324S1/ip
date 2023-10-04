package Tasks;

/**
 * Represents a task with a deadline, which is a subclass of Task.
 */
public class DeadlineTask extends Task {
    private final String by;

    /**
     * Constructs a DeadlineTask with the given description.
     *
     * @param description The description of the task, including the deadline (if provided).
     */
    public DeadlineTask(String description) {
        super(description);
        this.by = this.extractDeadline(description);
    }

    /**
     * Extracts the deadline from the task description.
     *
     * @param description The description of the task.
     * @return The extracted deadline or "No Deadline" if not found.
     */
    private String extractDeadline(String description) {
        int index = description.indexOf("/by");
        return index != -1 ? description.substring(index + 3).trim() : "No Deadline";
    }

    /**
     * Gets the deadline associated with this task.
     *
     * @return The deadline string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the DeadlineTask.
     *
     * @return A string representation in the format: "[D][TaskDescription] (by: [Deadline])".
     */
    public String toString() {
        String superString = super.toString();
        return "[D]" + superString + " (by: " + this.by + ")";
    }
}

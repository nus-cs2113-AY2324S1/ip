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

    private String deadlineDescription(String description){
        int byIndex = description.indexOf("/by");
        if(byIndex != -1){
            return description.substring(0, byIndex).trim();
        }
        return description.trim(); // If "/by" is not found, return the whole description
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

    public String getDeadlineDescription(){
        return deadlineDescription(super.getDescription());
    }
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.getDeadlineDescription() + " (by: " + this.by + ")";
    }
}

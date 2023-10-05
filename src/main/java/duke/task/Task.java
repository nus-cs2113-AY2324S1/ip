package duke.task;

/**
 * Generic task class. Contains different methods to format a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a formatted task string for printing purposes.
     *
     * @return String of formatted task.
     */
    public String getFormattedTask() {
        return "[" + getStatusIcon() + "] " + description;
    }
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter to allow isDone status to be modified
     *
     * @param isDone boolean of new status
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string of the task serialized to be saved
     * in the text file.
     *
     * @return Serialized string
     */
    public String getSerializedString() {
        return isDone + "|" + description;
    }

    /**
     * Returns description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }
}

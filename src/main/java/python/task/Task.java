package python.task;

/**
 * Represents a abstract Task object
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs <code>Task</code> object
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of task
     *
     * @return Returns the status icon of task
     */
    public String getStatusIcon() {
        return (isDone() ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns the type icon of task
     *
     * @return Returns the type icon of task
     */
    public abstract String getTypeIcon();

    /**
     * Returns the description of the task
     *
     * @return Returns the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the is completed or not
     *
     * @return Returns whether the is completed or not
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the status of completion of the task
     *
     * @param done The status to be set
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a human-readable string format
     *
     * @return Returns a human-readable string format
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + getDescription();
    }
}

package Tasks;

/**
 * Represents a generic task in the Barbie-themed task manager.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon ("X" if task is done, " " if task not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of the task.
     *
     * @param status The status of the task (true if done, false if not done).
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representing the task, in the format: "[X] Description" if done.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
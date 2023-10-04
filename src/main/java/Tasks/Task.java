package Tasks;

/**
 * The Task class represents a task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Set initial completion to false
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, " " (a space) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format: "[status icon] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

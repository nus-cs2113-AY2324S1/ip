package lemon.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description Description of the task.
     * @param isDone Indication of whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

<<<<<<< HEAD
    public String getDescription() {
        return description;
    }

=======
    /**
     * Marks the task as completed.
     */
>>>>>>> branch-A-JavaDoc
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns an icon representing the status of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return Formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task for storage in a file.
     *
     * @return Formatted string representing the task for storage.
     */
    public String toFile() {
        return " | " + this.getStatus() + " | " + description;
    }
}

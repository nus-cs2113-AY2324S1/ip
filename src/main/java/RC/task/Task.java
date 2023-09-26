package RC.task;

/**
 * Represents a basic task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task with the specified description, and set it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // returns "X" if task is done, " " otherwise
    }

    /**
     * Returns a string of the task description.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns a string representation of the task for saving to storage.
     *
     * @return A string representation of the task.
     */
    public String formatString() {
        int markTask = 0;
        if (this.getStatusIcon().equals("X")) {
            markTask = 1;
        }
        return markTask + " | " + this.getDescription();
    }
}

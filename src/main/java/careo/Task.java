package careo;

public class Task {
    /** Textual description of the task */
    protected String description;
    /** Whether the task has been completed */
    protected boolean isDone;

    /**
     * Instantiates a Task instance from a description.
     *
     * @param description Textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Generates and returns a status icon based on whether the task is done.
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns type, description and whether it is done of a task.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
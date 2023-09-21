package Data;

/**
 * Abstract class for all tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * @return String description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the status of the task
     * @return String representation of the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
package mudmud.task;

/**
 * Represents a task in the list.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task from the previous session.
     *
     * @param description The description of the task.
     * @param isDone Status of whether it is marked or unmarked.
     */
    public Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals("true");
    }

    /**
     * Returns a status icon based on isDone.
     *
     * @return A status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return A string that is going to be printed.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

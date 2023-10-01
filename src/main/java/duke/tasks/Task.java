package duke.tasks;

/**
 * Superclass of all the potential classes for the command "todo", "event", "deadline"
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To display the corresponding Icon for the current status
     * @return A String represents its current status
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Override the toString() method of the superclass Object,
     * so it can return its own description
     * @return the description of task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

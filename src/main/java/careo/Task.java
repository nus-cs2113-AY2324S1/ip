package careo;


import java.io.Serializable;

public class Task implements Serializable {
    /** Textual description of the task */
    protected String description;
    /** Whether the task has been completed */
    protected boolean isDone;

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

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
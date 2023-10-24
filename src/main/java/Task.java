/**
 * The Task class represents a task with a description and completion status.
 */
public class Task {
    private String description;
    public boolean isDone;

    /**
     * Creates a new task with the specified description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Gets the marker for the task's completion status ('X' if done, ' ' if not done).
     *
     * @return The marker representing the completion status.
     */
    public String getMarker(){
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString(){
        return "[" + getMarker() + "] " + description;
    }
}
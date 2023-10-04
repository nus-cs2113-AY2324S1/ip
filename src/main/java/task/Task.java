package task;

/**
 * The Task class represents a task with a description and completion status.
 * It also keeps track of the total number of tasks created.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTask = 0;

    /**
     * Constructs a new Task with the given description and marks it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTask++;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, " " (a space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Gets the total number of tasks created.
     *
     * @return The number of tasks created.
     */
    public static int getNumberOfTask() {
        return numberOfTask;
    }

    /**
     * Decrements the total number of tasks created by one.
     */
    public static void deleteOneTask() {
        numberOfTask--;
    }

    /**
     * Returns a string representation of the task for display purposes.
     *
     * @return A string containing the status icon and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getDescription();
    }

    /**
     * Returns a string representation of the task suitable for saving to a text file.
     *
     * @return A formatted string containing the status icon and description.
     */
    public String toText() {
        return " " + " | " + getStatusIcon() + " | " + getDescription();
    }
}
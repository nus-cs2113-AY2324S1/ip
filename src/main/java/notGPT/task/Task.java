package notGPT.task;

/**
 * The Task class represents a task with a name and completion status.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified name, initially marked as not done.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Gets the name of the task.
     *
     * @return The task's name as a String.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
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
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Gets the task's timing.
     *
     * @return An array of Strings representing the task's timing.
     */
    public String getTaskTiming() {
        return "";
    }

    /**
     * Converts the task to a formatted String representation.
     *
     * @return A String representing the task's status and name.
     */
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + statusIcon + "] " + taskName;
    }

    /**
     * Converts the task to a formatted String representation for file storage.
     *
     * @return A String representing the task's status and name for file storage.
     */
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return "|" + status + "|" + taskName;
    }
}


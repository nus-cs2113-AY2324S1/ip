package bob.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param description contains description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets deadline type icon (D).
     *
     * @return deadline type icon.
     */
    public String getTypeIcon() {
        return " ";
    }

    /**
     * Gets completion status of task. If task is completed, returns "X". Else returns " ".
     *
     * @return Returns status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets message to show when a new task is added to the task list.
     *
     * @param taskCount Gets index of the task in the task list. This should be 1-indexed.
     * @return Returns the formatted message reflecting the added task.
     */
    public String getTaskAdded(int taskCount) {
        return String.format("Got it. I've added this task:\n" +
                        "\t  %s\n" +
                        "\tNow you have %d %s in the list",
                getTask(), taskCount, (taskCount == 1 ? "task" : "tasks"));
    }

    public String getTaskDeleted(int taskCount) {
        return String.format("Noted. I've removed this task:\n" +
                        "\t  %s\n" +
                        "\tNow you have %d %s in the list",
                getTask(), taskCount, (taskCount == 1 ? "task" : "tasks"));
    }

    /**
     * Gets formatted task for printing.
     *
     * @return formatted task for printing.
     */
    public String getTask() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), description);
    }

    /**
     * Gets formatted task to be stored in file.
     *
     * @return formatted task to be stored in file.
     */
    public String getTaskForFile() {
        return String.format("%s | %b | %s", getTypeIcon(), isDone, description);
    }

    /**
     * Sets status of task. Mark {@param isDone} as {@code true} when task is complete, and
     * {@code false} otherwise.
     *
     * @param isDone Boolean value to set the task completion status.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

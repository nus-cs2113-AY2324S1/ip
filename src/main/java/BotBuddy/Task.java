package BotBuddy;

/**
 * Abstract class that represents tasks.
 *
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /** Number of tasks in the task list */
    private static int noOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        noOfTasks++;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public static int getNoOfTasks() {
        return noOfTasks;
    }

    public static void setNoOfTasks(int noOfTasks) {
        Task.noOfTasks = noOfTasks;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}

package RC.task;

public abstract class Task {
    private String description;
    private boolean isDone;
    private static int numTasks = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public static int getNumTasks() {
        return numTasks;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String formatString() {
        int markTask = 0;
        if (this.getStatusIcon().equals("X")) {
            markTask = 1;
        }
        return markTask + " | " + this.getDescription();
    }
}

package RC.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // returns "X" if task is done, " " otherwise
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

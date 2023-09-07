package Duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Method to return the task description
    public String getDescription() {
        return this.description;
    }

    // Set the task status to done [X]
    public void markDone() {
        this.isDone = true;
    }

    // Set the task status to not done [ ]
    public void unmarkDone() {
        this.isDone = false;
    }

}
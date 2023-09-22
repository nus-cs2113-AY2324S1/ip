package duke;

public class Task {
    private String description;
    private boolean isDone;

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() { // Public getter method for isDone
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
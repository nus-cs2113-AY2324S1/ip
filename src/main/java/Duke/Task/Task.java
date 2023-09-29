package Duke.Task;

/**
 * Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    String taskType;
    String symbol;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskType = "Task";
        symbol = "t";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return taskType;
    }

    public String convertToSaveFormat() {
        String doneMarker = isDone ? "1" : "0";
        return symbol + " | " + doneMarker + " | " + description;
    }

    @Override
    public String toString() {
        String marker = this.isDone ? "x" : " ";
        return "[" + marker + "] " + this.description;
    }
}
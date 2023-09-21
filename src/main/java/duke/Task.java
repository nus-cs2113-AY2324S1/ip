package duke;
import java.io.Serializable;
public class Task implements Serializable{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return "Task";
    }

    // Convert a task to a string format for saving
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return getTaskType() + " | " + doneStatus + " | " + description;
    }

    // Create a method to parse a string into a Task object
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = new Task(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}


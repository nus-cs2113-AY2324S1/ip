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

    public static Task fromFileString(String fileString) throws DukeException {
        String[] parts = fileString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (taskType.equals("D")) {
            if (parts.length >= 4) {
                String by = parts[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            }
        } else if (taskType.equals("E")) {
            if (parts.length >= 5) {
                String from = parts[3];
                String to = parts[4];
                Event event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            }
        } else {
            Task task = new Task(description);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        }

        throw new DukeException("Incomplete data for a task");
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


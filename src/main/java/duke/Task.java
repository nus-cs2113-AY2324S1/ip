package duke;

import java.io.Serializable;

/**
 * Represents a task in the task list.
 * A task has a description and can be marked as done or undone.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the task type identifier.
     * "T" represents a Todo task.
     *
     * @return The task type identifier.
     */
    public String getTaskType() {
        return "T";
    }

    /**
     * Converts the task to a string format for saving.
     *
     * @return The task in a string format for saving.
     */
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return getTaskType() + " | " + doneStatus + " | " + description;
    }

    /**
     * Creates a task from a string in file format.
     *
     * @param fileString The string representation of the task in file format.
     * @return The task created from the file string.
     * @throws DukeException If the file string is incomplete or in an invalid format.
     */
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
        } else if (taskType.equals("T")) {
            Task task = new Todo(description);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        }

        throw new DukeException("Incomplete data for a task");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

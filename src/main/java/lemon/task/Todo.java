package lemon.task;

/**
 * Represents a task without date/time. Inherits from the Task class.
 * A Todo object corresponds to a task represented by a task description.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description and completion status.
     * @param description Description of the todo task.
     * @param isDone Indication of whether the todo task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task for display.
     *
     * @return Formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for storage in a file.
     *
     * @return Formatted string representing the todo task for storage.
     */
    public String toFile() {
        return "T" + super.toFile();
    }
}

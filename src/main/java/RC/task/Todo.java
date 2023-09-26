package RC.task;

/**
 * Represents the todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for saving to storage.
     *
     * @return A string representation of todo task.
     */
    @Override
    public String formatString() {
        return "T | " + super.formatString();
    }
}

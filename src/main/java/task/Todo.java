package task;

/**
 * The Todo class represents a task with a description and no specific deadline or event time.
 * It is a subclass of the Task class and includes additional functionality for managing todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for display purposes.
     *
     * @return A formatted string containing "[T]" for todo tasks, the status icon, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task suitable for saving to a text file.
     *
     * @return A formatted string containing "T" to identify todo tasks, the status icon,
     *         and description.
     */
    @Override
    public String toText() {
        return "T" + " | " + getStatusIcon() + " | " + getDescription();
    }
}
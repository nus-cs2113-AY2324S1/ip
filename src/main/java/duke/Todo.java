package duke;

/**
 * Represents a Todo task in Duke.
 * It is a subclass of the Task class and contains information about the task's description.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves the task type, which is "Todo."
     *
     * @return The task type.
     */
    @Override
    public String getTaskType() {
        return "Todo";
    }

    /**
     * Converts the Todo task to a string format for saving.
     *
     * @return The string representation of the Todo task for saving.
     */
    @Override
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return "T | " + doneStatus + " | " + description;
    }

    /**
     * Converts the Todo task to a string format for displaying.
     *
     * @return The string representation of the Todo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
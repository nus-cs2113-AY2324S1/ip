package Tasks;

/**
 * Represents a todo task in the barbie-themed task manager.
 */
public class Todo extends Task {
    /**
     * Initializes a new todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its status and description.
     *
     * @return A string representing the todo task, in the format: "[T] [X] Description"
     */
    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }
}

package BotBuddy;

/**
 * Represents a todo.
 *
 * @see Task
 */
public class Todo extends Task {
    /**
     * Creates a task object.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

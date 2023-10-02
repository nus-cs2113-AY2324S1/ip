package duke.task;

/**
 * Represents a todo in the list.
 */
public class Todo extends Task {

    /**
     * Creates a new todo.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo from the previous session.
     *
     * @param description The description of the task.
     * @param isDone Status of whether it is marked or unmarked.
     */
    public Todo(String description, String isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

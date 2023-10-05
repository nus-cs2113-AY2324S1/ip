package Tasks;

import Tasks.Task;

/**
 * The Todo class represents a task without a specific due date or time.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the provided description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the Todo object.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

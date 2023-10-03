package duke;

/**
 * Represents a todo task consisting of a description of the task.
 */
public class Todo extends Task {

    /**
     * Generates new Todo object.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the single line representation of the todo which is used by the UI.
     * @return string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the single line representation of the todo which is used for the file.
     * @return string representation of the todo.
     */
    @Override
    public String toSaveFormat() {
        return ("T | " + super.toSaveFormat());
    }
}

package Duke;

/**
 * Represents a Todo task that extends from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructor to create a new Todo task with the given description.
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Get the complete description of the Todo task with its type and status.
     * @return The complete description of the Todo task.
     */
    @Override
    public String getDescription() {
        return "[" + type + "]" + super.getDescription();
    }
}
package task;
/**
 * Represents a todo task
 */
public class Todo extends Task {
    /**
     * Creates a new todo task object
     *
     * @param description The details of the task
     */
    public Todo(String description) {
        super(description, false);
    }
    /**
     * Override the type of the task to 'T'
     */
    @Override
    public String getType() {
        return "T";
    }
    /**
     * Override the full detail of the task
     */
    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}

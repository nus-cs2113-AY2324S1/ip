package elgin.task;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo task.
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
        type = "T";
    }

    /**
     * Instantiates a new Todo.
     *
     * @param description Description of the Todo.
     * @param isDone True if the task is completed, else False.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        type = "T";
        setIsDone(isDone);
    }

}

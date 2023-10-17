package taskmanagement;

/**
 * ToDos class in Zran application.
 * It represents a task of class 'ToDos'
 * Extends the base Task class.
 */
public class ToDos extends Task {

    /**
     * Constructs an instance of 'ToDos' with the given description and deadline.
     *
     * @param description The description of the todos task.
     */
    public ToDos(String description) {
        super(description);
        taskType = "T";
    }

    /**
     * Constructs an instance of 'ToDos' with the given description and deadline.
     *
     * @param description The description of the todos task.
     * @param isDone      The completion status of the task.
     */
    public ToDos(String description, boolean isDone) {
        super(description);
        taskType = "T";
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description;
    }
}


package alan.data.task;
/**
 * Represents a todo task. A <code>Todo</code> object corresponds to
 * a task consisting of description and 'T' task type
 */
public class Todo extends Task {
    private boolean isDone;

    public Todo(String description) {
        super(description, TaskType.T);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}

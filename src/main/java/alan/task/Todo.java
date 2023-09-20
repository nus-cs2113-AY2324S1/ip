package alan.task;

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

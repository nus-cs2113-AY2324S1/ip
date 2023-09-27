package taskmanagement;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
        taskType = "T";
    }

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


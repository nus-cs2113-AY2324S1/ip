package taskmanagement;

public class ToDos extends Task {


    public ToDos(String description) {
        super(description);
        taskType = "T";
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description;
    }
}


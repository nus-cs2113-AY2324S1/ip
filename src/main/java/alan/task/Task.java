package alan.task;

public class Task {
    private String description;
    private TaskType type;
    private boolean isDone;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public TaskType getTaskType() {
        return type;
    }

    public void setTaskType(TaskType type) {
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public int getStatusValue() {
        return (isDone ? 1 : 0);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}


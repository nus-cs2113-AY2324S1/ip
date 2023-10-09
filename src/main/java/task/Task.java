package task;

public abstract class Task {

    protected String description;
    protected boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public abstract String getCode();
}

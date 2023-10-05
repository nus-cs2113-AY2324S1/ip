package python.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone() ? "[X]" : "[ ]"); // mark done task with X
    }

    public abstract String getTypeIcon();

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + getDescription();
    }

    public String toDiskSaveFormat() {
        return getTypeIcon() + " | " +  getStatusIcon() + " | " + getDescription();
    }
}

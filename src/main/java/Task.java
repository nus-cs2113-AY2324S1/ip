public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }
    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getTypeIcon() { return type; }

}
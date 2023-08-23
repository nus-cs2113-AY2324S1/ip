public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTask() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

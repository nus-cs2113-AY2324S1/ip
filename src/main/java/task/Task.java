package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Basic constructor for Task
     * @param description Task description or name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    public boolean getIsDone() {
        return isDone;
    }
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

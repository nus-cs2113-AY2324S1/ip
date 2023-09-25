package linguobot.task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

//    public abstract String toString(String date);

    public String toFileString() {
        return "";
    }

//    public abstract String toFileString(String date);
}

package simon.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDescription() {
        return description;
    }
    public boolean getStatus() {
        return isDone;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void unmarkAsDone() {
        isDone = false;
    }
    public static int getNumberOfTask() {
        return numberOfTask;
    }
    public String toString() {

        return "[" + getStatusIcon() + "]" + " " + getDescription();
    }
}

public class Task {
    protected String description;
    protected boolean isDone;
    private int id;
    private static int numberOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = numberOfTask + 1;
        numberOfTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
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

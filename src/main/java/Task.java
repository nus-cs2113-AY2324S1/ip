public class Task {
    protected String description;
    protected boolean isDone;
    private int id;
    public static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

//        numberOfTasks++;
        id = numberOfTasks;
    }
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:";
    }
}
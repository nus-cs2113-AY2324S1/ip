/** Parent class defining a task with description, process and agenda overview */
public class Task {
    private String description;
    private boolean isDone;
    private static int numberOfTotalTasks = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTotalTasks++;
    }

    public char getStatusIcon() {
        return (isDone ? 'X' : ' '); // returns a cross when the task is done and a blank else
    }
    public void markTaskAsDone() {
        isDone = true;
    }
    public void markTaskAsNotDone() {
        isDone = false;
    }
    /** Retrieves the true task description from the user input */
    public String getDescription() {
        boolean containsTime = description.contains("/");
        if (containsTime) {
            return description.split("/")[0];
        } else {
            return description;
        }
    }
    public boolean isDone() {
        return isDone;
    }
    public static int getNumberOfTotalTasks() {
        return numberOfTotalTasks;
    }
    @Override
    public String toString() {
        String summary = String.format("[%c] %s", getStatusIcon(), getDescription());
        return summary;
    }
}


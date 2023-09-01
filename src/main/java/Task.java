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
        return (isDone ? 'X' : ' '); // mark done task with X
    }
    public void markTaskAsDone() {
        isDone = true;
    }
    public void markTaskAsNotDone() {
        isDone = false;
    }
    public String getDescription() {
        boolean containsTime = description.contains("/");
        String commandlessDescription = description.substring(description.indexOf(" "));
        if (containsTime == true) {
            return commandlessDescription.substring(0, commandlessDescription.indexOf('/'));
        } else {
            return commandlessDescription;
        }
    }
    public boolean isDone() {
        return isDone;
    }
    public static int getNumberOfTotalTasks() {
        return numberOfTotalTasks;
    }
    public String toString() {
        String summary = String.format("[%c] %s", getStatusIcon(), getDescription());
        return summary;
    }
}


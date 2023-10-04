package doli.tasks;

/** Parent class defining a task with description, process and agenda overview */
public abstract class Task {
    private final String description;
    public boolean isDone;
    private static int numberOfTotalTasks = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTotalTasks++;
    }
    public char getStatusIcon() {
        return (isDone ? 'X' : ' '); // returns a cross (X) when the task is done and a blank ( ) else
    }
    public void markTaskAsDone() {
        isDone = true;
    }
    public void markTaskAsNotDone() {
        isDone = false;
    }

    /** Retrieves the true task description from the user input
     *  removes any time bound information such as deadlines, start- or endtimes
     */
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
    /**
     * Overrides the general objects method .toString() allowing for
     * a tailored use incorporating details of the task such as its description and whether it is done or not.
     * @return a String summarising the tasks's details
     */
    @Override
    public String toString() {
        String summary = String.format("[%c] %s", getStatusIcon(), getDescription());
        return summary;
    }
}


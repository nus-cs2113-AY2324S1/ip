package doli.tasks;

/**
 * <h3>Task class</h3>
 * The Task class is the corresponding superclass for ToDo, Deadline and Event.
 * It allows the user to manage tasks which have a description and can be marked as completed or not.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public abstract class Task {
    /** Description of the task */
    private final String description;
    /** Boolean indicating whether the task is completed or not */
    public boolean isDone;
    /** Counter for the total number of created tasks */
    private static int numberOfTotalTasks = 0;

    /**
     * Constructs an object of type Task and sets it as not yet done by default.
     *
     * @param description of type String describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTotalTasks++;
    }

    /**
     * Gets the status icon, meaning a cross (X) when the task is done and a blank ( ) else.
     *
     * @return a character 'X' or ' ' depending on whether the task is completed.
     */
    public char getStatusIcon() {
        return (isDone ? 'X' : ' ');
    }

    /**
     * Marks the task as done (and sets its icon as 'X').
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not yet done.
     */
    public void markTaskAsNotDone() {
        isDone = false;
    }

    /**
     * Retrieves the true task description from the user input
     * removes any time bound information such as deadlines, start- or end-times.
     */
    public String getDescription() {
        boolean containsTime = description.contains("/");
        if (containsTime) {
            return description.split("/")[0];
        } else {
            return description;
        }
    }

    /**
     * Returns true if the task is completed and false otherwise.
     *
     * @return a boolean value indicating whether the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the number of total tasks created.
     *
     * @return an integer counting the total tasks.
     */
    public static int getNumberOfTotalTasks() {
        return numberOfTotalTasks;
    }
    /**
     * Overrides the general objects method toString() allowing for
     * a tailored use incorporating details of the task such as its description and whether it is done or not.
     *
     * @return a String summarising the tasks' details.
     */
    @Override
    public String toString() {
        String summary = String.format("[%c] %s", getStatusIcon(), getDescription());
        return summary;
    }
}


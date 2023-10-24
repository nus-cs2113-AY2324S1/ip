package duke;

/**
 * Task is an abstract class representing a generic task in the Hilary robot.
 * It includes fields and methods for retrieving the task's description and status.
 * Task objects serve as the base class for specific task types (Todo, Deadline, Event).
 * Subclasses extend Task to provide additional functionality and formatting.
 * <p>
 * Fields:
 * - description: The description of the task.
 * - isDone: A boolean variable indicating whether the task is marked as done.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a `Task` object with the specified description and initializes it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task (whether it is done or not).
     *
     * @return `true` if the task is marked as done, `false` otherwise.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Gets an icon representing the status of the task.
     *
     * @return "X" if the task is done, " " (a space) if it's not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task, marking it as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return A string representing the task, including its status icon and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method to be implemented by subclasses to get the event time associated with the task.
     *
     * @return A string representing the event time (applicable to event-type tasks) or an empty string.
     */
    abstract public String getEventTime();
}

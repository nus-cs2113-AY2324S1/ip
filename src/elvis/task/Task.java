package elvis.task;

import java.time.LocalDateTime;

/**
 * Abstract class representing a generic task.
 * Stores the description and completion status of a task.
 */
public abstract class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Flag indicating whether the task is completed.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task instance.
     *
     * @param description    The description of the task.
     * @param isDoneFromFile The completion status read from a file.
     */
    public Task(String description, int isDoneFromFile) {
        this.description = description.trim();
        this.isDone = isDoneFromFile == 1;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return "X" if the task is completed, otherwise " ".
     */
    public String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Retrieves the boolean value of the completion status.
     *
     * @return true if the task is completed, otherwise false.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param truthValue The new completion status.
     */
    public void setStatus(boolean truthValue) {
        this.isDone = truthValue;
    }

    /**
     * Retrieves the type of the task.
     * This method is intended to be overridden by subclasses.
     *
     * @return '?' as a placeholder for the task type.
     */
    public char getTaskType() {
        return '?';
    }

    /**
     * Placeholder method for getting the task's date and time.
     * Intended to be overridden by subclasses.
     *
     * @return A default LocalDateTime object.
     */
    public LocalDateTime getDateTime() {
        return LocalDateTime.parse("0000-01-01T00:00");
    }

    /**
     * Placeholder method for getting the task's start date and time.
     * Intended to be overridden by subclasses.
     *
     * @return A default LocalDateTime object.
     */
    public LocalDateTime getStartDateTime() {
        return LocalDateTime.parse("0000-01-01T00:00");
    }

    /**
     * Placeholder method for getting the task's end date and time.
     * Intended to be overridden by subclasses.
     *
     * @return A default LocalDateTime object.
     */
    public LocalDateTime getEndDateTime() {
        return LocalDateTime.parse("0000-01-01T00:00");
    }
}

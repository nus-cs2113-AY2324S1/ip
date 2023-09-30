package tasks;

/**
 * Represents a task in the Duke application.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;


    /*
     * Initializes a new Task with the given description.
     * 
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * Returns the status icon of the task.
     * 
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); 
    }

    /*
     * Marks the task as done.
     * 
     * @param isDone The status of the task.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = true;
    }
    
    /*
     * Marks the task as undone.
     * 
     * @param isDone The status of the task.
     */
    public void markAsUndone(boolean isDone) {
        this.isDone = false;
    }

    /*
     * Returns the string representation of the task.
     * 
     * @return The string representation of the task.
     */
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /*
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /*
     * Converts the task object to a data representation for saving to a file.
     * 
     * @return The data representation of the task.
     */
    public abstract String toData();
}

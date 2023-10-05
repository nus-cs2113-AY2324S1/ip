package dawson.task;

/**
 * The abstract base class representing a generic task.
 *
 * Foundation for various types of tasks, such as Todo, Deadline, or Event tasks.
 * It provides task description and status tracking common attributes and methods.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Abstract method for encoding a Task into a string representation.
     *
     * This method is implemented by concrete subclasses Todo, Deadline, or Event tasks to provide a
     * customized string representation for each type of task.
     *
     * @return A string representation of a task, used for saving a task's details for storage.
     */
    public abstract String encode();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}

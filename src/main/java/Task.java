public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if(description.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Check whether the task is done.
     *
     * @return Whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Converts the Deadline object into a string.
     *
     * @return String format of the object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the object into a string to be stored in file.
     *
     * @return String format of the object to be stored in file.
     */
    public String toFile() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }
}

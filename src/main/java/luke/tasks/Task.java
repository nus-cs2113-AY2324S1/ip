package luke.tasks;

/**
 * The Task Class represents a generic task in the LukeTime application.
 * It serves as the base class for various types of tasks and provides common task attributes and methods.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and sets its initial completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
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
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task and displays a corresponding message.
     *
     * @param done true if the task is done, false otherwise.
     */
    public void setDone(boolean done) {
        if (done) {
            System.out.println("\tWoohoo! You have accomplished:");
        } else {
            System.out.println("\tHA! You still have to complete:");
        }
        isDone = done;
    }

    /**
     * Provides a default implementation for printing a guide specific to each task type.
     */
    public void printGuide() {
        //Default Implementation: do nothing
    }

    /**
     * Returns a string representation of the task for memory storage.
     *
     * @return A string representation of the task for memory storage.
     */
    public String memoryString() {
        String isDoneString;

        if (isDone()) {
            isDoneString = "[X]";
        } else {
            isDoneString = "[ ]";
        }

        return "[T]" + isDoneString + " task" + getDescription();
    }
}
/**
 * The Task class represents a task with a description and status. It is an abstract type
 */
package Chatty.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Initializes a new Deadline object with the given description and deadline date.
    `*/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * marks item done
     */
    public void mark(){
        this.isDone = true;
    }
    /**
     * marks item not done
     */
    public void unmark(){
        this.isDone = false;
    }
    /**
     * Gets the description for the deadline task
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the status icon for the task.
     *
     * @return The status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Checks if item is done
     *
     * @return The boolean value if item is done or not
     */
    public boolean getIsDone(){
        return isDone;
    }
    /**
     * @return The String format that is saved
     */
    public String saveFormat() {
        return "";
    }
    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

}

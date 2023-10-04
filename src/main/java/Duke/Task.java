/**
 * Task is an abstract class representing a generic task in the Duke application.
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
 * @since 2023-09-30
 */

package Duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus(){
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone(){
        isDone = true;
    }

    public void unmark(){
        isDone = false;
    }
    public String getDescription(){
        return description;
    }
    public String toString(){
        return("[" + getStatusIcon() + "] " + description);
    }
    abstract public String getEventTime();

}
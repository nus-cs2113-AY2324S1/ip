package linguobot.task;

/**
 * The <code>Task</code> class represents a generic task with a description and completion status.
 * It serves as the base class for specific task types such as `Todo`, `Deadline`, and `Event`.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String toFileString() {
        return "";
    }
  
}

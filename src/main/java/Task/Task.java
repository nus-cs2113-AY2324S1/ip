package Task;

/**
 * class indicating an instance of a task object
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    //constructor
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Checks if a task has been marked as done or undone
     * @return status icon "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * unmarks a task
     */
    public void unmark(){
        this.isDone = false;
    }

    /**
     * marks a task with "X".
     */
    public void mark(){
        this.isDone = true;
    }

    /**
     * returns string representation of task
     * @return a string representation of the task with its status
     */
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}

package duke.tasks;

/**
 * Represents a task.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a Task object with the given task name and sets its status to not done.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName){
        this.taskName = taskName;
        this.setDone(false);
    }

    /**
     * Constructs a Task object with the given task name and status.
     *
     * @param taskName The name of the task.
     * @param isDone The status of the task.
     */
    public Task(String taskName, boolean isDone){
        this.taskName = taskName;
        this.setDone(isDone);
    }

    /**
     * Returns the status of the Task object.
     *
     * @return The status of the Task object.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Sets the status of the Task object.
     *
     * @param isDone The new status of the Task object.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the name of the Task object.
     *
     * @return The name of the Task object.
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * Sets the name of the Task object.
     *
     * @param taskName The new name of the Task object.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representation of the Task object.
     */
    public String toString(){
        if(this.isDone){
            return "[X] " + this.taskName;
        }else{
            return "[ ] " + this.taskName;
        }
    }

}
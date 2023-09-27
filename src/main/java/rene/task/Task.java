package rene.task;
/**
 * Represents all tasks in the task list in general.
 */
public class Task {
    public enum TaskType {TODO, EVENT, DEADLINE, DEFAULT};
    private TaskType taskType;
    private String description;
    private boolean done;
    /**
     * Mark a task as done.
     */
    public void markAsDone(){
        done = true;
    }
    /**
     * Mark a task as not done.
     */
    public void markAsNotDone(){
        done = false;
    }
    /**
     * Creates a new task with a blank description,
     * not done status and default task type.
     */
    public Task(){
        description = "";
        done = false;
        taskType = TaskType.DEFAULT;
    }
    /**
     * Creates a new task with a given description,
     * not done status and default task type.
     *
     * @param description Description given to the task.
     */
    public Task(String description){
        this.description = description;
        done = false;
        taskType = TaskType.DEFAULT;
    }
    /**
     * Creates a new task with a given description,
     * not done status and given task type.
     *
     * @param description Description given to the task.
     * @param taskType Type given to the task.
     */
    public Task(String description, TaskType taskType){
        this.description = description;
        done = false;
        this.taskType = taskType;
    }
    /**
     * Checks if a task has been done.
     */
    public boolean taskIsDone(){
        return done;
    }
    /**
     * Returns task description.
     */
    public String getTaskDescription(){
        return description;
    }
    /**
     * Returns task timing details.
     */
    public String getTaskTiming(boolean useDefaultTiming){
        return "";
    }
    /**
     * Returns task type.
     */
    public TaskType getTaskType(){
        return taskType;
    }
}

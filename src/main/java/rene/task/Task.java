package rene.task;
public class Task {
    public enum TaskType {TODO, EVENT, DEADLINE, DEFAULT};
    private TaskType taskType;
    private String description;
    private boolean done;

    public void markAsDone(){
        done = true;
    }
    public void markAsNotDone(){
        done = false;
    }
    public Task(){
        description = "";
        done = false;
        taskType = TaskType.DEFAULT;
    }
    public Task(String description){
        this.description = description;
        done = false;
        taskType = TaskType.DEFAULT;
    }
    public Task(String description, TaskType taskType){
        this.description = description;
        done = false;
        this.taskType = taskType;
    }
    public boolean taskIsDone(){
        return done;
    }
    public String getTaskDescription(){
        return description;
    }

    public String getTaskTiming(){
        return "";
    }
    public TaskType getTaskType(){
        return taskType;
    }
}

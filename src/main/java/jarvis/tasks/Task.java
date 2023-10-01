package jarvis.tasks;

public class Task {
    public enum TaskType {TODO, EVENT, DEADLINE, DEFAULT};
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getDescription(){
        return this.description;
    }
    // find out how to retrieve the description without making the method static

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick symbol once done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
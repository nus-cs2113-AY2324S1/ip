package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public boolean getCompletionStatus(){
        return this.isDone;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString(){
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }

}

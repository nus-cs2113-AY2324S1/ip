package duke;

public class Task {
    protected String description;
    protected boolean isDone;

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

    @Override
    public String toString(){
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }

}

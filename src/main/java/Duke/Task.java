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
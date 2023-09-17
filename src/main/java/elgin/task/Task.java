package elgin.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getIsDone() {
        return (isDone ? "X" : " ");
    }

    public String getIsDoneAsOneOrZero() {
        return (isDone ? "1" : "0");
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "[" + getType() + "][" + getIsDone() + "] " + getDescription();
    }
}

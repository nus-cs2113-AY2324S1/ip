package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskType() {
        return "TASK";
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
public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); 
    }

    public void markAsDone(boolean isDone) {
        this.isDone = true;
    }
    
    public void markAsUndone(boolean isDone) {
        this.isDone = false;
    }

    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public String getDescription() {
        return this.description;
    }
}

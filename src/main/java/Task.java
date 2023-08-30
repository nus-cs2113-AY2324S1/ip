public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "[X]" : "[ ]"); //return X symbols when done, else return empty space
    }

    public void markAsDone(boolean isDone) {
        this.isDone = true;
    }
    
    public void markAsUndone(boolean isDone) {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}

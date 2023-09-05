public class Task {
    protected String taskName;
    protected boolean isDone;

    //constructor
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    //check if task is marked or unmarked
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //unmark task
    public void unmark(){
        this.isDone = false;
    }

    //mark task
    public void mark(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}

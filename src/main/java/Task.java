public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName){
        this.isDone = false;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        if(!this.isDone) {
            return ("[ ] " + this.taskName);
        } else {
            return ("[X] " + this.taskName);
        }
    }


    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}

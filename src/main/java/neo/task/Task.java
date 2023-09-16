package neo.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String formatTask() {
        int status;
        if (this.getStatusIcon().equals("X")) {
            status = 1;
        } else {
            status = 0;
        }
        return status + " / " + this.description;
    }
}

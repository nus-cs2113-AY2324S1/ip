public class Task {
    protected String task;
    protected boolean isDone;

    public Task() {
        setTask("");
        setDone(false);
    }

    public Task(String task) {
        setTask(task);
        setDone(false);
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}

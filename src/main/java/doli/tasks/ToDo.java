package doli.tasks;

/** Subclass of Task, specifying normal todo tasks */
public class ToDo extends Task{
    protected boolean isDone;
    public ToDo(String description) {
        super(description);
        isDone = false;
    }
    public void setDone(boolean done) {
        this.isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

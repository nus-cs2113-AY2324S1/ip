package doli.tasks;

/** Subclass of Task, specifying tasks containing a deadline */
public class Deadline extends Task{
    protected String deadline;
    protected boolean isDone;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.isDone = false;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDone(boolean done) {
        this.isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        String summary = String.format("[D] %s (%s)", super.toString(), // D stands for Deadline
                deadline.replace("by", "by:"));
        return summary;
    }
}

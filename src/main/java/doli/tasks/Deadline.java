package doli.tasks;

/** Subclass of Task, specifying tasks containing a deadline */
public class Deadline extends Task{
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public String getDeadline() {
        return deadline;
    }
    @Override
    public String toString() {
        String summary = String.format("[D] %s (%s)", super.toString(), deadline.replace("by", "by:"));
        return summary;
    }
}

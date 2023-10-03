package alan.data.task;
/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to
 * a task consisting of description, by time period and D task type
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.D);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }
}

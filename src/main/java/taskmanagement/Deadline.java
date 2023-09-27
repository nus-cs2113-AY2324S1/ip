package taskmanagement;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        taskType = "D";
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        taskType = "D";
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
    public String getBy(){  return by;  }

}

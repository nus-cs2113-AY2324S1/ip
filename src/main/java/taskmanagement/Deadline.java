package taskmanagement;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        taskType = "D";
    }
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        taskType = "D";
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (by: " + by.format(formatter) + ")";
    }
    public LocalDate getBy(){  return by;  }

}

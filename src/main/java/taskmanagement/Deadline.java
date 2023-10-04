package taskmanagement;

import java.time.LocalDate;

/**
 * Deadline class in Zran application.
 * It represents a task of class 'Deadline' which consists of an additional variable 'by'.
 * Extends the base Task class.
 */
public class Deadline extends Task {
    protected LocalDate by;
    /**
     * Constructs an instance of 'Deadline' with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline for the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        taskType = "D";
    }

    /**
     * Constructs an instance of 'Deadline' with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline for the task.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
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

package task;

import java.time.LocalDate;

/**
 * A Deadline is a Task which has both a description and a due date.
 */
public class Deadline extends Task {

    protected LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getCode() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dueDate + ")";
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }
}

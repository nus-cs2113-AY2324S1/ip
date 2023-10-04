package task;

/**
 * A Deadline is a Task which has both a description and a due date.
 */
public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    // String[] in the format:
    // details[0] = description
    // details[1] = due date
    public Deadline(String[] details) {
        super(details[0]);
        this.dueDate = details[1];
    }

    public String getCode() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dueDate + ")";
    }

    public String getDueDate() {
        return this.dueDate;
    }
}

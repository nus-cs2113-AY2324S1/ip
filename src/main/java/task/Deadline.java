package task;

public class Deadline extends Task {

    protected String dueDate;

    /**
     * Constructor for deadline
     * @param description Task description
     * @param dueDate Due Date for the deadline, in 11PM/DD/MM/YY
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}

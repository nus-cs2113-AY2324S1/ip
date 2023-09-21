package Data;

/**
 * Class to represent a deadline task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline task
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline
     * @return String representation of the deadline
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

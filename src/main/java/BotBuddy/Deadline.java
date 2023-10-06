package BotBuddy;

/**
 * Represents a deadline.
 *
 * @see Task
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a deadline object.
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

package careo;

/**
 * Represents a certain kind of task due at a certain date.
 */
public class Deadline extends Task {
    /** By when the task must be completed */
    protected String by;

    /**
     * Retrieves the by date of this deadline.
     *
     * @return The date as string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Instantiates a deadline given its description and by date.
     *
     * @param description Textual description of the deadline.
     * @param by The time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns type, description and by time of a deadline.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
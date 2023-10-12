package python.task;

/**
 * Represents a deadline object using due date
 */
public class Deadline extends Task {
    final static private String TYPE_ICON = "[D]";
    private String by;

    /**
     * Constructs <code>Deadline</code> object
     *
     * @param description The description of the task
     * @param by          The due datetime
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type icon of deadline
     *
     * @return Returns the type icon of deadline
     */
    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }

    /**
     * Returns the due datetime of deadline
     *
     * @return Returns the due datetime of deadline
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a human-readable string format
     *
     * @return Returns a human-readable string format
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }
}

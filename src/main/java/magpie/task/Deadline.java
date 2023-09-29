package magpie.task;
/**
 * Represents a <b>Deadline</b> Task with <code>description</code> and deadline <code>/by</code><br>
 * <i>Example: return book /by 2pm</i>
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for <code>description</code>, <code>isDone</code>, and <code>by</code>.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextToWrite() {
        return "D | " + parseBooleanToInt(isDone) + " | " + description + " | " + by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }


}

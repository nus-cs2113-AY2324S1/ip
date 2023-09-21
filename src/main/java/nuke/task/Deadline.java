package nuke.task;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a task that has a deadline,
     * with the name and the deadline.
     *
     * @param name name of the task.
     * @param by deadline of the task.
     */
    public Deadline(String name, String by) {
        super(name);
        setBy(by);
    }

    /**
     * Returns deadline of the task.
     *
     * @return deadline of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets deadline of the task.
     *
     * @param by deadline of the task
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getBy());
    }

    @Override
    public String formatData() {
        return String.format("%s / %s", super.formatData(), getBy());
    }

    public static final String TYPE = "D";
}

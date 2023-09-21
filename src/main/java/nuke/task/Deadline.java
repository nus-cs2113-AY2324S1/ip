package nuke.task;

import nuke.NukeDateTime;

import static nuke.task.TaskParser.TASK_FORMAT_SEPARATOR;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private NukeDateTime by;

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
        return by.toString();
    }

    /**
     * Sets deadline of the task.
     *
     * @param by deadline of the task
     */
    public void setBy(String by) {
        this.by = new NukeDateTime(by);
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
        return super.formatData() + TASK_FORMAT_SEPARATOR + getBy();
    }

    public static final String TYPE = "D";
}

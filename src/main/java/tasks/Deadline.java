package tasks;


/**
 *
 * Deadline class that represents the deadline task in the task list with an actual deadline.
 */
public class Deadline extends Task {

    private String by;

    /**
     *
     * Creates deadline task.
     *
     * @param name the name of the deadline
     *
     * @param by the deadline of the deadline task
     */
    public Deadline(String name, String by) {
        super(name);
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

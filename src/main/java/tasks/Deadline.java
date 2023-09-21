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
        super(name, "D");
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    public String toSaveString() {
        return super.toSaveString() + "|" + by;
    }
}

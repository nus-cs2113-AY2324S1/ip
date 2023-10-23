package duke.tasks;
/**
 * A subclass extends Task that handles the command "deadline"
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Override the toString() method of the superclass,
     * so it can return its own description
     * @return the description of deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

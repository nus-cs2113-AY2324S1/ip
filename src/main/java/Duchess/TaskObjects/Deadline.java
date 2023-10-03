package Duchess.TaskObjects;

/** Class for Deadline tasks. Extension of Task class.
 * 
 */
public class Deadline extends Task {

    private String deadline;

    /** Empty constructor. */
    public Deadline() {
        super();
    }

    /** Main constructor for Deadline class.
     * @param name Name of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /** Converts Deadline object to string representation for display. */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /** Converts Deadline object to string representation for saving. */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.deadline;
    }
}    
/**
 * The Deadline class represents a task with a deadline date.
 */
package Chatty.tasks;

public class Deadline extends Task {
    private String by;

    /**
     * * Initializes a new Deadline object with the given description and deadline date.
    */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Gets the by date for the deadline task
     */
    public String getBy() {
        return by;
    }
    /**
     * Gets the description for the deadline task
     */
    @Override
    public String getDescription() {
        return "[D][" + getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }
    /**
     * returns the format in which it is saved
     */
    @Override
    public String saveFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + description + " | " + by;
    }
}

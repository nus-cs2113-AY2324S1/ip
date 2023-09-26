package task;

/**
 * Represents a deadline task with an additional 'by' variable to store due date
 */
public class Deadline extends Task {
    protected String by;
    /**
     * Creates a new deadline task object
     *
     * @param description The details of the task
     * @param by The due date of the task
     */
    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }
    /**
     * Override the type of the task to 'D'
     */
    @Override
    public String getType() {
        return "D";
    }
    /**
     * Override the excess information output of the task
     */
    @Override
    public String getExcess() {
        return by;
    }
    /**
     * Override the full detail of the task
     */
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + by + ")";
    }


}

package Duke;

/**
 * Represents a Deadline task that extends from the Task class. A Deadline task has a specific when time.
 */
public class Deadline extends Task {

    protected String when;
    
    /**
     * Constructor to create a new Deadline task with the given description and when time.
     * @param description The description of the Deadline task.
     * @param when The when time of the Deadline task.
     */
    public Deadline(String description, String when) {
        super(description);
        this.when = when;
        this.type = "D";
    }
    
    /**
     * Get the complete description of the Deadline task with its type, status, and when time.
     * @return The complete description of the Deadline task.
     */
    @Override
    public String getDescription() {
        return "[" + type + "]" + super.getDescription() + "(by: " + when + ")";
    }

    /**
     * Get the when time of the task.
     * @return The when time of the task.
     */
    @Override
    public String getWhen() {
        return when;
    }
}

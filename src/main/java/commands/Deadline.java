package commands;

/**
 * Represents a task with a specific deadline. It is a subclass of the ToDo class.
 */
public class Deadline extends ToDo {

    protected String by;

    /**
     * Constructs a Deadline object with a description and a deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = 'D';
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", this.description, this.by);
    }
}

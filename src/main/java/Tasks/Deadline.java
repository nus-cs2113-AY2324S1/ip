package Tasks;

import Tasks.Task;

/**
 * The Deadline class represents a task with a specific due date.
 * It extends the Task class and inherits its properties and behavior.
 */
public class Deadline extends Task {
    private String due;

    /**
     * Constructs a Deadline object with the provided description and due date.
     *
     * @param description The description of the deadline.
     * @param due         The due date of the deadline.
     */
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
        this.type = 'D';
    }
    // appends "[D]" to the beginning of the string.
    // Then, it calls super.toString(), ie. it calls the toString() method of the superclass
    // then adds the due date
    /**
     * Returns a formatted string representation of the Deadline object.
     *
     * @return A string representing the Deadline.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (by:"+ this.due + ")";
    }
}

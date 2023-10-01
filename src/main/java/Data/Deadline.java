package Data;

import java.time.LocalDate;

/**
 * Class to represent a deadline task
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline task
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public Deadline (String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline
     * @return String representation of the deadline
     */
    public String getByString() {
        return this.by.format(Task.DATE_FORMAT);
    }

    public String getByStringToSave() {
        return this.by.toString();
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getByString() + ")";
    }
}

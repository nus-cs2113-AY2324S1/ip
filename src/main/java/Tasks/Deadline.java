package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Barbie-themed task manager.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Initializes a new Deadline task with the given description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     *  Gets the formatted deadline date and time.
     *
     * @return The formatted deadline date and time.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the deadline date and time.
     *
     * @param by The new deadline date and time.
     */
    public void setBy(LocalDateTime by) {
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the Deadline task, including its status, description, and deadline.
     *
     * @return A string representing the Deadline task, in the format: "[D] [X] Description (by: Deadline)".
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by + ")";
    }
}

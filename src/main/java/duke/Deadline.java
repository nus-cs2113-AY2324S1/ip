package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    /**
     * Constructor for Deadline
     * @param description description of the deadline
     * @param dueDate due date of the described task
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the single line representation of the deadline which is used by the UI.
     * @return string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DATE_TIME_FORMATTER) + ")";
    }


    /**
     * Returns the single line representation of the deadline which is used for the file.
     * @return string representation of the deadline.
     */
    @Override
    public String toSaveFormat() {
        return ("D | " + super.toSaveFormat() + " | " + dueDate.format(DATE_TIME_FORMATTER));
    }
}

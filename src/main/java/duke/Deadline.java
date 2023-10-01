package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task which has a description and a due date.
 */
public class Deadline extends Task{

    private LocalDateTime dueDate;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    /**
     * Constructor for Deadline
     * @param description description of the deadline
     * @param dueDate due date of the described task
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;

    }

    @Override
    /**
     * Returns the single line representation of the deadline which is used by the UI.
     * @return string representation of the deadline.
     */
    public String toString(){
        return "[D]" + super.toString() + " (by: " + dueDate.format(DTF) + ")";
    }

    @Override
    /**
     * Returns the single line representation of the deadline which is used for the file.
     * @return string representation of the deadline.
     */
    public String toFileString() {
        return ("D | " + super.toFileString() + " | " + dueDate.format(DTF));
    }
}

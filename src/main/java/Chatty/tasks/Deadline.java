/**
 * The Deadline class represents a task with a deadline date.
 */
package Chatty.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * * Initializes a new Deadline object with the given description and deadline date.
    */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    /**
     * Gets the by date for the deadline task
     */
    public LocalDate getBy() {
        return by;
    }
    /**
     * Gets the description for the deadline task
     */
    @Override
    public String getDescription() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[D][" + getStatusIcon() + "] " + super.getDescription() + " (by: " + formattedDate + ")";
    }
    /**
     * returns the format in which it is saved
     */
    @Override
    public String saveFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + description + " | " + by;
    }
}

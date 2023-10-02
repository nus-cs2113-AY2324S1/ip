package spaceman.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline in the TaskList.
 *
 * @see Task
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline (String description, LocalDateTime by, int setMark) {
        super(description, setMark);
        this.by = by;
    }

    @Override
    public String getDetails() {
        return "[D]" + super.getDetails() + " (by: " + getFormattedBy() + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }

    public String getFormattedBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mma dd MMMM, yyyy");
        return by.format(formatter);
    }
}
package nupjuk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * nupjuk.Deadline class
 * tasks with deadline
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Make description with datetimeformat
     *
     * @return description in deadline format
     */
    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);
        // delete "(by:" and ")"
        return String.format("%s (by: %s)", description, deadline.format(formatter));
    }
}

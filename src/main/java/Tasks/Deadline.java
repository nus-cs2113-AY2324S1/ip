package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    public String getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by + ")";
    }
}

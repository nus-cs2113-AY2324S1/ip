package fredbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public void setBy(LocalDate by) {
        this.by = by;
    }
    public LocalDate getBy() {
        return by;
    }

    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + formatDate(by);
    }

    @Override
    public String toFile() {
        return "[D]" + super.toString() + " by: " + by;
    }
}

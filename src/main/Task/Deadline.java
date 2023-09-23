package Task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    public String toFile() {
        return "D," + (isCompleted() ? "1" : "0") + "," + getName() + "," + getBy();
    }
}

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

    /**
     * Converts the object into a string format to be stored in the file.
     * 
     * @return Formatted string to be stored in the file.
     */
    public String toFile() {
        return "D," + (isCompleted() ? "1" : "0") + "," + getName() + "," + getBy();
    }
}

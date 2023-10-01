package Data;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline (String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getByString() {
        return this.by.format(Task.DATE_FORMAT);
    }

    public String getByStringToSave() {
        return this.by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getByString() + ")";
    }
}

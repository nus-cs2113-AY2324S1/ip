package MySun.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline (String description, LocalDateTime dueDate, int setMark) {
        super(description, setMark);
        this.dueDate = dueDate;
    }

    public LocalDateTime getBy() {
        return this.dueDate;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + getFormattedBy() + ")";
    }

    public String getFormattedBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mma dd MMMM, yyyy");
        return dueDate.format(formatter);
    }
}


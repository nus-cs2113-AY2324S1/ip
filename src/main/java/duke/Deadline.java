package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.taskType ,super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")));
    }
}
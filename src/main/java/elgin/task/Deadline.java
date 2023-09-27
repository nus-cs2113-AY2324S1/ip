package elgin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static elgin.parser.Parser.DATETIME_FORMATTER;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        type = "D";
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        type = "D";
        this.by = by;
        setIsDone(isDone);
    }

    public String getBy() {
        return by.format(DATETIME_FORMATTER);
    }

    public String getByAs12HourClock() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh.mma");
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getByAs12HourClock() + ")";
    }
}

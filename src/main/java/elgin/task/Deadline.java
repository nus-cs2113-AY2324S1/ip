package elgin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static elgin.parser.Parser.DATETIME_FORMATTER;


public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of Deadline.
     * @param by Ending date time of Deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        type = "D";
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     * Called when loading Deadline from saved file.
     *
     * @param description Description of Deadline.
     * @param by Ending date time of Deadline.
     * @param isDone True if the Deadline is completed, else False
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        type = "D";
        this.by = by;
        setIsDone(isDone);
    }

    /**
     * Gets Deadline ending date time in 24 hours clock format.
     *
     * @return LocalDateTime of Deadline end
     */
    public String getBy() {
        return by.format(DATETIME_FORMATTER);
    }

    /**
     * Gets Deadline ending date time in 12 hours clock format.
     *
     * @return LocalDateTime of Deadline end
     */
    public String getByAs12HourClock() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh.mma");
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getByAs12HourClock() + ")";
    }
}

package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task which is a task with a start date and an end date.
 */
public class Event extends Task {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    /**
     * Constructor for Event class.
     * @param description Description of the event.
     * @param startDate Start date of the event.
     * @param endDate End date of the event.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the single line representation of the event which is used by the UI.
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        String startEndDescription = " (from: " + startDate.format(DATE_TIME_FORMATTER) + " to: " + endDate.format(DATE_TIME_FORMATTER) + ")";
        return ("[E]" + super.toString() + startEndDescription);
    }

    /**
     * Returns the single line representation of the event which is used for the file.
     * @return string representation of the event.
     */
    @Override
    public String toSaveFormat() {
        String startEndDescription = " | " + startDate.format(DATE_TIME_FORMATTER) + " | " + endDate.format(DATE_TIME_FORMATTER);
        return ("E | " + super.toSaveFormat() + startEndDescription);
    }
}

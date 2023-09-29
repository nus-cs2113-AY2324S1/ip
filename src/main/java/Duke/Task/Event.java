package Duke.Task;

import java.time.LocalDate;

/**
 * A specific type of Tasks that contains task description, start date and end date.
 */

public class Event extends Task {
    protected LocalDate startDateTime;
    protected LocalDate endDateTime;

    public Event(String description, LocalDate startDateTime, LocalDate endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        taskType = "Event";
        symbol = "E";
    }

    /**
     * Encodes Event data for file saving.
     *
     * @return Encoded Event data in String format.
     */
    @Override
    public String convertToSaveFormat() {
        String doneMarker = isDone ? "1" : "0";
        return symbol + " | " + doneMarker + " | " + description + " /from " + startDateTime
                + " /to " + endDateTime;
    }

    /**
     * Override the printing of Event.
     *
     * @return String format of Event for printing.
     */
    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from:" + startDateTime
                + " to:" + endDateTime + ")";
    }
}

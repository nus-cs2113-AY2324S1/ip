package RC.task;

import RC.RCException;
import RC.command.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents the event task.
 */
public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private static final String MESSAGE_INVALID_DATE_TIME = "\tOOPS!!! End date should come after start date";

    /**
     * Constructs event task with specified description, start date-time, and end date-time.
     *
     * @param description The description of the event task.
     * @param startDateTime The start date and time of the event task.
     * @param endDateTime The end date and time of the the event task
     * @throws RCException If there is an issue parsing date and time.
     */
    public Event(String description, String startDateTime, String endDateTime) throws RCException {
        super(description);
        this.startDateTime = DateTimeParser.getDateTime(startDateTime);
        this.endDateTime = DateTimeParser.getDateTime(endDateTime);

        if (this.endDateTime.isBefore(this.startDateTime)) {
            throw new RCException(MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDateTime.format(DateTimeParser.strFormatter) +
                " to: " + this.endDateTime.format(DateTimeParser.strFormatter) + ")";
    }

    /**
     * Returns a string representation of the event task for saving to storage.
     *
     * @return A string representation of event task.
     */
    @Override
    public String formatString() {
        return "E | " + super.formatString() + " | " + this.startDateTime.format(DateTimeParser.dateFormatter) +
                " | " + this.endDateTime.format(DateTimeParser.dateFormatter);
    }
}

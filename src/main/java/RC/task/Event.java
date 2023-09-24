package RC.task;

import RC.RCException;
import RC.command.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private static final String MESSAGE_INVALID_DATE_TIME = "\tOOPS!!! End date should come after start date";

    public Event(String description, String startDateTime, String endDateTime) throws RCException {
        super(description);
        this.startDateTime = DateTimeParser.getDateTime(startDateTime);
        this.endDateTime = DateTimeParser.getDateTime(endDateTime);

        if (this.endDateTime.isBefore(this.startDateTime)) {
            throw new RCException(MESSAGE_INVALID_DATE_TIME);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDateTime.format(DateTimeParser.strFormatter) +
                " to: " + this.endDateTime.format(DateTimeParser.strFormatter) + ")";
    }

    @Override
    public String formatString() {
        return "E | " + super.formatString() + " | " + this.startDateTime.format(DateTimeParser.dateFormatter) +
                " | " + this.endDateTime.format(DateTimeParser.dateFormatter);
    }
}

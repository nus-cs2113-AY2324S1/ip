package RC.task;

import RC.RCException;
import RC.command.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(String description, String dateTime) throws RCException{
        super(description);
        this.dateTime = DateTimeParser.getDateTime(dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeParser.strFormatter) + ")";
    }

    @Override
    public String formatString() {
        return "D | " + super.formatString() + " | " + this.dateTime.format(DateTimeParser.dateFormatter);
    }
}

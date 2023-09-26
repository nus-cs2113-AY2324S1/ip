package RC.task;

import RC.RCException;
import RC.command.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    /**
     * Constructs deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param dateTime The deadline date and time of the task.
     * @throws RCException If there is an issue parsing date and time.
     */
    public Deadline(String description, String dateTime) throws RCException{
        super(description);
        this.dateTime = DateTimeParser.getDateTime(dateTime);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeParser.strFormatter) + ")";
    }

    /**
     * Returns a string representation of the deadline task for saving to storage.
     *
     * @return A string representation of deadline task.
     */
    @Override
    public String formatString() {
        return "D | " + super.formatString() + " | " + this.dateTime.format(DateTimeParser.dateFormatter);
    }
}

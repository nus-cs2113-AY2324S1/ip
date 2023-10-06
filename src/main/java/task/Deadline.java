package task;

import commandFormat.TimeParser;
import exception.DukeException;

import java.sql.Time;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public static Deadline newDdl(String userCommand) throws DukeException {
        // command format: deadline return book /by Sunday
        if (!(userCommand.contains("/by"))){
            throw new DukeException("Oh, no! I cannot detect the keyword '/by' ");
        }
        userCommand = userCommand.substring(9);
        int indexOfBy = userCommand.indexOf("/by");
        String ddlTask = userCommand.substring(0, indexOfBy).trim();
        String due = userCommand.substring(indexOfBy + 4);

        return new Deadline(ddlTask, due);
    }

    @Override
    public String toString() {
         LocalDateTime  time = TimeParser.parseDateTime(this.due);
         String dueTime = TimeParser.convertDateTimetoString(time);
        return "[D]" + super.toString() + " (by: " + dueTime + ")";
    }
}

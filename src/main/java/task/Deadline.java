package task;

import commandFormat.TimeParser;
import exception.DukeException;
import exception.InvalidTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    /**
     *
     * @param userCommand  Whole userInput start from "deadline"
     * @return Deadline object
     * @throws DukeException  Raises exception if invalid due time while able to parse the input time
     *                        If failed to parse the time, just assume valid input
     */
    public static Deadline newDdl(String userCommand) throws DukeException, InvalidTimeException {
        // command format: deadline return book /by Sunday
        if (!(userCommand.contains("/by"))){
            throw new DukeException("Oh, no! I cannot detect the keyword '/by' ");
        }
        userCommand = userCommand.substring(9);
        int indexOfBy = userCommand.indexOf("/by");
        String ddlTask = userCommand.substring(0, indexOfBy).trim();
        String due = userCommand.substring(indexOfBy + 4);

        try{
            LocalDateTime  dueTime = TimeParser.parseDateTime(due);
            LocalDateTime now = LocalDateTime.now();
             if (dueTime.isBefore(now)) {
                 throw new InvalidTimeException("Invalid Time! Deadline is over already!");
            }
        } catch (DateTimeParseException d){
            /* do nothing */
        }
        return new Deadline(ddlTask, due);
    }

    /**
     * print example: [D][ ] return book (by: Friday)
     * if failed to parse input time, assume user input is valid
     * @return string of task message
     */
    @Override
    public String toString() {
        try {
            LocalDateTime  time = TimeParser.parseDateTime(this.due);
            String dueTime = TimeParser.convertDateTimetoString(time);
            return "[D]" + super.toString() + " (by: " + dueTime + ")";
        } catch (Exception e) {
            return "[D]" + super.toString() + " (by: " + this.due + ")";
        }
    }
}

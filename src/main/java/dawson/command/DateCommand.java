package dawson.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.task.TaskList;

/**
 * Finds event or deadline tasks that have matching date as a given query date
 */
public class DateCommand extends Command {

    private static final String MESSAGE_CANNOT_PARSE_DATE = "Unable to parse input date!";
    private static final String MESSAGE_NO_TIME_FOUND = "No deadline or event tasks found on: %s!";
    private static final String MESSAGE_FOUND_TIME_PROMPT = "Here are the tasks found for (%s):";

    private String payload;

    public DateCommand(String payload) {
        this.payload = payload;
    }

    @Override
    public CommandResult execute(TaskList list) throws DawsonException {
        try {
            LocalDate inputDate = Parser.parseDate(payload);
            ArrayList<String> result = list.findTasksWithDate(inputDate);

            if (result.isEmpty()) {
                return new CommandResult(String.format(MESSAGE_NO_TIME_FOUND, Parser.userDateFormat.format(inputDate)));
            }

            result.add(0, String.format(MESSAGE_FOUND_TIME_PROMPT, Parser.userDateFormat.format(inputDate)));
            return new CommandResult(result.toArray(new String[0]));

        } catch (DateTimeParseException e) {
            throw new DawsonException(MESSAGE_CANNOT_PARSE_DATE);
        }
    }
    
}

package dawson.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import dawson.command.Command;
import dawson.command.DeadlineCommand;
import dawson.command.DeleteCommand;
import dawson.command.EventCommand;
import dawson.command.ExitCommand;
import dawson.command.FindCommand;
import dawson.command.InvalidCommand;
import dawson.command.ListCommand;
import dawson.command.MarkCommand;
import dawson.command.DateCommand;
import dawson.command.TodoCommand;
import dawson.command.UnmarkCommand;
import dawson.exception.DawsonException;
import dawson.task.*;
import dawson.ui.Messages;

/**
 * Parses user input.
 */
public class Parser {

    public static final DateTimeFormatter userDateFormat = 
        DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static final DateTimeFormatter userDateTimeFormat = DateTimeFormatter
        .ofPattern("dd MMM yyyy, HH:mm");

    public static final String COMMAND_DELIMITER = "\\s+";
    public static final String TASK_DELIMITER = "\\|";

    /**
     * Parses a user input string into a corresponding Command object.
     *
     * @param input The user input string to be parsed.
     * @return Command object that represents the parsed command or an InvalidCommand if the input is not recognized.
     */
    public static Command parseCommand(String input) {
        String[] split = input.split(COMMAND_DELIMITER, 2); // split the input into command and arguments
        String commandString = split[0].toLowerCase(); // First word is command
        String payload = split.length > 1 ? split[1] : ""; // Remaining input text

        switch (commandString) {
            case Command.TODO_COMMAND:
                return new TodoCommand(payload);
            case Command.DEADLINE_COMMAND:
                return new DeadlineCommand(payload);
            case Command.EVENT_COMMAND:
                return new EventCommand(payload);
            case Command.LIST_COMMAND:
                return new ListCommand();
            case Command.DATE_COMMAND:
                return new DateCommand(payload);
            case Command.FIND_COMMAND:
                return new FindCommand(payload);
            case Command.DELETE_COMMAND:
                return new DeleteCommand(payload);
            case Command.MARK_COMMAND:
                return new MarkCommand(payload);
            case Command.UNMARK_COMMAND:
                return new UnmarkCommand(payload);
            case Command.EXIT_COMMAND:
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    };

    /**
     * Parses an encoded task string into a corresponding Task object.
     *
     * @param encodedTaskString The encoded task string to be parsed.
     * @return Task object that represents the parsed task.
     * @throws DawsonException If there is an issue parsing the task string or it cannot be recognized.
     */
    public static Task parseTask(String encodedTaskString) throws DawsonException {
        final int TYPE_INDEX = 0;

        String[] split = encodedTaskString.split(TASK_DELIMITER, 2);
        String taskType = split[TYPE_INDEX].trim(); // Get first letter

        switch (taskType) {
            case "T":
                return TodoTask.decodeTodo(encodedTaskString);
            case "D":
                return DeadlineTask.decodeDeadline(encodedTaskString);
            case "E":
                return EventTask.decodeEvent(encodedTaskString);
        }
        throw new DawsonException(Messages.MESSAGE_PARSE_TASK_ERROR);
    }

    /**
     * Parses a date and time string into a LocalDateTime object, with optional time and default values.
     * Date and Time should be in the format of <strong> dd/MM/yyyy HHmm</strong>. If no time is provided, time will be set to 00:00
     * if isStart is true, else time set to 23:59 if isStart is false.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @param isStart Boolean flag indicating whether the time represents a start time (true) or end time (false).
     * @return A LocalDateTime object representing the parsed date and time, or null if parsing fails.
     */
    public static LocalDateTime parseDateTime(String dateTimeString, boolean isStart) {
        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, isStart ? 0 : 23)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, isStart ? 0 : 59)
            .toFormatter();
        
        try {
            return LocalDateTime.parse(dateTimeString, parseFormat);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parses a date string into a LocalDate object using the format "day/month/year" or "dd/MM/yyyy".
     *
     * @param dateString The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the input date string does not match the specified format.
     */
    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateString, parseFormat);
    }

}

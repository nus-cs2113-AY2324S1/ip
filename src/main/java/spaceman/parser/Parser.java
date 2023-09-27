package spaceman.parser;

import static spaceman.ui.Messages.MESSAGE_EMPTY_TODO;
import static spaceman.ui.Messages.MESSAGE_EMPTY_DEADLINE;
import static spaceman.ui.Messages.MESSAGE_EMPTY_EVENT;

import spaceman.commands.*;

import spaceman.data.TaskList;
import spaceman.data.exception.IncompleteDescriptionException;
import spaceman.data.exception.InvalidActionException;

import spaceman.data.task.Deadline;
import spaceman.data.task.Event;
import spaceman.data.task.Todo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Returns the Command inputted by the user.
     * @param text input text from user
     * @param tasks list of tasks
     * @return a Command object to be executed
     * @throws InvalidActionException If user input is not a valid action.
     * @throws IncompleteDescriptionException If user input is incomplete.
     */
    public static Command parseCommand(String text, TaskList tasks) throws InvalidActionException,
            IncompleteDescriptionException {
        String[] commandTypeAndArgs = text.split(" ", 2);
        String commandType = commandTypeAndArgs[0];
        String arguments;
        if (commandTypeAndArgs.length > 1) {
            arguments = commandTypeAndArgs[1];
        } else {
            arguments = null;
        }
        try {
            switch (commandType){
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskIndex(arguments));
            case "unmark":
                return new UnmarkCommand(parseTaskIndex(arguments));
            case "todo":
                return new AddCommand(parseTodo(arguments));
            case "deadline":
                return new AddCommand(parseDeadline(arguments));
            case "event":
                return new AddCommand(parseEvent(arguments));
            case "delete":
                return new DeleteCommand(parseTaskIndex(arguments));
            case "find":
                return new FindCommand(arguments);
            default:
                return new InvalidCommand();
            }
        } catch (IncompleteDescriptionException e) {
            throw e;
        }
    }

    public static Todo parseTodo(String arguments) throws IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_TODO);
        } else {
            return new Todo(arguments);
        }
    }

    public static Deadline parseDeadline(String arguments) throws IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_DEADLINE);
        } else {
            String[] descriptions = arguments.split("/by");
            String description = descriptions[0].trim();
            String date = descriptions[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
            return new Deadline(description, formattedDate);
        }
    }

    public static Event parseEvent(String arguments) throws IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_EVENT);
        } else {
            String[] descriptions = arguments.split("/from");
            String description = descriptions[0].trim();
            String[] eventTime = descriptions[1].split("/to");
            String eventStart = eventTime[0].trim();
            String eventEnd = eventTime[1].trim();
            DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime formattedStart = LocalDateTime.parse(eventStart, formatterStart);
            DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern("HHmm");
            LocalTime formattedEnd = LocalTime.parse(eventEnd, formatterEnd);
            return new Event(description, formattedStart, formattedEnd);
        }
    }

    public static int parseTaskIndex(String arguments) {
        return Integer.parseInt(arguments);
    }
}

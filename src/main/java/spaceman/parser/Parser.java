package spaceman.parser;

import static spaceman.ui.Messages.MESSAGE_EMPTY_TODO;
import static spaceman.ui.Messages.MESSAGE_EMPTY_DEADLINE;
import static spaceman.ui.Messages.MESSAGE_EMPTY_EVENT;
import static spaceman.ui.Messages.MESSAGE_INVALID_DATE;
import static spaceman.ui.Messages.MESSAGE_INVALID_INDEX;

import static spaceman.commands.ListOfCommands.COMMAND_BYE;
import static spaceman.commands.ListOfCommands.COMMAND_LIST;
import static spaceman.commands.ListOfCommands.COMMAND_MARK;
import static spaceman.commands.ListOfCommands.COMMAND_UNMARK;
import static spaceman.commands.ListOfCommands.COMMAND_TODO;
import static spaceman.commands.ListOfCommands.COMMAND_DEADLINE;
import static spaceman.commands.ListOfCommands.COMMAND_EVENT;
import static spaceman.commands.ListOfCommands.COMMAND_DELETE;
import static spaceman.commands.ListOfCommands.COMMAND_FIND;

import spaceman.commands.Command;
import spaceman.commands.AddCommand;
import spaceman.commands.DeleteCommand;
import spaceman.commands.ExitCommand;
import spaceman.commands.FindCommand;
import spaceman.commands.InvalidCommand;
import spaceman.commands.ListCommand;
import spaceman.commands.MarkCommand;
import spaceman.commands.UnmarkCommand;
import spaceman.data.TaskList;
import spaceman.data.exception.IncompleteDescriptionException;
import spaceman.data.exception.InvalidActionException;
import spaceman.data.exception.InvalidDateFormatException;
import spaceman.data.task.Deadline;
import spaceman.data.task.Event;
import spaceman.data.task.Task;
import spaceman.data.task.Todo;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input.
 */
public class Parser {
    private static final String DATE_TIME_PATTERN1 = "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4}"; // dd/mm/yyyy 1500
    private static final String DATE_TIME_PATTERN2 = "\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{4}"; // yyyy/mm/dd 1500
    private static final String DATE_TIME_PATTERN3 = "\\d{1,2}-\\d{1,2}-\\d{4}\\s+\\d{4}"; // dd-mm-yyyy 1500
    private static final String DATE_TIME_PATTERN4 = "\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{4}"; // yyyy-mm-dd 1500

    /**
     * Returns the {@link Command} inputted by the user.
     * @param text input text from user
     * @param tasks list of tasks
     * @return a {@link Command} object to be executed
     * @throws InvalidActionException If user input is not a valid action.
     * @throws IncompleteDescriptionException If user input is incomplete.
     */
    public static Command parseCommand(String text, TaskList tasks) throws InvalidActionException,
            IncompleteDescriptionException, InvalidDateFormatException, ParseException {
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
            case COMMAND_BYE:
                return new ExitCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_MARK:
                return new MarkCommand(parseTaskIndex(arguments));
            case COMMAND_UNMARK:
                return new UnmarkCommand(parseTaskIndex(arguments));
            case COMMAND_TODO:
                return new AddCommand(parseTodo(arguments));
            case COMMAND_DEADLINE:
                return new AddCommand(parseDeadline(arguments));
            case COMMAND_EVENT:
                return new AddCommand(parseEvent(arguments));
            case COMMAND_DELETE:
                return new DeleteCommand(parseTaskIndex(arguments));
            case COMMAND_FIND:
                return new FindCommand(arguments);
            default:
                return new InvalidCommand();
            }
        } catch (IncompleteDescriptionException | InvalidDateFormatException | ParseException e) {
            throw e;
        }
    }

    private static String dateTimePatternValidation(String date) throws InvalidDateFormatException {
        if (date.matches(DATE_TIME_PATTERN1)) {
            return "d/M/yyyy HHmm";
        } else if (date.matches(DATE_TIME_PATTERN2)) {
            return "yyyy/M/d HHmm";
        } else if (date.matches(DATE_TIME_PATTERN3)) {
            return "d-M-yyyy HHmm";
        } else if (date.matches(DATE_TIME_PATTERN4)) {
            return "yyyy-M-d HHmm";
        } else {
            throw new InvalidDateFormatException(MESSAGE_INVALID_DATE);
        }
    }

    private static LocalDateTime parseDate(String date, String format, DateTimeFormatter formatter)
            throws InvalidDateFormatException {
        String separator;
        if (format.contains("-")) {
            separator = "-";
        } else {
            separator = "/";
        }
        String[] dateMonthYear = date.split(separator);
        if (Integer.parseInt(dateMonthYear[1]) > 12) {
            throw new InvalidDateFormatException(MESSAGE_INVALID_DATE);
        }
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Parsers arguments in the context of adding a todo.
     * @param arguments full command argument string
     * @return a {@link Todo} object
     * @throws IncompleteDescriptionException If the description of the todo is empty.
     */
    public static Todo parseTodo(String arguments) throws IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_TODO);
        } else {
            return new Todo(arguments);
        }
    }

    /**
     * Parsers arguments in the context of adding a deadline.
     * @param arguments full command argument string
     * @return a {@link Deadline} object
     * @throws IncompleteDescriptionException If the description of the deadline is empty.
     */
    public static Deadline parseDeadline(String arguments) throws IncompleteDescriptionException,
            InvalidDateFormatException, ParseException {
        if (arguments == null) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_DEADLINE);
        } else {
            String[] descriptions = arguments.split("/by");
            String description = descriptions[0].trim();
            String date = descriptions[1].trim();
            String format = dateTimePatternValidation(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime formattedDate = parseDate(date, format, formatter);
            return new Deadline(description, formattedDate);
        }
    }

    /**
     * Parsers arguments in the context of adding an event.
     * @param arguments full command argument string
     * @return an {@link Event} object
     * @throws IncompleteDescriptionException If the description of the event is empty.
     */
    public static Event parseEvent(String arguments) throws IncompleteDescriptionException,
            InvalidDateFormatException {
        if (arguments == null) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_EVENT);
        } else {
            String[] descriptions = arguments.split("/from");
            String description = descriptions[0].trim();
            String[] eventTime = descriptions[1].split("/to");
            String eventStart = eventTime[0].trim();
            String eventEnd = eventTime[1].trim();
            String format = dateTimePatternValidation(eventStart);
            DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern(format);
            LocalDateTime formattedStart = parseDate(eventStart, format, formatterStart);
            DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern("HHmm");
            LocalTime formattedEnd = LocalTime.parse(eventEnd, formatterEnd);
            return new Event(description, formattedStart, formattedEnd);
        }
    }

    /**
     * Parse the task index specified by the user
     * @param arguments full command argument string
     * @return index of task
     */
    public static int parseTaskIndex(String arguments) throws IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > Task.getTaskCount()) {
            throw new IndexOutOfBoundsException(MESSAGE_INVALID_INDEX);
        }
        return taskIndex;
    }
}

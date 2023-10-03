package MySun.parser;

import MySun.data.TaskList;
import MySun.data.exception.IncompleteDescriptionException;
import MySun.data.exception.InvalidActionException;
import MySun.data.exception.InvalidDateFormatException;
import MySun.data.task.Deadline;
import MySun.data.task.Event;
import MySun.data.task.Todo;
import MySun.commands.Command;
import MySun.commands.AddCommand;
import MySun.commands.DeleteCommand;
import MySun.commands.ExitCommand;
import MySun.commands.FindCommand;
import MySun.commands.InvalidCommand;
import MySun.commands.ListCommand;
import MySun.commands.MarkCommand;
import MySun.commands.UnmarkCommand;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

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
                    return new FindCommand(parseFind(arguments));
                default:
                    return new InvalidCommand();
            }
        } catch (IncompleteDescriptionException | InvalidDateFormatException | ParseException e) {
            throw e;
        }
    }

    private static String dateTimePatternValidation(String date) throws InvalidDateFormatException {
        if (date.matches("\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4}")) {
            return "d/M/yyyy HHmm";
        } else if (date.matches("\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{4}")) {
            return "yyyy/M/d HHmm";
        } else if (date.matches("\\d{1,2}-\\d{1,2}-\\d{4}\\s+\\d{4}")) {
            return "d-M-yyyy HHmm";
        } else if (date.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{4}")) {
            return "yyyy-M-d HHmm";
        } else {
            throw new InvalidDateFormatException("OOPS!!! The format of the date is invalid.");
        }
    }

    private static LocalDateTime parseDate(String date, DateTimeFormatter formatter) {
        return LocalDateTime.parse(date, formatter);
    }

    public static Todo parseTodo(String arguments) throws IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new Todo(arguments);
        }
    }

    public static Deadline parseDeadline(String arguments) throws IncompleteDescriptionException,
            InvalidDateFormatException, ParseException {
        if (arguments == null) {
            throw new IncompleteDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String[] descriptions = arguments.split("/by");
            if (descriptions.length < 2) {
                throw new IncompleteDescriptionException("OOPS!!! Please use /by to indicate time in a deadline.");
            }
            String description = descriptions[0].trim();
            String date = descriptions[1].trim();
            String format = dateTimePatternValidation(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime formattedDate = parseDate(date, formatter);
            return new Deadline(description, formattedDate);
        }
    }

    public static Event parseEvent(String arguments) throws IncompleteDescriptionException,
            InvalidDateFormatException {
        if (arguments == null) {
            throw new IncompleteDescriptionException("OOPS!!! The description of an event cannot be empty.");
        } else {
            String[] descriptions = arguments.split("/from");
            String description = descriptions[0].trim();
            if (descriptions.length < 2) {
                throw new IncompleteDescriptionException("OOPS!!! Please use /from to indicate time in an event.");
            }
            String[] eventTime = descriptions[1].split("/to");
            if (eventTime.length < 2) {
                throw new IncompleteDescriptionException("OOPS!!! Please use /to to indicate time in an event.");
            }
            String eventStart = eventTime[0].trim();
            String eventEnd = eventTime[1].trim();
            String startFormat = dateTimePatternValidation(eventStart);
            String endFormat = dateTimePatternValidation(eventEnd);
            DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern(startFormat);
            DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern(endFormat);
            LocalDateTime formattedStart = parseDate(eventStart, formatterStart);
            LocalDateTime formattedEnd = parseDate(eventEnd, formatterEnd);
            return new Event(description, formattedStart, formattedEnd);
        }
    }

    public static int parseTaskIndex(String arguments) throws IndexOutOfBoundsException,
            IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException("OOPS!! The command is Incomplete.");
        }
        int taskIndex = Integer.parseInt(arguments);
        if (taskIndex > TaskList.getSize() || taskIndex <= 0) {
            throw new IndexOutOfBoundsException("OOPS!! The task index is invalid.");
        }
        return taskIndex;
    }

    public static String parseFind(String arguments) throws IncompleteDescriptionException {
        if (arguments == null) {
            throw new IncompleteDescriptionException("OOPS!! The command is Incomplete.");
        } else {
            return arguments;
        }
    }
}

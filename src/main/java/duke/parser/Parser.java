package duke.parser;

import duke.command.*;
import duke.exception.DukeCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents parser to parse user's input.
 */
public class Parser {

    /**
     * Parse a string index into an integer index.
     *
     * @param input The string index.
     * @return The integer index.
     */
    public int parseIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    /**
     * Parse a user's input into components of a task.
     *
     * @param input The user's input.
     * @param regex An expression used for splitting.
     * @return The input after splitting.
     */
    public String[] parseTask(String input, String regex) {
        return input.split(regex);
    }

    /**
     * Split up the input to get command.
     *
     * @param input The user's input
     * @return The input after splitting.
     */
    public String[] parseCommandType(String input) {
        return input.split(" ", 2);
    }

    /**
     * Convert a string to DateTime with a specified format.
     *
     * @param input The string with a DateTime format.
     * @return A string converted into DateTime.
     */
    public LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Convert a DateTime variable back into a string with a specified format.
     *
     * @param dateTime DateTime variable
     * @return String that is converted from DateTime.
     */
    public String convertDateTimetoString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * Creates a command class based on the user's input.
     * 
     * @param input The user's input
     * @return A command class
     * @throws DukeCommandException If it is an invalid command.
     */
    public Command parseCommand(String input) throws DukeCommandException {
        String[] parsedInput = parseCommandType(input);
        String commandType = parsedInput[0];
        String data = parsedInput.length == 1 ? "" : parsedInput[1].trim();
        Command command;

        switch (commandType) {
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            command = new MarkCommand(data);
            break;
        case "unmark":
            command = new UnmarkCommand(data);
            break;
        case "delete":
            command = new DeleteCommand(data);
            break;
        case "find":
            command = new FindCommand(data);
            break;
        case "todo":
            command = new AddTodoCommand(data);
            break;
        case "deadline":
            command = new AddDeadlineCommand(data);
            break;
        case "event":
            command = new AddEventCommand(data);
            break;
        case "bye":
            command = new ByeCommand();
            break;
        default:
            throw new DukeCommandException();
        }

        return command;
    }
}

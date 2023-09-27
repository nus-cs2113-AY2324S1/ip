package elgin.parser;

import elgin.command.*;
import elgin.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;


public class Parser {

    private static final int INVALID_INDEX = -1;
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parse user input and split into command and arguments.
     * Returns the Command type based on command and arguments.
     *
     * @param command The command input by user
     * @return Command to be executed.
     * @throws DukeException If invalid command/arguments is supplied.
     */
    public Command parseCommand(String command) throws DukeException {
        String[] commandArray = command.split(" ", 2);
        String userCommand = commandArray[0].toLowerCase();
        String arguments = "";
        int index = INVALID_INDEX;
        if (commandArray.length > 1) {
            arguments = commandArray[1];
            index = parseTaskIndex(arguments, userCommand);
        }

        switch (userCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(index);
        case "unmark":
            return new UnmarkCommand(index);
        case "delete":
            return new DeleteCommand(index);
        case "find":
            return new FindCommand(arguments);
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            return new AddCommand(userCommand, arguments);
        default:
            throw new DukeException("Sorry I do not understand your command");
        }
    }

    /**
     * Parse arguments of Deadline and Event and
     * store them into hashmap.
     *
     * @param command Command type, either Deadline or Event.
     * @param arguments Arguments to be parsed.
     * @return Hashmap containing the parsed arguments.
     * @throws DukeException If invalid arguments is supplied for the respective command.
     */
    public static HashMap<String, String> parseArguments(String command, String arguments) throws DukeException {
        HashMap<String, String> parsedArgs = new HashMap<>();
        String[] splitArg;
        switch (command) {
        case "deadline":
            splitArg = arguments.split(" /by ");
            if (splitArg.length < 2) {
                throw new DukeException("Usage: deadline <task> /by <deadline>");
            }
            parsedArgs.put("description", splitArg[0]);
            parsedArgs.put("by", splitArg[1]);
            break;
        case "event":
            splitArg = arguments.split(" /from ");
            if (splitArg.length < 2) {
                throw new DukeException("Usage: event <task> /from <time> /to <time>");
            }
            parsedArgs.put("description", splitArg[0]);
            String[] fromTo = splitArg[1].split(" /to ");
            if (fromTo.length < 2) {
                throw new DukeException("Usage: event <task> /from <time> /to <time>");
            }
            parsedArgs.put("from", fromTo[0]);
            parsedArgs.put("to", fromTo[1]);
            break;
        default:
            throw new DukeException("Sorry I do not understand your command");
        }
        return parsedArgs;
    }

    /**
     * Parse the task Index from argument if command
     * is mark/unmark/delete, else return INVALID_INDEX.
     *
     * @param arguments Argument containing the index.
     * @param command Command type.
     * @return Index of the task to be executed on.
     * @throws DukeException If index argument does not contain number only.
     */
    public int parseTaskIndex(String arguments, String command) throws DukeException {

        if (!(command.equals("mark") || command.equals("unmark") || command.equals("delete"))) {
            return INVALID_INDEX;
        }

        if (!isArguments(arguments)) {
            return INVALID_INDEX;
        }

        int idx = INVALID_INDEX;
        try {
            idx = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        }
        return idx;
    }

    /**
     * Checks if arguments string is empty.
     *
     * @param arguments Arguments string
     * @return True if arguments is not empty, else False.
     * @throws DukeException If arguments is empty.
     */
    public static boolean isArguments(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description/Task number cannot be empty.");
        }
        return true;
    }

    /**
     * Returns LocalDateTime from string format of date time.
     *
     * @param input String representation of date time.
     * @return LocalDateTime of date time.
     * @throws DukeException If the date time does not specific format (dd/MM/yyyy HHmm).
     */
    public static LocalDateTime parseDateTime(String input) throws DukeException {
        LocalDateTime parsedDateTime;
        try {
            parsedDateTime = LocalDateTime.parse(input, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Error parsing date time.");
        }
        return parsedDateTime;
    }

    /**
     * Compares two if LocalDateTime and throws exception if
     * end datetime is before start datetime.
     *
     * @param from Starting datetime.
     * @param to Ending datetime.
     * @throws DukeException If /to datetime is before /from datetime.
     */
    public static void isValidFromToDateTime(LocalDateTime from, LocalDateTime to) throws DukeException {
        if (to.isBefore(from)) {
            throw new DukeException("/to datetime cannot be before /from datetime");
        }
    }
}

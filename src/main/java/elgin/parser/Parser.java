package elgin.parser;

import elgin.command.*;
import elgin.exception.DukeException;

import java.util.HashMap;


public class Parser {

    private static final int INVALID_INDEX = -1;

    public Command parseCommand(String command) throws DukeException {
        String[] commandArray = command.split(" ", 2);
        commandArray[0] = commandArray[0].toLowerCase();
        String userCommand = commandArray[0];
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

    public static boolean isArguments(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("OOPS! Description/Task number cannot be empty.");
        }
        return true;
    }

}

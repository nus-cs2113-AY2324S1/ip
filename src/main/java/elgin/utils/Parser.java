package elgin.utils;

import elgin.exception.DukeException;

import java.util.HashMap;


public class Parser {

    private static final int INVALID_INDEX = -1;

    public static String[] parseCommand(String command) {
        String[] commandArray = command.split(" ", 2);
        commandArray[0] = commandArray[0].toLowerCase();
        return commandArray;
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
            parsedArgs.put("from", splitArg[0]);
            parsedArgs.put("to", splitArg[1]);
            break;
        }
        return parsedArgs;
    }

    public static int parseTaskIndex(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("Please enter a task number.");
        }
        int idx = INVALID_INDEX;
        try {
            idx = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a task number.");
        }
        return idx;
    }

}

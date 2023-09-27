package elgin.parser;

import elgin.exception.DukeException;

import java.util.HashMap;

import static elgin.ui.Ui.*;


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
                throw new DukeException(getDeadlineUsageMsg());
            }
            parsedArgs.put("description", splitArg[0]);
            parsedArgs.put("by", splitArg[1]);
            break;
        case "event":
            splitArg = arguments.split(" /from ");
            if (splitArg.length < 2) {
                throw new DukeException(getEventUsageMsg());
            }
            parsedArgs.put("description", splitArg[0]);
            String[] fromTo = splitArg[1].split(" /to ");
            if (fromTo.length < 2) {
                throw new DukeException(getEventUsageMsg());
            }
            parsedArgs.put("from", fromTo[0]);
            parsedArgs.put("to", fromTo[1]);
            break;
        default:
            throw new DukeException(getUnknownCmdErrorMsg());
        }
        return parsedArgs;
    }

    public static int parseTaskIndex(String arguments) throws DukeException {
        if (!isArguments(arguments, getEmptyDescErrorMsg(getEmptyTaskIdxErrorMsg()))) {
            return INVALID_INDEX;
        }

        int idx = INVALID_INDEX;
        try {
            idx = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(getEmptyTaskIdxErrorMsg());
        }
        return idx;
    }

    public static boolean isArguments(String arguments, String message) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException(message);
        }
        return true;
    }

}

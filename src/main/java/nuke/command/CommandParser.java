package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.command.exception.InvalidCommandTypeException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public static Command parseCommand(String commandLine)
            throws InvalidCommandTypeException, InvalidCommandArgumentException {
        // Get command type and arguments.
        String type = getCommandType(commandLine);
        String args = getCommandArguments(commandLine, type);

        // Construct command using command type.
        Command command = getBlankCommand(type);
        // Apply arguments on command.
        command.applyArguments(args);

        return command;
    }

    private static Command getBlankCommand(String type)
            throws InvalidCommandTypeException {
        Command command;
        switch (type) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            command = new MarkCommand();
            break;
        case "unmark":
            command = new UnmarkCommand();
            break;
        case "todo":
            command = new TodoCommand();
            break;
        case "deadline":
            command = new DeadlineCommand();
            break;
        case "event":
            command = new EventCommand();
            break;
        case "delete":
            command = new DeleteCommand();
            break;
        default:
            throw new InvalidCommandTypeException(type);
        }
        return command;
    }

    private static String getCommandType(String commandLine) {
        return commandLine.split("\\s")[0];
    }

    private static String getCommandArguments(String commandLine, String type) {
        return commandLine.substring(type.length()).strip();
    }

    public static boolean isNotOneWord(String str) {
        if (str.isEmpty()) {
            return true;
        } else return str.split("\\s").length != 1;
    }

    public static boolean isNotContainingExactOneLabel(String str, String label) {
        String[] argSplit = str.split("\\s");
        long labelCnt = Arrays.stream(argSplit).filter(a -> a.equals(label)).count();
        return labelCnt != 1;
    }

    public static boolean matches(String str, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(str);
        return matcher.matches();
    }

    public static String[] parseArguments(String args, String regex) {
        String[] parsedArgs;

        Matcher matcher = Pattern.compile(regex).matcher(args);
        matcher.find();
        parsedArgs = new String[matcher.groupCount()];
        for (int i = 1; i <= matcher.groupCount(); i++) {
            parsedArgs[i - 1] = matcher.group(i);
        }

        return parsedArgs;
    }
}

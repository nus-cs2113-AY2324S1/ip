package nuke.command;

import nuke.Ui;
import nuke.command.*;
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
        Command command;

        // Construct command using command type.
        try {
            command = getBlankCommand(type);
        } catch (InvalidCommandTypeException e) {
            handleTypeError(e);
            throw e;
        }
        // Apply arguments on command.
        try {
            command.applyArguments(args);
        } catch (InvalidCommandArgumentException e) {
            command.handleArgumentError(e);
            throw e;
        }

        return command;
    }

    private static Command getBlankCommand(String type)
            throws InvalidCommandTypeException {
        Command command;
        switch (type) {
        case "bye":
            command = new CommandBye();
            break;
        case "list":
            command = new CommandList();
            break;
        case "mark":
            command = new CommandMark();
            break;
        case "unmark":
            command = new CommandUnmark();
            break;
        case "todo":
            command = new CommandTodo();
            break;
        case "deadline":
            command = new CommandDeadline();
            break;
        case "event":
            command = new CommandEvent();
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

    public static void handleTypeError(InvalidCommandTypeException e) {
        String desc = String.format("There is no command called '%s'.", e.type);
        String detail = "Existing command: bye, list, mark, unmark, todo, deadline, event";
        Ui.printCommandError(desc, detail);
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

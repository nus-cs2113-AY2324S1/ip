package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.command.exception.InvalidCommandTypeException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents parser of commands.
 */
public class CommandParser {

    /**
     * Parses a line of command into {@link Command} and returns it.
     * @param commandLine string of command
     * @return command; result of parsing
     * @throws InvalidCommandTypeException if type of command is invalid
     * @throws InvalidCommandArgumentException if one or more arguments of command are invalid
     */
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
        case ByeCommand.TYPE:
            command = new ByeCommand();
            break;
        case ListCommand.TYPE:
            command = new ListCommand();
            break;
        case MarkCommand.TYPE:
            command = new MarkCommand();
            break;
        case UnmarkCommand.TYPE:
            command = new UnmarkCommand();
            break;
        case TodoCommand.TYPE:
            command = new TodoCommand();
            break;
        case DeadlineCommand.TYPE:
            command = new DeadlineCommand();
            break;
        case EventCommand.TYPE:
            command = new EventCommand();
            break;
        case DeleteCommand.TYPE:
            command = new DeleteCommand();
            break;
        case FindCommand.TYPE:
            command = new FindCommand();
            break;
        default:
            throw new InvalidCommandTypeException(type);
        }
        return command;
    }

    private static String getCommandType(String commandLine) {
        return commandLine.split(REGEX_WHITESPACE)[0];
    }

    private static String getCommandArguments(String commandLine, String type) {
        return commandLine.substring(type.length()).strip();
    }

    /**
     * Returns if the string is one word.
     * @param str string
     * @return if the string is not one word.
     */
    public static boolean isNotOneWord(String str) {
        if (str.isEmpty()) {
            return true;
        } else return str.split(REGEX_WHITESPACE).length != 1;
    }

    /**
     * Returns if the string is not containing exact one label
     * @param str string
     * @param label label
     * @return if the string is not containing exact one label
     */
    public static boolean isNotContainingExactOneLabel(String str, String label) {
        String[] argSplit = str.split(REGEX_WHITESPACE);
        long labelCnt = Arrays.stream(argSplit).filter(a -> a.equals(label)).count();
        return labelCnt != 1;
    }

    /**
     * Returns if the string matches the regular expression.
     * @param str string
     * @param regex regular expression
     * @return if the string matches the regular expression
     */
    public static boolean matches(String str, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(str);
        return matcher.matches();
    }

    /**
     * Parses the arguments using the regular expression and returns them.
     * Arguments in the regular expression has to in the group.
     *
     * @param args arguments
     * @param regex regular expression
     * @return parsed arguments
     */
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

    private static final String REGEX_WHITESPACE = "\\s";
}

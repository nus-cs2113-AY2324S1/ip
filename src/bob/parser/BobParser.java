package bob.parser;

import bob.Bob;
import bob.BobException;
import bob.commands.*;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses Duke commands.
 */
public class BobParser {

    /**
     * Pattern used to parse Duke commands.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*?)");

    /**
     * Parses Duke command from {@param userInput}. Returns an Array of length 2 in the form
     * [commandWord, arguments].
     *
     * @param userInput command sent by user.
     * @return Array of length two containing [commandWord, arguments].
     */
    public Command parseCommand(String userInput) throws BobException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new BobException("Invalid command!");
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments").trim();

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(arguments);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommandParser().parse(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(arguments);
        case EventCommand.COMMAND_WORD:
            return new EventCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            throw new BobException("I don't know that command");
        }
    }

}

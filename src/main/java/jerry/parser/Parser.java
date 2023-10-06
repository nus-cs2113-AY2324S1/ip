package jerry.parser;

import jerry.commands.*;
import jerry.exceptions.IllegalValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import static jerry.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jerry.common.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

/**
 * Parses user input.
 */
public class Parser {


    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

        case AddCommand.TODO_COMMAND_WORD:
            return prepareAddTodoCommand(arguments);

        case AddCommand.DEADLINE_COMMAND_WORD:
            return prepareAddDeadlineCommand(arguments);

        case AddCommand.EVENT_COMMAND_WORD:
            return prepareAddEventCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMarkCommand(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmarkCommand(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add todo command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddTodoCommand(String args) {
        Pattern pattern = Pattern.compile("(.+)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches() && matcher.groupCount() == 1) {
            String description = matcher.group(1);
            return new AddCommand(description);
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add deadline command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddDeadlineCommand(String args) {
        Pattern pattern = Pattern.compile("(.+) /by (.+)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches() && matcher.groupCount() == 2) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            return new AddCommand(description, by);
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add event command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddEventCommand(String args) {
        Pattern pattern = Pattern.compile("(.+) /from (.+) /to (.+)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches() && matcher.groupCount() == 3) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new AddCommand(description, from, to);
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDeleteCommand(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the mark task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareMarkCommand(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the mark task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareUnmarkCommand(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new UnmarkCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    private Command prepareFindCommand(String args) {
        final String[] keywords = args.split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }


    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        if (args.isEmpty()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(args.split(" ")[0]);
    }


    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

}

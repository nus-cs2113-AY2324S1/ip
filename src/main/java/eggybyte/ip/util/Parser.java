package eggybyte.ip.util;

import eggybyte.ip.command.*;

/**
 * Parses user input.
 */
public class Parser {
    public static Boolean debugMode = false;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws Exception
     */
    public Command parseCommand(String userInput) throws Exception {
        String[] words = userInput.trim().split(" ", 2); // split the input into command and arguments
        if (words.length == 0) {
            throw new Exception("Empty input is invalid!");
        }

        final String commandWord = words[0];
        final String argumentString = userInput.replaceFirst(commandWord, "").trim();
        final String[] arguments = getArguments(argumentString);
        if (debugMode) {
            Logger.printArray(arguments);
        }

        try {
            switch (commandWord) {
                // case TodoCommand.COMMAND_WORD:
                // return new TodoCommand(arguments);
                // case DeadlineCommand.COMMAND_WORD:
                // return new DeadlineCommand(arguments);
                // case EventCommand.COMMAND_WORD:
                // return new EventCommand(arguments);
                // case DeleteCommand.COMMAND_WORD:
                // return new DeleteCommand(arguments);
                // case ListCommand.COMMAND_WORD:
                // return new ListCommand(arguments);
                case ByeCommand.COMMAND_WORD:
                    return new ByeCommand(arguments);
                default:
                    return new RepeatCommand(commandWord, arguments);
                // throw new Exception("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception exception) {
            throw exception;
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private String[] getArguments(String argumentString) {
        if (argumentString.length() == 0) {
            return new String[0];
        }
        String[] parts = argumentString.split("/");
        parts[0] = parts[0].trim();
        for (int i = 1; i < parts.length; i++) {
            parts[i] = parts[i].replaceFirst(parts[i].split(" ")[0], "").trim();
        }
        return parts;
    }

    // /**
    // * Parses arguments in the context of the delete person command.
    // *
    // * @param args full command args string
    // * @return the prepared command
    // */
    // private Command prepareDeleteCommand(String args) {
    // try {
    // final int targetIndex = parseArgsAsDisplayedIndex(args);
    // return new DeleteCommand(targetIndex);
    // } catch (ParseException pe) {
    // return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
    // DeleteCommand.MESSAGE_USAGE));
    // } catch (NumberFormatException nfe) {
    // return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    // }
    // }

    // /**
    // * Parses the given arguments string as a single index number.
    // *
    // * @param args arguments string to parse as index number
    // * @return the parsed index number
    // * @throws ParseException if no region of the args string could be found
    // * for the index
    // * @throws NumberFormatException the args string region is not a valid number
    // */
    // private int parseArgsAsDisplayedIndex(String args) throws ParseException,
    // NumberFormatException {
    // if (args.isEmpty()) {
    // throw new ParseException("Could not find index number to parse");
    // }
    // return Integer.parseInt(args.split(" ")[0]);
    // }

    // /**
    // * Signals that the user input could not be parsed.
    // */
    // public static class ParseException extends Exception {
    // ParseException(String message) {
    // super(message);
    // }
    // }

}

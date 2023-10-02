package kenergeticbot;

import kenergeticbot.command.*;
import kenergeticbot.exceptionhandler.KenergeticBotException;
import java.text.ParseException;

import static kenergeticbot.exceptionhandler.KenergeticBotException.*;

public class Parser{

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws KenergeticBotException if input does not match any known command
     */
    public static Command parseCommand(TaskList taskList, String userInput) {
        String command[] = userInput.split(" ");
        switch (command[0].toLowerCase()) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMark(taskList,userInput);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(taskList,userInput);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(taskList,userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFind(taskList, userInput);
        default:
            try{
                return new AddCommand(userInput);
            } catch (KenergeticBotException e) {
                return new IncorrectCommand(e.getMessage());
            }
        }
    }

    /**
     * Parses arguments in the context of the mark task command.
     *
     * @param userInput full command args string
     * @return the prepared command
     */
    private static Command prepareMark(TaskList taskList, String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(taskList ,userInput, "mark");
            return new MarkCommand(listIndex);
        } catch (KenergeticBotException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + MarkCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the unmark task command.
     *
     * @param userInput full command args string
     * @return the prepared command
     */
    private static Command prepareUnmark(TaskList taskList, String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(taskList, userInput, "unmark");
            return new UnmarkCommand(listIndex);
        } catch (KenergeticBotException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + UnmarkCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param userInput full command args string
     * @return the prepared command
     */
    private static Command prepareDelete(TaskList taskList, String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(taskList, userInput, "delete");
            return new DeleteCommand(listIndex);
        } catch (KenergeticBotException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + DeleteCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the mark task command.
     *
     * @param userInput full command args string
     * @return the prepared command
     */
    private static Command prepareFind(TaskList taskList, String userInput) {
        try {
            final String listKeyword = parseArgsAsDisplayedKeyword(taskList, userInput, "find");
            return new FindCommand(listKeyword);
        } catch (KenergeticBotException e) {
            return new IncorrectCommand(e.getMessage());
        } catch (ParseException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT + MarkCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses the given arguments string to identify task index number.
     *
     * @param userInput arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private static int parseArgsAsDisplayedIndex(TaskList taskList, String userInput, String command) throws ParseException, NumberFormatException, KenergeticBotException {
        String formattedString = userInput.replace(command, "").trim();
        int listIndex = Integer.parseInt(formattedString);
        if (formattedString.isEmpty()) {
            throw new KenergeticBotException(COMMAND_TYPO_NO_NUMBER);
        } else if (listIndex > taskList.getSize()) {
            throw new KenergeticBotException(OUT_OF_RANGE);
        }
        return listIndex;
    }

    /**
     * Parses the given arguments string to identify task keyword.
     *
     * @param userInput arguments string to parse as keyword
     * @return the parsed keyword
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private static String parseArgsAsDisplayedKeyword(TaskList taskList, String userInput, String command) throws ParseException, NumberFormatException, KenergeticBotException {
        String keywordString = userInput.replace(command, "").trim();
        if (keywordString.isEmpty()) {
            throw new KenergeticBotException(COMMAND_TYPO_NO_KEYWORD);
        }
        return keywordString;
    }
}

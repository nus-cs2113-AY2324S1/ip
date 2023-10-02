package duke.parser;

import duke.commands.Command;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.FindCommand;

import duke.exception.DukeException;

import static duke.ui.MessageConstants.MESSAGE_ERROR_INVALID_TASK_NUMBER;
import static duke.ui.MessageConstants.MESSAGE_ERROR_EMPTY_DESCRIPTION;
import static duke.ui.MessageConstants.MESSAGE_ERROR_FROM_TIME_MISSING;
import static duke.ui.MessageConstants.MESSAGE_ERROR_TO_TIME_MISSING;
import static duke.ui.MessageConstants.MESSAGE_ERROR_DEADLINE_BY;
import static duke.ui.MessageConstants.MESSAGE_ERROR_COMMAND;
import static duke.ui.MessageConstants.MESSAGE_ERROR_FIND_MISSING;

import static duke.parser.TaskConstants.DEADLINE;
import static duke.parser.TaskConstants.EVENT;
import static duke.parser.TaskConstants.TODO;
import static duke.parser.TaskConstants.BYE;
import static duke.parser.TaskConstants.LIST;
import static duke.parser.TaskConstants.MARK;
import static duke.parser.TaskConstants.UNMARK;
import static duke.parser.TaskConstants.DELETE;
import static duke.parser.TaskConstants.FIND;

import static duke.parser.TaskConstants.FROM_DELIMITER;
import static duke.parser.TaskConstants.TO_DELIMITER;
import static duke.parser.TaskConstants.BY_DELIMITER;

/**
 * Represents a parser for Duke commands.
 */
public class Parser {

    /**
     * Parses the given input and returns the corresponding Command object.
     *
     * @param input The input string to parse.
     * @return The corresponding Command object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static Command parse(String input) throws DukeException {
        String[] args = input.trim().split(" ", 2);
        String command = args[0];
        String commandInput = args.length > 1 ? args[1] : "";
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return parseTodoCommand(commandInput);
        case DEADLINE:
            return parseDeadlineCommand(commandInput);
        case EVENT:
            return parseEventCommand(commandInput);
        case MARK:
            return parseMarkCommand(commandInput);
        case UNMARK:
            return parseUnmarkCommand(commandInput);
        case DELETE:
            return parseDeleteCommand(commandInput);
        case FIND:
            return parseFindCommand(commandInput);
        default:
            throw new DukeException(MESSAGE_ERROR_COMMAND);
        }
    }

    /**
     * Parses the given input and returns the corresponding AddTodoCommand object.
     *
     * @param commandRawInput The raw input string to parse.
     * @return The corresponding AddTodoCommand object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static AddTodoCommand parseTodoCommand(String commandRawInput) throws DukeException {
        String description = commandRawInput.trim();
        if (description.isEmpty()) {
            throw new DukeException(MESSAGE_ERROR_EMPTY_DESCRIPTION);
        }
        return new AddTodoCommand(description);
    }

    /**
     * Parses the given input and returns the corresponding AddDeadlineCommand object.
     *
     * @param commandRawInput The raw input string to parse.
     * @return The corresponding AddDeadlineCommand object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static AddDeadlineCommand parseDeadlineCommand(String commandRawInput) throws DukeException {
        int byPos = commandRawInput.indexOf(BY_DELIMITER);
        if (byPos == -1) {
            throw new DukeException(MESSAGE_ERROR_DEADLINE_BY);
        }

        String description = commandRawInput.substring(0, byPos).trim();
        String by = commandRawInput.substring(byPos + 3).trim();

        if (description.isEmpty()) {
            throw new DukeException(MESSAGE_ERROR_EMPTY_DESCRIPTION);
        }

        if (by.isEmpty()) {
            throw new DukeException(MESSAGE_ERROR_DEADLINE_BY);
        }

        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the given input and returns the corresponding AddEventCommand object.
     *
     * @param commandRawInput The raw input string to parse.
     * @return The corresponding AddEventCommand object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static AddEventCommand parseEventCommand(String commandRawInput) throws DukeException {
        int fromPos = commandRawInput.indexOf(FROM_DELIMITER);
        int toPos = commandRawInput.indexOf(TO_DELIMITER);

        if (fromPos == -1) {
            throw new DukeException(MESSAGE_ERROR_FROM_TIME_MISSING);
        }

        if (toPos == -1) {
            throw new DukeException(MESSAGE_ERROR_TO_TIME_MISSING);
        }

        //get the task name
        String description = commandRawInput.substring(0,fromPos).trim();

        //get the event time 
        String fromTime = commandRawInput.substring(fromPos+5,toPos).trim();
        String toTime = commandRawInput.substring(toPos+3).trim();

        //if the taskname is empty throw an exception
        if(description.isEmpty()){
            throw new DukeException(MESSAGE_ERROR_EMPTY_DESCRIPTION);
        }

        //if the fromTime is empty throw an exception
        if(fromTime.isEmpty()){
            throw new DukeException(MESSAGE_ERROR_FROM_TIME_MISSING);
        }

        //if the toTime is empty throw an exception
        if(toTime.isEmpty()){
            throw new DukeException(MESSAGE_ERROR_TO_TIME_MISSING);
        }

        return new AddEventCommand(description,fromTime,toTime);
    }

    /**
     * Parses the given input and returns the corresponding MarkCommand object.
     *
     * @param commandRawInput The raw input string to parse.
     * @return The corresponding MarkCommand object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static MarkCommand parseMarkCommand(String commandRawInput) throws DukeException {
        try {
            int index = Integer.parseInt(commandRawInput.trim()) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Parses the given input and returns the corresponding UnmarkCommand object.
     *
     * @param commandRawInput The raw input string to parse.
     * @return The corresponding UnmarkCommand object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static UnmarkCommand parseUnmarkCommand(String commandRawInput) throws DukeException {
        try {
            int index = Integer.parseInt(commandRawInput.trim()) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
        
    }

    /**
     * Parses the given input and returns the corresponding DeleteCommand object.
     *
     * @param commandRawInput The raw input string to parse.
     * @return The corresponding DeleteCommand object.
     * @throws DukeException If there is an error parsing the input.
     */
    public static DeleteCommand parseDeleteCommand(String commandRawInput) throws DukeException {
        try {
            int index = Integer.parseInt(commandRawInput.trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Parses the user input for the "find" command into a FindCommand object.
     *
     * @param commandRawInput The user input for the "find" command.
     * @return The FindCommand object corresponding to the user input.
     * @throws DukeException If there is an error parsing the user input.
     */
    public static FindCommand parseFindCommand(String commandRawInput) throws DukeException {
        String searchString = commandRawInput.trim();
        if(searchString.isEmpty()){
            throw new DukeException(MESSAGE_ERROR_FIND_MISSING);
        }
        return new FindCommand(searchString);
    }
}
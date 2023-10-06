package lemon.validation;

import lemon.commands.*;
import lemon.exception.LemonException;
import lemon.task.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lemon.common.Messages.MESSAGE_EMPTY_LIST;

/**
 * Utility class for handling input validations in the Lemon chatbot.
 * This class checks the validity of input and retrieves parts of the input according to the needs of each function.
 * Returns exceptions when encoutered.
 */
public class Parser {
    /**
     * Identifies command type of user input.
     *
     * @param input Input string from user.
     * @return Type of command.
     */
    public String commandType(String input) {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.matches("todo\\b.*")) {
            return "todo";
        } else if (input.matches("deadline\\b.*")) {
            return "deadline";
        } else if (input.matches("event\\b.*")) {
            return "event";
        } else if (input.matches("delete\\b.*")) {
            return "delete";
        } else if (input.matches("mark\\b.*")) {
            return "mark";
        } else if (input.matches("unmark\\b.*")) {
            return "unmark";
        } else if (input.equals("help")) {
            return "help";
        } else {
            return "invalid";
        }
    }

    /**
     * Returns a newly created command according to the type of user input.
     *
     * @param input Input string from user.
     * @return New command object.
     * @throws LemonException If input is invalid or is of an invalid format.
     */
    public Command parseInput(String input) throws LemonException {
        try {
            String commandType = commandType(input.toLowerCase().trim());

            String[] taskStr = input.split("\\s+");
            String args = "";

            if (taskStr.length > 1) {
                args = taskStr[1].trim();
            }

            return createCommand(commandType, input, args);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new LemonException("Uh-oh! Invalid input format. Please try again!");
        }
    }

    /**
     * Creates command according to the type of user input.
     *
     * @param commandType Type of user input.
     * @param input Input string from user.
     * @param args Arguments passed into the input from user.
     * @return New command object.
     * @throws LemonException If exceptions occur while validating input.
     */
    public Command createCommand(String commandType, String input, String args) throws LemonException {
        switch (commandType) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case TodoCommand.COMMAND_WORD:
            return parseTodo(input);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(input);
        case EventCommand.COMMAND_WORD:
            return parseEvent(input);
        case DeleteCommand.COMMAND_WORD:
            int deleteIndex = checkTaskNumberFormat(args);
            return new DeleteCommand(deleteIndex);
        case MarkCommand.COMMAND_WORD:
            int markIndex = checkTaskNumberFormat(args);
            return new MarkCommand(markIndex);
        case UnmarkCommand.COMMAND_WORD:
            int unmarkIndex = checkTaskNumberFormat(args);
            return new UnmarkCommand(unmarkIndex);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Returns a task object according to the task data from storage file.
     * Retrieves information from input to create new task object.
     *
     * @param task Task data from storage file.
     * @return New task object.
     * @throws LemonException If data in the file is not of the right format.
     */
    public Task parseFile(String task) throws LemonException {
        try {
            String[] taskStr = task.split(" \\| ");

            if (taskStr.length < 3) {
                throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                        "'<T/D/E> | <0/1> | <task> [| <date/time>] [| <date/time>]'!\n");
            }

            String taskType = taskStr[0].trim();
            String taskStatus = taskStr[1].trim();
            boolean isDone = taskStr[1].trim().equals("1");
            String description = taskStr[2].trim();

            if (!taskStatus.equals("0") && !taskStatus.equals("1")) {
                throw new LemonException("Uh-oh! Data in the file is not of the right task type! " +
                        "Please use <0/1> for the task status!\n");
            }

            return createTask(taskStr, taskType, description, isDone);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                    "'<T/D/E> | <0/1> | <task> [| <date/time>] [| <date/time>]'!\n");
        }
    }

    /**
     * Creates new task based on the task data from storage file.
     * Retrieves information from input to create new task object.
     *
     * @param taskStr Array of sliced input strings from user.
     * @param taskType Type of task according to user input.
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     * @return New task object.
     * @throws LemonException If data in the file is not of the right format.
     */
    public Task createTask(String[] taskStr, String taskType, String description, boolean isDone) throws LemonException{
        try {
            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String by = taskStr[3].trim();
                return new Deadline(description, by, isDone);
            case "E":
                String from = taskStr[3].trim();
                String to = taskStr[4].trim();
                return new Event(description, from, to, isDone);
            default:
                throw new LemonException("Uh-oh! Data in the file is not of the right task type! " +
                        "Please use <T/D/E> for the type of task!\n");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LemonException("Uh-oh! Data in the file is not in the right format! Please use the format " +
                    "'<T/D/E> | <0/1> | <task> [| <date/time>] [| <date/time>]'!\n");
        }
    }

    /**
     * Matches input format with accepted input format for todo task.
     * Retrieves information from input to create new command object.
     *
     * @param input Input string from user.
     * @return New TodoCommand object.
     * @throws LemonException If user input is invalid or of invalid format.
     */
    private static Command parseTodo(String input) throws LemonException {
        String inputPattern = "todo (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String description;

        if (matchFound) {
            description = matcher.group(1).trim();

            if (description.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task!");
            }
        } else {
            throw new LemonException("Oopsie! Please use the format 'todo <task>'!");
        }
        return new TodoCommand(description);
    }

    /**
     * Matches input format with accepted input format for deadline task.
     * Retrieves information from input to create new command object.
     *
     * @param input Input string from user.
     * @return New DeadlineCommand object.
     * @throws LemonException If user input is invalid or of invalid format.
     */
    private static Command parseDeadline(String input) throws LemonException {
        String inputPattern = "deadline (.+?) /by (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String description, dateTime;

        if (matchFound) {
            description = matcher.group(1).trim();
            dateTime = matcher.group(2).trim();

            if (description.isEmpty() || dateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task and date/time!");
            }
        } else {
            throw new LemonException("Oopsie! Please use the format 'deadline <task> /by <date/time>'!");
        }
        return new DeadlineCommand(description, dateTime);
    }

    /**
     * Matches input format with accepted input format for event task.
     * Retrieves information from input to create new command object.
     *
     * @param input Input string from user.
     * @return New EventCommand object.
     * @throws LemonException If user input is invalid or of invalid format.
     */
    private static Command parseEvent(String input) throws LemonException {
        String inputPattern = "event (.+?) /from (.+?) /to (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();

        String description, startDateTime, endDateTime;

        if (matchFound) {
            description = matcher.group(1).trim();
            startDateTime = matcher.group(2).trim();
            endDateTime = matcher.group(3).trim();

            if (description.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty()) {
                throw new LemonException("Oopsie! Please state the task, starting date/time and ending date/time!");
            }
        } else {
            throw new LemonException("Oopsie! Please use the format 'event <task> " +
                    "/from <starting date/time> /to <ending date/time>'!");
        }
        return new EventCommand(description, startDateTime, endDateTime);
    }

    /**
     * Checks if the arguments passed is a non-integer input for task index.
     * Subtracts 1 from the taskk index to get a 0-based index.
     *
     * @param input Input string from user.
     * @return Task index.
     * @throws LemonException If the input from user is non-integer.
     */
    private static int checkTaskNumberFormat(String input) throws LemonException {
        int taskIndex;
        try{
            int INDEX_OFFSET = 1;
            taskIndex = Integer.parseInt(input.trim()) - INDEX_OFFSET;
        } catch (NumberFormatException e) {
            throw new LemonException("Oopsie! Please enter a valid task number!");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new LemonException("Oopsie! Please enter a task number that is on the list!");
        }
        return taskIndex;
    }

    /**
     * Checks if the task index is within the range of the task list.
     * Throws an exception if index is out of bounds.
     *
     * @param taskIndex Index number of task.
     * @param tasks List of tasks.
     * @throws LemonException If task list is empty or if task index is out of bounds of task list.
     */
    public void checkTaskNumberRange(int taskIndex, TaskList tasks) throws LemonException {
        if (tasks.isEmpty()) {
            throw new LemonException(MESSAGE_EMPTY_LIST);
        } else if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new LemonException("Oopsie! Please enter a task number that is on the list!");
        }
    }
}

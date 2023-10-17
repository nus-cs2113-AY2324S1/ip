package exceptions;

import commands.Commands;
import taskmanagement.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for validating user inputs in the Zran application.
 * Provides methods for validating task indices, user input descriptions, and date formats.
 * Throws ZranExceptions with specific error messages when validation fails.
 */
public class UserInputValidation {

    /**
     * Validates and extracts the task index from user input.
     *
     * @param input User's input into the application.
     * @param tasks   The task list of class 'TaskList'.
     * @param command The command associated with the input.
     * @return The validated task index.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    public static int validateTaskIndex(String input, TaskList tasks, String command) throws ZranExceptions {
        String taskIndex = extractTaskIndex(input, command);
        return parseIndex(taskIndex, tasks.listItems.size());
    }

    private static String extractTaskIndex(String input, String command) throws ZranExceptions {
        String taskIndex = input.substring(command.length()).trim();
        if (taskIndex.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.EMPTY_TASK_INDEX.message);
        }
        return taskIndex;
    }

    private static int parseIndex(String indexStr, int listSize) throws ZranExceptions {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= listSize) {
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
        }
    }

    /**
     * Validates and extracts the task description for a task of class 'ToDos' from user's input.
     *
     * @param input   The user input to extract the task description from.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    public static String validateAddTodoCommand(String input) throws ZranExceptions {
        return validateTaskDescription(input, Commands.TODO_TASK_COMMAND);
    }

    /**
     * Validates and extracts the task description for a task of class 'Deadline' from user's input.
     *
     * @param input   The user input to extract the task description from.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    public static String[] validateAddDeadlineCommand(String input) throws ZranExceptions {
        String[] taskInfo = new String[]{ "", ""};
        taskInfo[0] = validateTaskDescription(input, Commands.DEADLINE_TASK_COMMAND);
        taskInfo[1] = validateDeadlineDate(input);
        return taskInfo;
    }

    /**
     * Validates and extracts the task description for a task of class 'Event' from user's input.
     *
     * @param input   The user input to extract the task description from.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    public static String[] validateAddEventCommand(String input) throws ZranExceptions {
        String[] taskInfo = new String[]{ "", "", ""};
        taskInfo[0] = validateTaskDescription(input, Commands.EVENT_TASK_COMMAND);
        return validateEventDuration(input, taskInfo);
    }

    /**
     * Validates the task description extracted from user input by ensuring that it is not empty.
     *
     * @param input   The user input to extract the task description from.
     * @param command The command associated with the input.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    private static String validateTaskDescription(String input, String command) throws ZranExceptions {
        String description = extractTaskDescription(input, command);
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return description;
    }

    /**
     * Validates and extracts the task description from user input.
     *
     * @param input   The user input to extract the task description from.
     * @param command The command associated with the input.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    private static String extractTaskDescription(String input, String command) throws ZranExceptions {
        int descriptionIndex = input.indexOf(command) + command.length();
        if (descriptionIndex == -1) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return input.substring(descriptionIndex).trim();
    }

    /**
     * Validates and extracts the deadline date for a Deadline task from user input.
     *
     * @param input The user input to extract the deadline date from.
     * @return The validated deadline date.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    private static String validateDeadlineDate(String input) throws ZranExceptions {
        int byIndex = input.indexOf(Commands.DEADLINE_DATE_COMMAND);
        if (byIndex == -1) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_DEADLINE_FORMAT.message);
        }
        String by = input.substring(byIndex + Commands.DEADLINE_DATE_COMMAND.length()).trim();
        if (by.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.EMPTY_DEADLINE.message);
        }
        return by;
    }

    /**
     * Validates the deadline task description extracted from user input by ensuring that it is not empty.
     *
     * @param input   The user input to extract the task description from.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    public static String validateDeadlineDescription(String input) throws ZranExceptions {
        int byIndex = input.indexOf(Commands.DEADLINE_DATE_COMMAND);
        String description = input.substring(Commands.DEADLINE_TASK_COMMAND.length(), byIndex).trim();
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return description;
    }

    /**
     * Validates and extracts the event duration for an Event task from user input.
     *
     * @param input     The user input to extract the event duration from.
     * @param taskInfo  An array to store the validated event duration information.
     * @return The validated task information array.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    private static String[] validateEventDuration(String input, String[] taskInfo) throws ZranExceptions {
        int fromIndex = input.indexOf(Commands.EVENT_TASK_START);
        int toIndex = input.indexOf(Commands.EVENT_TASK_END);
        if (fromIndex == -1 || toIndex == -1) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_EVENT_FORMAT.message);
        }
        String from = input.substring(fromIndex + Commands.EVENT_TASK_START.length(), toIndex).trim();
        String to = input.substring(toIndex + Commands.EVENT_TASK_END.length()).trim();
        if (from.isEmpty() || to.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.EMPTY_EVENT_DURATION.message);
        }
        taskInfo[1] = from;
        taskInfo[2] = to;
        return taskInfo;
    }

    /**
     * Validates the event task description extracted from user input by
     * ensuring that it is not empty.
     *
     * @param input   The user input to extract the task description from.
     * @return The validated task description.
     * @throws ZranExceptions If validation fails with specific error messages.
     */
    public static String validateEventDescription(String input) throws ZranExceptions {
        int fromIndex = input.indexOf(Commands.EVENT_TASK_START);
        String description = input.substring(Commands.EVENT_TASK_COMMAND.length(), fromIndex).trim();
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return description;
    }

    public static LocalDate validateDate(String input) throws ZranExceptions {
        LocalDate parsedDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedDate = LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_DATE_FORMAT.message);
        }
        return parsedDate;
    }
}

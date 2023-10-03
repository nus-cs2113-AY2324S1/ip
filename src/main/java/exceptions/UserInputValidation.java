package exceptions;

import commands.Commands;
import taskmanagement.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserInputValidation {
    public static int validateTaskIndex(String input, TaskList tasks, String command) throws ZranExceptions {
        String taskIndex = extractTaskIndex(input, command);
        return validateAndParseIndex(taskIndex, tasks.listItems.size());
    }

    private static String extractTaskIndex(String input, String command) throws ZranExceptions {
        String taskIndex = input.substring(command.length()).trim();
        if (taskIndex.isEmpty()) { //is there a number present?
            throw new ZranExceptions(ZranErrorMessages.EMPTY_TASK_INDEX.message);
        }
        return taskIndex;
    }

    private static int validateAndParseIndex(String indexStr, int listSize) throws ZranExceptions {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= listSize) { //is it in range?
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
            }
            return index;
        } catch (NumberFormatException e) { //in case they gave a letter
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
        }
    }

    public static String validateAddTodoCommand(String input) throws ZranExceptions {
        return validateTaskDescription(input, Commands.TODO_TASK_COMMAND);
    }

    public static String[] validateAddDeadlineCommand(String input) throws ZranExceptions {
        String[] taskInfo = new String[]{ "", ""};
        taskInfo[0] = validateTaskDescription(input, Commands.DEADLINE_TASK_COMMAND);
        taskInfo[1] = validateDeadlineDate(input);
        return taskInfo;
    }

    public static String[] validateAddEventCommand(String input) throws ZranExceptions {
        String[] taskInfo = new String[]{ "", "", ""};
        taskInfo[0] = validateTaskDescription(input, Commands.EVENT_TASK_COMMAND);
        return validateEventDuration(input, taskInfo);
    }

    private static String validateTaskDescription(String input, String command) throws ZranExceptions {
        String description = extractTaskDescription(input, command);
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return description;
    }

    //actly can this be used to remove unrecognised commands branch in parser?
    private static String extractTaskDescription(String input, String command) throws ZranExceptions {
        int descriptionIndex = input.indexOf(command) + command.length();
        if (descriptionIndex == -1) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return input.substring(descriptionIndex).trim();
    }

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

    public static String validateDeadlineDescription(String input) throws ZranExceptions {
        int byIndex = input.indexOf(Commands.DEADLINE_DATE_COMMAND);
        String description = input.substring(Commands.DEADLINE_TASK_COMMAND.length(), byIndex).trim();
        if (description.isEmpty()) {
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_DESCRIPTION.message);
        }
        return description;
    }

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

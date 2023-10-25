/**
 * This class is responsible for parsing and processing user commands for the Ken chatbot.
 * It defines constants for various supported commands and provides methods to handle
 * user input and extract task information from it.
 */
public class CommandParser {

    /**
     * Command keyword for terminating the chatbot.
     */
    public static final String COMMAND_BYE = "bye";

    /**
     * Command keyword for listing tasks.
     */
    public static final String COMMAND_LIST = "list";

    /**
     * Command keyword for adding a to-do task.
     */
    public static final String COMMAND_TODO = "todo";

    /**
     * Command keyword for adding a deadline task.
     */
    public static final String COMMAND_DEADLINE = "deadline";

    /**
     * Command keyword for adding an event task.
     */
    public static final String COMMAND_EVENT = "event";

    /**
     * Command keyword for deleting a task.
     */
    public static final String COMMAND_DELETE = "delete";

    /**
     * Command keyword for marking a task as done.
     */
    public static final String COMMAND_MARK = "mark";

    /**
     * Command keyword for marking a task as done.
     */
    public static final String COMMAND_UNMARK = "unmark";

    /**
     * Command keyword for finding tasks by keyword.
     */
    public static final String COMMAND_FIND = "find";


    /**
     * Processes the user's command, extracts task type and description, and performs
     * the corresponding action (e.g., adding, deleting, marking) on the task list.
     *
     * @param userInput The user's input command.
     * @param taskList The task list to which the command should be applied.
     * @throws KenException If there's an issue with the user's command or task handling.
     */
    public static void processUserCommand(String userInput, TaskList taskList) throws KenException {
        String[] parts = userInput.split(" ", 2);

        if (parts.length != 2) {
            handleInvalidCommand(parts[0]);
        } else {
            String taskType = parts[0].trim();
            String taskDescription = parts[1].trim();

            if (taskType.equalsIgnoreCase(COMMAND_TODO) || taskType.equalsIgnoreCase(COMMAND_DEADLINE) || taskType.equalsIgnoreCase(COMMAND_EVENT)) {
                AddCommand.handleAddTask(taskType, taskDescription, taskList);
            } else if (taskType.equalsIgnoreCase(COMMAND_DELETE)) {
                DeleteCommand.handleDeleteTask(taskDescription, taskList);
            } else if (taskType.equalsIgnoreCase(COMMAND_MARK)) {
                MarkCommand.handleMarkTask(taskDescription, taskList);
            } else if (taskType.equalsIgnoreCase(COMMAND_UNMARK)) {
                MarkCommand.handleUnmarkTask(taskDescription, taskList);
            } else {
                throw new InvalidCommandException();
            }
        }
    }

    private static void handleInvalidCommand(String command) throws InvalidCommandException, EmptyDescriptionException, WrongFormatException {
        if (command.equalsIgnoreCase(COMMAND_DELETE)) {
            throw new WrongFormatException();
        } else if (command.equalsIgnoreCase(COMMAND_TODO) || command.equalsIgnoreCase(COMMAND_DEADLINE) || command.equalsIgnoreCase(COMMAND_EVENT)) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + command + " task.");
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Extracts date information from a task description, if present, and returns
     * an array containing the task description and date information.
     *
     * @param description The task description that may contain date information.
     * @return An array containing the task description and date information.
     * @throws AddCommand.MissingInfoException If there's an issue with the date information extraction.
     */
    public static String[] extractDateInfo(String description) throws AddCommand.MissingInfoException {
        if (description.contains("/from") && description.contains("/to")) {
            return extractEventDateInfo(description);
        } else if (description.contains("/by")) {
            return extractDeadlineDateInfo(description);
        } else {
            return new String[]{description, null};
        }
    }

    /**
     * Extracts date information for an event task from the given description.
     *
     * @param description The task description that contains date information for an event task.
     * @return An array containing the task description and date information.
     * @throws AddCommand.MissingInfoException If there's an issue with the date information extraction.
     */
    private static String[] extractEventDateInfo(String description) throws AddCommand.MissingInfoException {
        String[] parts = description.split(" /from | /to ");
        if (parts.length > 2) {
            String startDate = parts[1];
            String endDate = parts[2];
            if (endDate.isEmpty()) {
                throw new AddCommand.MissingInfoException("/to date cannot be empty");
            }
            String dateInfo = " from: " + startDate + " to: " + endDate;
            return new String[]{parts[0], dateInfo};
        } else {
            throw new AddCommand.MissingInfoException("Invalid date format for event task");
        }
    }

    /**
     * Extracts date information for a deadline task from the given description.
     *
     * @param description The task description that contains date information for a deadline task.
     * @return An array containing the task description and date information.
     */
    private static String[] extractDeadlineDateInfo(String description) {
        String[] parts = description.split(" /by ");
        if (parts.length > 1) {
            String deadlineDate = parts[1];
            String dateInfo = " by: " + deadlineDate;
            return new String[]{parts[0], dateInfo};
        }
        return new String[]{description, null};
    }
}

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
     * Command keyword for finding tasks by keyword.
     */
    public static final String COMMAND_FIND = "find";

    /**
     * Processes the user's command, extracts task type and description, and performs
     * the corresponding action (e.g., adding, deleting, marking) on the task list.
     *
     * @param userInput The user's input command.
     * @param taskList The task list to which the command should be applied.
     * @param storage The storage object for saving the task list.
     * @throws KenException If there's an issue with the user's command or task handling.
     */
    public static void processUserCommand(String userInput, TaskList taskList, Storage storage) throws KenException {
        String[] parts = userInput.split(" ", 2);

        if (parts.length != 2) {
            handleInvalidCommand(parts[0]);
        } else {
            String taskType = parts[0].trim();
            String taskDescription = parts[1].trim();

            if (taskType.equalsIgnoreCase(COMMAND_TODO) || taskType.equalsIgnoreCase(COMMAND_DEADLINE) || taskType.equalsIgnoreCase(COMMAND_EVENT)) {
                TaskList.handleAddTask(taskType, taskDescription, taskList);
                storage.saveTasks(taskList); // Save tasks after adding
            } else if (taskType.equalsIgnoreCase(COMMAND_DELETE)) {
                TaskList.handleDeleteTask(taskDescription, taskList);
                storage.saveTasks(taskList); // Save tasks after deleting
            } else if (taskType.equalsIgnoreCase(COMMAND_MARK)) {
                TaskList.handleMarkTask(taskDescription, taskList);
                storage.saveTasks(taskList); // Save tasks after marking
            } else {
                throw new KenException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].'");
            }
        }
    }

    private static void handleInvalidCommand(String command) throws InvalidCommandException, EmptyDescriptionException {
        if (command.equalsIgnoreCase(COMMAND_DELETE)) {
            throw new InvalidCommandException("Please provide a task number to delete.");
        } else if (command.equalsIgnoreCase(COMMAND_TODO) || command.equalsIgnoreCase(COMMAND_DEADLINE) || command.equalsIgnoreCase(COMMAND_EVENT)) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + command + " task.");
        } else {
            throw new InvalidCommandException("Hmm, what's that? Please use 'todo,' 'deadline,' 'event,' 'delete [number],' or 'mark [number].'");
        }
    }

    /**
     * Extracts date information from a task description, if present, and returns
     * an array containing the task description and date information.
     *
     * @param description The task description that may contain date information.
     * @return An array containing the task description and date information.
     */
    public static String[] extractDateInfo(String description) {
        if (description.contains("/from") && description.contains("/to")) {
            String[] parts = description.split(" /from | /to ");
            if (parts.length > 1) {
                String startDate = parts[1];
                String endDate = parts[2];
                String dateInfo = " from: " + startDate + " to: " + endDate;
                return new String[]{parts[0], dateInfo};
            }
        } else if (description.contains("/by")) {
            String[] parts = description.split(" /by ");
            if (parts.length > 1) {
                String deadlineDate = parts[1];
                String dateInfo = " by: " + deadlineDate;
                return new String[]{parts[0], dateInfo};
            }
        }
        return new String[]{description, null};
    }
}

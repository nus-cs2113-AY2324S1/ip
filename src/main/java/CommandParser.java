public class CommandParser {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_MARK = "mark";

    public static final String COMMAND_FIND = "find";


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

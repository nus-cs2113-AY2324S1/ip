public class AddCommand {
    /**
     * Handles the addition of a task to the task list.
     *
     * @param taskType        The type of the task (e.g., "T" for Todo).
     * @param taskDescription The description of the task.
     * @param taskList        The TaskList to which the task will be added.
     * @throws EmptyDescriptionException If the task description is empty.
     * @throws MissingInfoException      If required information is missing for a task type.
     */
    public static void AddTask(String taskType, String taskDescription, TaskList taskList) throws EmptyDescriptionException, MissingInfoException {
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + taskType + " task.");
        }
        taskType = taskType.substring(0, 1).toUpperCase();
        String[] dateInfo = CommandParser.extractDateInfo(taskDescription);
        if (isMissingInfo(taskType, dateInfo)) {
            throw new MissingInfoException(taskType);
        }
        taskDescription = dateInfo[0];
        addTaskToList(taskType, taskDescription, dateInfo[1], taskList);
        displayTaskAddedMessage(taskType, taskDescription, dateInfo[1], taskList);
    }

    /**
     * Checks if required information is missing for a task type.
     *
     * @param taskType The type of the task.
     * @param dateInfo An array containing task description and date information.
     * @return True if required information is missing, otherwise false.
     */
    private static boolean isMissingInfo(String taskType, String[] dateInfo) {
        if (taskType.equals("D") && dateInfo[1] == null) {
            return true;
        } else return taskType.equals("E") && (dateInfo[1] == null || dateInfo[1].isEmpty());
    }

    /**
     * Adds a task to the task list.
     *
     * @param taskType        The type of the task.
     * @param taskDescription The description of the task.
     * @param dateInfo        The date information for the task.
     * @param taskList        The TaskList to which the task will be added.
     */
    private static void addTaskToList(String taskType, String taskDescription, String dateInfo, TaskList taskList) {
        taskList.getTaskTypes().add(taskType);
        taskList.getTaskDates().add(dateInfo);
        taskList.getTaskDescriptions().add(taskDescription.trim());
        taskList.getTaskDoneStatus().add(false);
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param taskType        The type of the task.
     * @param taskDescription The description of the task.
     * @param dateInfo        The date information for the task.
     * @param taskList        The TaskList to which the task was added.
     */
    private static void displayTaskAddedMessage(String taskType, String taskDescription, String dateInfo, TaskList taskList) {
        System.out.println(" Got it. I've added this task:");
        String dateInfoText = dateInfo != null ? " " + dateInfo : "";
        System.out.println("   [" + taskType + "][ ] " + taskDescription + dateInfoText);
        System.out.println(" Now you have " + taskList.getTaskDescriptions().size() + " tasks in the list.");
    }

    /**
     * Handles adding a task and saves tasks after adding it.
     *
     * @param taskType        The type of the task (e.g., "T" for Todo).
     * @param taskDescription The description of the task.
     * @param taskList        The TaskList to which the task will be added.
     * @throws KenException If there's an issue with the task or task list handling.
     */
    public static void handleAddTask(String taskType, String taskDescription, TaskList taskList) throws KenException {
        AddTask(taskType, taskDescription, taskList);
        Storage.saveTasks(taskList); // Save tasks after deleting
    }

    public static class MissingInfoException extends KenException {
        public MissingInfoException(String taskType) {
            super("Hey!! Don't leave the description or date empty for a " + taskType + " task.");
        }
    }
}

public class AddCommand {
    /**
     * Handles the addition of a task to the task list.
     *
     * @param taskType        The type of the task (e.g., "T" for Todo).
     * @param taskDescription The description of the task.
     * @param taskList        The TaskList to which the task will be added.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    public static void AddTask(String taskType, String taskDescription, TaskList taskList) throws EmptyDescriptionException {
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException("Hey!! Description cannot be empty for a " + taskType + " task.");
        }

        taskType = taskType.substring(0, 1).toUpperCase();
        String[] dateInfo = CommandParser.extractDateInfo(taskDescription);
        taskDescription = dateInfo[0];
        taskList.getTaskTypes().add(taskType);
        taskList.getTaskDates().add(dateInfo[1]);
        taskList.getTaskDescriptions().add(taskDescription.trim());
        taskList.getTaskDoneStatus().add(false);

        System.out.println(" Got it. I've added this task:");
        String dateInfoText = taskList.getTaskDates().get(taskList.getTaskDescriptions().size() - 1) != null ? " " + taskList.getTaskDates().get(taskList.getTaskDescriptions().size() - 1) : "";
        System.out.println("   [" + taskType + "][ ] " + taskDescription + dateInfoText);
        System.out.println(" Now you have " + taskList.getTaskDescriptions().size() + " tasks in the list.");
    }

    public static void handleAddTask(String taskType, String taskDescription, TaskList taskList) throws KenException {
        AddTask(taskType, taskDescription, taskList);
        Storage.saveTasks(taskList); // Save tasks after deleting
    }
}

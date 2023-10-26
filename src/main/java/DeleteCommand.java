

public class DeleteCommand {
    /**
     * Handles the deletion of a task from the task list.
     *
     * @param taskDescription The description of the task to be deleted.
     * @param taskList        The TaskList from which the task will be deleted.
     * @throws KenException If there is an error while handling the task deletion.
     */
    public static void DeleteTask(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                String deletedTaskDescription = taskList.getTaskDescriptions().get(taskIndex);
                printDeletedTaskInfo(taskList, taskIndex, deletedTaskDescription);
                Storage.deleteTask(taskIndex, taskList);
                System.out.println("Now you have " + taskList.getTaskDescriptions().size() + " tasks in the list.");
            }  else {
                throw new TaskNotFoundException();
            }
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    public static void handleDeleteTask(String taskDescription, TaskList taskList) throws KenException {
        DeleteTask(taskDescription, taskList);
        Storage.saveTasks(taskList); // Save tasks after deleting
    }

    private static void printDeletedTaskInfo(TaskList taskList, int taskIndex, String deletedTaskDescription) {
        System.out.println("Got it. I've removed this task:");
        System.out.println("   [" + taskList.getTaskTypes().get(taskIndex)
                + "][ ] " + deletedTaskDescription
                + (taskList.getTaskDates().get(taskIndex) != null ? " " + taskList.getTaskDates().get(taskIndex) : ""));
    }
}

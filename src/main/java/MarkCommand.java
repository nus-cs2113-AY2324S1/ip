public class MarkCommand {
    /**
     * Handles the marking of a task as completed in the task list.
     *
     * @param taskDescription The description of the task to be marked as completed.
     * @param taskList        The TaskList in which the task will be marked.
     * @throws KenException If there is an error while handling the task completion.
     */
    public static void handleMarkTask(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                if (!taskList.getTaskDoneStatus().get(taskIndex)) {
                    taskList.getTaskDoneStatus().set(taskIndex, true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" [X] " + taskList.getTaskDescriptions().get(taskIndex) + (taskList.getTaskDates().get(taskIndex) != null ? taskList.getTaskDates().get(taskIndex) : ""));
                } else {
                    System.out.println("This task is already marked as done.");
                }
            } else {
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
        }
    }

    /**
     * Handles the unmarking of a task as incomplete in the task list.
     *
     * @param taskDescription The description of the task to be unmarked.
     * @param taskList        The TaskList in which the task will be unmarked.
     * @throws KenException If there is an error while handling the task unmarking, such as an invalid task number.
     */
    public static void handleUnmarkTask(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                if (taskList.getTaskDoneStatus().get(taskIndex)) {
                    taskList.getTaskDoneStatus().set(taskIndex, false);
                    System.out.println("I've unmarked this task:");
                    System.out.println(" [ ] " + taskList.getTaskDescriptions().get(taskIndex) + (taskList.getTaskDates().get(taskIndex) != null ? taskList.getTaskDates().get(taskIndex) : ""));
                } else {
                    System.out.println("This task is already unmarked.");
                }
            } else {
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
        }
    }

}

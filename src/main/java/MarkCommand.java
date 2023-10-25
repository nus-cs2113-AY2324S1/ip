public class MarkCommand {
    /**
     * Handles the marking or unmarking of a task as completed in the task list.
     *
     * @param taskDescription The description of the task to be marked or unmarked.
     * @param taskList        The TaskList in which the task will be marked or unmarked.
     * @param markAsDone      Set to true to mark the task as done, false to unmark it.
     * @throws KenException If there is an error while handling the task completion or unmarking.
     */
    private static void markOrUnmarkTask(String taskDescription, TaskList taskList, boolean markAsDone) throws KenException {
        int taskIndex = getTaskIndex(taskDescription, taskList);
        boolean currentStatus = taskList.getTaskDoneStatus().get(taskIndex);

        if (shouldMarkOrUnmark(currentStatus, markAsDone)) {
            taskList.getTaskDoneStatus().set(taskIndex, markAsDone);
            displayMarkedOrUnmarkedMessage(markAsDone, taskList, taskIndex);
        } else {
            System.out.println("This task is already " + (markAsDone ? "marked as done." : "unmarked."));
        }
    }

    private static int getTaskIndex(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                return taskIndex;
            } else {
                throw new TaskNotFoundException();
            }
        } catch (NumberFormatException e) {
            throw new WrongFormatException();
        }
    }

    private static boolean shouldMarkOrUnmark(boolean currentStatus, boolean markAsDone) {
        return (markAsDone && !currentStatus) || (!markAsDone && currentStatus);
    }

    private static void displayMarkedOrUnmarkedMessage(boolean markAsDone, TaskList taskList, int taskIndex) {
        String status = markAsDone ? "marked as done" : "unmarked";
        String statusSymbol = markAsDone ? "X" : " ";
        System.out.println("Nice! I've " + status + " this task:");
        System.out.println(" [" + statusSymbol + "] " + taskList.getTaskDescriptions().get(taskIndex) + (taskList.getTaskDates().get(taskIndex) != null ? taskList.getTaskDates().get(taskIndex) : ""));
    }

    /**
     * Handles the marking of a task as completed in the task list.
     *
     * @param taskDescription The description of the task to be marked as completed.
     * @param taskList        The TaskList in which the task will be marked.
     * @throws KenException If there is an error while handling the task completion.
     */
    public static void MarkTask(String taskDescription, TaskList taskList) throws KenException {
        markOrUnmarkTask(taskDescription, taskList, true);
    }

    /**
     * Handles the unmarking of a task as incomplete in the task list.
     *
     * @param taskDescription The description of the task to be unmarked.
     * @param taskList        The TaskList in which the task will be unmarked.
     * @throws KenException If there is an error while handling the task unmarking, such as an invalid task number.
     */
    public static void UnmarkTask(String taskDescription, TaskList taskList) throws KenException {
        markOrUnmarkTask(taskDescription, taskList, false);
    }

    public static void handleMarkTask(String taskDescription, TaskList taskList) throws KenException {
        MarkTask(taskDescription, taskList);
        Storage.saveTasks(taskList); // Save tasks after marking
    }

    public static void handleUnmarkTask(String taskDescription, TaskList taskList) throws KenException {
        UnmarkTask(taskDescription, taskList);
        Storage.saveTasks(taskList); // Save tasks after unmarking
    }
}

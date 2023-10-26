public class ListCommand {
    /**
     * Lists all the tasks in the task list.
     *
     * @param taskList The TaskList to be listed.
     */
    public static void listTasks(TaskList taskList) {
        System.out.println(" Here are the tasks in your list:");
        int numTasks = taskList.getTaskDescriptions().size(); // Get the number of tasks

        for (int i = 0; i < numTasks; i++) {
            char doneSymbol = taskList.getTaskDoneStatus().get(i) ? 'X' : ' ';
            String dateInfo = taskList.getTaskDates().get(i) != null ? taskList.getTaskDates().get(i) : "";
            System.out.println(" " + (i + 1) + ".[" + taskList.getTaskTypes().get(i) + "][" + doneSymbol + "] " + taskList.getTaskDescriptions().get(i) + dateInfo);
        }
    }
}

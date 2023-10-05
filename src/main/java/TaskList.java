import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages the task-related data and provides methods to manipulate tasks.
 */
public class TaskList {

    /**
     * A list to store task descriptions.
     */
    public static List<String> taskDescriptions = new ArrayList<>();

    /**
     * A list to store task completion status (true for completed, false for incomplete).
     */
    public static List<Boolean> taskDoneStatus = new ArrayList<>();

    /**
     * A list to store task types (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */
    public static List<String> taskTypes = new ArrayList<>();

    /**
     * A list to store task dates (optional for some task types).
     */
    public static List<String> taskDates = new ArrayList<>();

    /**
     * Parses a task description from a string and adds it to the task lists.
     *
     * @param line The string containing task information.
     */
    public void parseAndAddTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            taskTypes.add(parts[0]);
            taskDoneStatus.add(parts[1].equals("1"));
            taskDescriptions.add(parts[2]);

            if (parts[0].equals("T")) {
                taskDates.add(null); // Set the date to null for Todo tasks
            } else if (parts.length >= 4) {
                taskDates.add(parts[3]); // Set the date for Deadline and Event tasks
            } else {
                taskDates.add(null); // Handle the case where the date is missing for Deadline and Event tasks
            }
        }
    }

    /**
     * Retrieves the list of task descriptions.
     *
     * @return The list of task descriptions.
     */
    public List<String> getTaskDescriptions() {
        return taskDescriptions;
    }

    /**
     * Retrieves the list of task completion statuses.
     *
     * @return The list of task completion statuses.
     */
    public List<Boolean> getTaskDoneStatus() {
        return taskDoneStatus;
    }

    /**
     * Retrieves the list of task types.
     *
     * @return The list of task types.
     */
    public List<String> getTaskTypes() {
        return taskTypes;
    }

    /**
     * Retrieves the list of task dates.
     *
     * @return The list of task dates.
     */
    public List<String> getTaskDates() {
        return taskDates;
    }

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

    /**
     * Handles the addition of a task to the task list.
     *
     * @param taskType       The type of the task (e.g., "T" for Todo).
     * @param taskDescription The description of the task.
     * @param taskList       The TaskList to which the task will be added.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    public static void handleAddTask(String taskType, String taskDescription, TaskList taskList) throws EmptyDescriptionException {
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

    /**
     * Handles the deletion of a task from the task list.
     *
     * @param taskDescription The description of the task to be deleted.
     * @param taskList       The TaskList from which the task will be deleted.
     * @throws KenException If there is an error while handling the task deletion.
     */
    public static void handleDeleteTask(String taskDescription, TaskList taskList) throws KenException {
        try {
            int taskIndex = Integer.parseInt(taskDescription.trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.getTaskDescriptions().size()) {
                String deletedTaskDescription = taskList.getTaskDescriptions().get(taskIndex);

                System.out.println("Got it. I've removed this task:");
                System.out.println("   [" + taskList.getTaskTypes().get(taskIndex) + "][ ] " + deletedTaskDescription + (taskList.getTaskDates().get(taskIndex) != null ? taskList.getTaskDates().get(taskIndex) : ""));
                Storage.deleteTask(taskIndex, taskList);
                System.out.println("Now you have " + taskList.getTaskDescriptions().size() + " tasks in the list.");
            } else {
                throw new TaskNotFoundException("I can't find this task. Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            throw new TaskNotFoundException("Wrong format!! Please provide the number of the task.");
        }
    }

    /**
     * Handles the marking of a task as completed in the task list.
     *
     * @param taskDescription The description of the task to be marked as completed.
     * @param taskList       The TaskList in which the task will be marked.
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
     * Converts a task at the specified index into a formatted string.
     *
     * @param index The index of the task to be converted.
     * @return The formatted string representing the task.
     */
    public String taskToString(int index) {
        String taskType = taskTypes.get(index);
        String doneStatus = taskDoneStatus.get(index) ? "[X]" : "[ ]";
        String description = taskDescriptions.get(index);
        String date = taskDates.get(index);

        switch (taskType) {
            case "T":
                return doneStatus + " " + description;
            case "D":
                if (date != null) {
                    return doneStatus + " " + description +  " (" + date + ")";
                } else {
                    return doneStatus + " " + description;
                }
            case "E":
                if (date != null) {
                    String[] dateParts = date.split(" to ");
                    if (dateParts.length == 2) {
                        return doneStatus + " " + description + " (" + dateParts[0] + " to " + dateParts[1] + ")";
                    } else if (dateParts.length == 1) {
                        return doneStatus + " " + description + " (" + dateParts[0] + " )";
                    }
                } else {
                    return doneStatus + " " + description;
                }
            default:
                return "Unknown task type: " + taskType;
        }
    }


    /**
     * Finds tasks containing a specified keyword and returns them in a list.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks.
     */
    public ArrayList<String> findTasksByKeyword(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskDescriptions.size(); i++) {
            String description = taskDescriptions.get(i);
            if (description.contains(keyword)) {
                String matchingTask = "[" + taskTypes.get(i) + "]" + taskToString(i);
                matchingTasks.add((i + 1) + ". " + matchingTask); // Include the task number
            }
        }
        return matchingTasks;
    }

    /**
     * Handles the "find" command, searching for tasks with a specified keyword.
     *
     * @param userInput The user input string containing the "find" command and keyword.
     */
    public void handleFindCommand(String userInput) {
        String keyword = userInput.substring(CommandParser.COMMAND_FIND.length()).trim();
        ArrayList<String> matchingTasks = findTasksByKeyword(keyword);

        Ui.printLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (String matchingTask : matchingTasks) {
                System.out.println(matchingTask);
            }
        }
        Ui.printLine();
    }


}

